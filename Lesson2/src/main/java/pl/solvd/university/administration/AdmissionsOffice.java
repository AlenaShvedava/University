package pl.solvd.university.administration;

import pl.solvd.university.departments.Faculty;
import pl.solvd.university.departments.Speciality;
import pl.solvd.university.documents.Pass;
import pl.solvd.university.people.Applicant;
import pl.solvd.university.state.PassExams;
import pl.solvd.university.state.Registration;
import pl.solvd.university.state.SaveLoadFiles;

import java.io.IOException;
import java.util.*;

import static pl.solvd.university.Main.*;

public class AdmissionsOffice {
    public static List<Applicant> applicants = new ArrayList<>();

    public static void chooseSpeciality() throws IOException, ClassNotFoundException {
        SaveLoadFiles.load("Lesson2/src/main/resources/state.bin");
        System.out.println("\nHere you can see the Faculties of the University: ");
        LOG.info("The list of Faculties is displayed");
        for (Faculty categories : Faculty.values()) {
            System.out.println(categories);
        }
        System.out.println("\nHere you can see the Specialities of the University: ");
        LOG.info("The list of Specialities is displayed");
        for (Speciality type : Speciality.values()) {
            System.out.println(type);
        }
        System.out.println("\nWrite what specialty you want to study:\n1 - CONCERT_PERFORMER,\n2 - VOCAL_SINGER,\n3 - MUSICAL_DIRECTOR,\n4 - CHOREOGRAPHER\n");
        Scanner input = new Scanner(System.in);
        switch (input.next()) {
            case "1" -> applicant.setSpeciality("CONCERT_PERFORMER");
            case "2" -> applicant.setSpeciality("VOCAL_SINGER");
            case "3" -> applicant.setSpeciality("MUSICAL_DIRECTOR");
            case "4" -> applicant.setSpeciality("CHOREOGRAPHER");
            default -> {
                LOG.info("Exception: You entered an invalid value. Make a choice of Specialty and Faculty again");
                System.out.println("Exception: You entered an invalid value. Please review the list of Faculties again and choose your Specialty");
                chooseSpeciality();
            }
        }
        for (Speciality type : Speciality.values()) {
            if (applicant.getSpeciality().equals(type.name())) {
                applicant.setFaculty(String.valueOf(type.getCategory()));
                LOG.info("Data on the chosen Faculty and Specialty are confirmed. List of exams issued");
                System.out.printf("Ok. Your faculty is %s. Your have to pass the exams: %s.\n", applicant.getFaculty(), type.getExams());
                applicant.changeActivity(new Registration());
                applicant.saveState();
            }
        }
    }

    public static void registration() throws Exception {
        SaveLoadFiles.load("Lesson2/src/main/resources/state.bin");
        LOG.info("Requested confirmation of further registration of the Applicant in the chosen Specialty");
        System.out.printf("Apply to faculty: %s, speciality: %s? (yes/no) \n", applicant.getFaculty(), applicant.getSpeciality());
        Scanner faculty = new Scanner(System.in);
        String chooseDone = faculty.next();
        if ((!(chooseDone.toUpperCase(Locale.ROOT).equals("YES")) && (!(chooseDone.toUpperCase(Locale.ROOT).equals("NO"))))) {
            try {
                throw new IOException("Exception: You entered an invalid value. Please, confirm your choice");
            } catch (IOException e) {
                LOG.error("Exception: Something went wrong. Invalid input");
                System.out.println(e.getMessage());
                registration();
            }
        } else {
            switch (chooseDone.toUpperCase(Locale.ROOT)) {
                case "NO": {
                    LOG.info("Applicant chose the wrong specialty. Start over");
                    System.out.println("To choose another specialty, start over");
                    main(null);
                }
                case "YES": {
                    LOG.info("Speciality is chosen. Request additional information about the Applicant for registration");
                    System.out.println("Enter your First name \n");
                    Scanner scan = new Scanner(System.in);
                    String name = scan.next();
                    if (name.matches("[a-zA-Z]+")) {
                        applicant.setFirstName(name);
                    } else
                        try {
                            throw new IOException("Exception: Only letters can be entered in this field. Please, try to register again");
                        } catch (IOException e) {
                            LOG.error("Exception: Invalid characters were entered for the field. Try to register again");
                            System.out.println(e.getMessage());
                            registration();
                            break;
                        }
                    System.out.println("Enter your Last name \n");
                    String surname = scan.next();
                    if (surname.matches("[a-zA-Z]+")) {
                        applicant.setLastName(surname);
                    } else
                        try {
                            throw new IOException("Exception: Only letters can be entered in this field. Please, try to register again");
                        } catch (IOException e) {
                            LOG.error("Exception: Invalid characters were entered for the field. Try to register again");
                            System.out.println(e.getMessage());
                            registration();
                            break;
                        }
                    System.out.println("Enter your date of birth\n");
                    StringBuilder dateOfBirth = new StringBuilder();
                    System.out.println("day(dd):");
                    String day = scan.next();
                    System.out.println("month(MM):");
                    String month = scan.next();
                    System.out.println("year(yyyy(1900/2000)):");
                    String year = scan.next();
                    if (day.matches("0?[1-9]|[12][0-9]|3[01]]") && month.matches("0?[1-9]|1[0-2]") && year.matches("(19|20)[0-9][0-9]")) {
                        dateOfBirth.append(day).append(".");
                        dateOfBirth.append(month).append(".");
                        dateOfBirth.append(year);
                        applicant.setDateOfBirth(String.valueOf(dateOfBirth));
                    } else {
                        try {
                            throw new IOException("Exception: Invalid characters were entered for the field. Please, try to register again");
                        } catch (IOException e) {
                            LOG.error("Exception: Invalid characters were entered for the field. Try to register again");
                            System.out.println(e.getMessage());
                            registration();
                            break;
                        }
                    }
                    applicants.add(applicant);
                    LOG.info("The Applicant is registered in the general list of applicants");
                    System.out.println("Congratulations! You have registered and are on the List of Applicants \nLIST OF APPLICANTS:\n");
                    for (Applicant value : applicants) {
                        System.out.println(value);
                    }
                    Pass.giveToApplicant();
                    applicant.changeActivity(new PassExams());
                    applicant.saveState();
                }
            }
        }
    }

    public static void returnOfDocuments() throws IOException, ClassNotFoundException {
        SaveLoadFiles.load("Lesson2/src/main/resources/state.bin");
        Pass.takeFromAnApplicant();
        System.out.println("Your documents have been returned. We are saying Good Bye");
    }
}
