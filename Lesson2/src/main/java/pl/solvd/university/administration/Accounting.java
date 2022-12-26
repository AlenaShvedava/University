package pl.solvd.university.administration;

import pl.solvd.university.state.Payment;
import pl.solvd.university.state.ReturnDocuments;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import static pl.solvd.university.Main.*;
import static pl.solvd.university.documents.Pass.show;

public class Accounting {
    public enum Bill {
        EDUCATION(2000);
        private final int price;

        Bill(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }

    static void exceptPayment(Bill category) throws Exception {
        System.out.printf("Accounting:\nNEW BILL:\n%s payment is %d dollars.\n", category.name(), category.getPrice());
        System.out.println("Ready to make a payment?");
        Scanner answer = new Scanner(System.in);
        String chooseDone = answer.next();
        if ((!(chooseDone.toUpperCase(Locale.ROOT).equals("YES")) && (!(chooseDone.toUpperCase(Locale.ROOT).equals("NO"))))) {
            try {
                throw new IOException("Exception: Something went wrong. Please, try to pay again");
            } catch (IOException e) {
                LOG.error("Exception: Something went wrong. Invalid input. Try to pay again");
                System.out.println(e.getMessage());
                exceptPayment(Bill.EDUCATION);
                applicant.save();
            }
        }
            else{
                switch (chooseDone.toUpperCase(Locale.ROOT)) {
                    case "NO" -> {
                        LOG.info("Applicant refused to pay now");
                        System.out.println("Payment declined. Please try to pay later");
                    }
                    case "YES" -> {
                        LOG.info("Payment was successful");
                        System.out.println("Payment accepted");
                    }
                }
            }
        }

        static void agreement (Bill category) throws Exception {
            System.out.printf("Accounting:\n%s payment is %d dollars\n", category.name(), category.getPrice());
            System.out.print("If you decide to study on a paid basis, please,\n-submit your Pass and \n-sign a contract for Paid training\n(yes/no):\n");
            Scanner answer = new Scanner(System.in);
            String chooseDone = answer.next();
            if ((!(chooseDone.toUpperCase(Locale.ROOT).equals("YES")) && (!(chooseDone.toUpperCase(Locale.ROOT).equals("NO"))))) {
                try {
                    throw new IOException("Exception: Something went wrong. You entered an invalid value. Let's try again");
                } catch (IOException e) {
                    LOG.error("Exception: Invalid input");
                    System.out.println(e.getMessage());
                    agreement(Accounting.Bill.EDUCATION);
                }
            } else {
                switch (chooseDone.toUpperCase(Locale.ROOT)) {
                    case "NO" -> {
                        LOG.info("Refused. Applicant goes to return his documents");
                        System.out.println("Ok. You have decided not to study on a Paid basis\nGo to the Admissions Office to return the documents\n");
                        applicant.changeActivity(activity, new ReturnDocuments());
                        applicant.save();
                        AdmissionsOffice.returnOfDocuments();
                    }
                    case "YES" -> {
                        LOG.info("The Applicant agrees to sign a contract for Paid education");
                        show();
                        System.out.println("Ok. The contract for Paid training has been signed\n");
                        applicant.changeActivity(activity, new Payment());
                        exceptPayment(Bill.EDUCATION);
                    }
                }
            }
        }
    }
