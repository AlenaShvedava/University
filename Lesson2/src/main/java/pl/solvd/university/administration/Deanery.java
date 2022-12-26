package pl.solvd.university.administration;

import pl.solvd.university.people.Applicant;
import pl.solvd.university.people.Student;
import pl.solvd.university.state.ReturnDocuments;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static pl.solvd.university.Main.*;

public class Deanery implements Comparator<Applicant> {

    @Override
    public int compare(Applicant o1, Applicant o2) {
        if (o2.getSum() == o1.getSum()) {
            return o2.getFirstLastName().compareTo(o1.getFirstLastName());
        } else
            return o2.getSum() - o1.getSum();
    }

    public static void sortByGrades(List<Applicant> sortedList, int NumOfBudgetPlaces, int NumOfPaidPlaces, String getSpecialty) throws Exception {
        System.out.println("Do you want to see the rating of Applicants for your Specialty? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        if ((!(answer.toUpperCase(Locale.ROOT).equals("YES")) && (!(answer.toUpperCase(Locale.ROOT).equals("NO"))))) {
            try {
                throw new IOException("Exception: Something went wrong. You entered an invalid value");
            } catch (IOException e) {
                LOG.error("Exception: Invalid input. The program is closed");
                System.out.println(e.getMessage());
                sortByGrades(sortedList, NumOfBudgetPlaces, NumOfPaidPlaces, getSpecialty);
            }
        } else {
            switch (answer) {
                case "NO" -> {
                    LOG.info("Well, the Applicant is not ready to see the rating of applicants. You can see this list later");
                    sortByGrades(sortedList, NumOfBudgetPlaces, NumOfPaidPlaces, getSpecialty);
                }
                case "YES" -> {
                    LOG.info("Applicant wants to see the results. Results sorted in descending order");
                    Set<Applicant> sortedApplicants = new TreeSet<>(new Deanery());
                    sortedApplicants.addAll(sortedList);
                    List<Applicant> sortedApplicantsCopy = sortedApplicants.stream().toList();
                    System.out.println("\nRATING LIST:\n");
                    IntStream.range(0, sortedApplicantsCopy.size()).forEach(i -> System.out.println((i + 1) + ". " + sortedApplicantsCopy.get(i)));
                    int sum = NumOfBudgetPlaces + NumOfPaidPlaces;
                    System.out.printf("\nFor the specialty %s is provided:\nBudget places - %d\nPaid places - %d\n", getSpecialty, NumOfBudgetPlaces, NumOfPaidPlaces);
                    LOG.info("Looking for an Applicant in the list and look at his rating");
                    for (int i = 0; i < sortedApplicantsCopy.size(); i++) {
                        boolean isMyApplicant = sortedApplicantsCopy.get(i).equals(applicant);
                        if (isMyApplicant) {
                            boolean isEnteredTheBudget = i < NumOfBudgetPlaces;
                            boolean isEnteredThePaidPlace = i >= NumOfBudgetPlaces && i < sum;
                            if (isEnteredTheBudget) {
                                System.out.println("\nGreat! You are enrolled in the University for a budget place!");
                                LOG.info("The list of Applicants recommended for enrollment is sent to the Dean of the faculty for approval");
                            } else if (isEnteredThePaidPlace) {
                                System.out.println("\nGreat! You are enrolled in Paid education at the University!\nGo to the Accounting department to conclude a payment agreement.\n");
                                LOG.info("The list of Applicants recommended for enrollment is sent to the Dean of the faculty for approval");
                                Accounting.agreement(Accounting.Bill.EDUCATION);
                            } else {
                                System.out.println("\nUnfortunately, you are not enrolled in the University");
                                LOG.info("Applicant goes to return his documents");
                                applicant.changeActivity(activity, new ReturnDocuments());
                                applicant.save();
                                AdmissionsOffice.returnOfDocuments();
                                break;
                            }
                            applicant.save();
                            Student.getFirstCourse(sortedApplicantsCopy.subList(0, sum));
                            System.out.println("We are glad to see you in the list of first-year students!");
                        }
                    }
                }
            }
        }
    }
}
