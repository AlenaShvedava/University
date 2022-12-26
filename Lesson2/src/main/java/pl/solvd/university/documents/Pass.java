package pl.solvd.university.documents;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static pl.solvd.university.Main.LOG;
import static pl.solvd.university.Main.applicant;

public class Pass {
    private final String name;

    public Pass(String name) {
        this.name = name;
    }

    public static void give() {
        Pass myPass = new Pass(applicant.getFirstLastName());
        LOG.info("\nApplicant is issued a pass");
        System.out.println("\nAdmission Office:\nSIGN THE CONTRACT:\nYou are going to leave your documents with us.\nIn return, you will be issued an Applicant Pass.\nSign that the documents have been exchanged (yes/no)\n");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        if ((!(answer.toUpperCase(Locale.ROOT).equals("YES")) && (!(answer.toUpperCase(Locale.ROOT).equals("NO"))))) {
            try {
                throw new IOException("Exception: You entered an invalid value. Possible answers: yes/no. Let's try again");
            } catch (IOException e) {
                LOG.error("Exception: Invalid input. Something went wrong. Let's try again");
                System.out.println(e.getMessage());
                give();
            }
        } else {
            switch (answer) {
                case "NO" -> {
                    LOG.info("The Applicant does not want to give his documents. To pass the exams, you must leave your documents at the Admissions Office");
                    System.out.println("Signature required");
                    give();
                }
                case "YES" -> {
                    LOG.info("The Applicant agrees to give his documents in exchange for Pass to the exams");
                    System.out.println("\nYou received an Applicant's pass");
                    show();
                }
            }
        }
    }

    public static void take() {
        System.out.println("Admission Office:\nYou handed over the Applicant's pass");
    }

    public static String center(String string, int length, char pad) {
        StringBuilder sb = new StringBuilder(length);
        sb.setLength((length - string.length() + 1) / 2);
        sb.append(string);
        sb.setLength(length);
        return sb.toString().replace('\0', pad);
    }

    public static void show() {

        System.out.println(" " + (center("", 25, '_') + " "));
        System.out.println("|" + (center("P A S S", 25, ' ') + "|"));
        System.out.println("|" + (center(applicant.getFirstLastName(), 25, ' ') + "|"));
        System.out.println("|" + (center("", 25, '_') + "|"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pass pass = (Pass) o;
        return Objects.equals(name, pass.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
