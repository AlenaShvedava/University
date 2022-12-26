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
        System.out.println("\nWrite what specialty you want to study: ");
        LOG.info("The list of Specialities is displayed");
        List<Speciality> specialities = new ArrayList<>(Arrays.asList(Speciality.values()));
        for (Speciality type : Speciality.values()) {
            System.out.println(type);
        }
        Scanner input = new Scanner(System.in);
        applicant.setSpeciality(input.next().toUpperCase());
        try {
            for (int i = 0; i <= specialities.size(); ) {
                if (!(applicant.getSpeciality().equals(specialities.get(i).name()))) {
                    i++;
                } else break;
            }
        } catch (Exception e) {
            LOG.info("Exception: You entered an invalid value. Make a choice of Specialty and Faculty again");
            System.out.println("Exception: You entered an invalid value. Please review the list of Faculties again and choose your Specialty");
            chooseSpeciality();
        }
        for (Speciality type : Speciality.values())
            if (applicant.getSpeciality().equals(type.name())) {
                applicant.setFaculty(String.valueOf(type.getCategory()));
                LOG.info("Data on the chosen Faculty and Specialty are confirmed. List of exams issued");
                System.out.printf("Ok. Your faculty is %s. Your have to pass the exams: %s.\n", applicant.getFaculty(), type.getExams());
                applicant.changeActivity(activity, new Registration());
                applicant.save();
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
                    applicants.add(new Applicant((short) 1, "Mary", "Peterson", "22.10.2000", "INSTRUMENT", "concert_performer", new ArrayList<>(List.of(56, 44, 95)), applicant.getSum()));
                    applicants.add(new Applicant((short) 2, "Barbara", "Scott", "14.03.2002", "VOCAL", "vocal_singer", new ArrayList<>(List.of(66, 40, 39)), applicant.getSum()));
                    applicants.add(new Applicant((short) 3, "Andre", "Roberts", "22.10.2003", "THEATRE", "musical_director", new ArrayList<>(List.of(79, 64, 38)), applicant.getSum()));
                    applicants.add(new Applicant((short) 4, "John", "Hampton", "05.11.2000", "VOCAL", "vocal_singer", new ArrayList<>(List.of(46, 83, 58)), applicant.getSum()));
                    applicants.add(new Applicant((short) 5, "Erin", "Jones", "23.07.2001", "CHOREOGRAPHY", "choreographer", new ArrayList<>(List.of(52, 57, 45)), applicant.getSum()));
                    applicants.add(new Applicant((short) 6, "Mildred", "Blake", "18.09.2002", "CHOREOGRAPHY", "choreographer", new ArrayList<>(List.of(94, 89, 86)), applicant.getSum()));
                    applicants.add(new Applicant((short) 7, "Amanda", "Patrick", "02.05.2000", "INSTRUMENT", "concert_performer", new ArrayList<>(List.of(79, 87, 100)), applicant.getSum()));
                    applicants.add(new Applicant((short) 8, "Margaret", "Williams", "31.12.1999", "instrument", "concert_performer", new ArrayList<>(List.of(31, 44, 68)), applicant.getSum()));
                    applicants.add(new Applicant((short) 9, "Debra", "Roberts", "27.03.2003", "INSTRUMENT", "concert_performer", new ArrayList<>(List.of(100, 88, 95)), applicant.getSum()));
                    applicants.add(new Applicant((short) 10, "Julie", "Hines", "09.11.2001", "VOCAL", "vocal_singer", new ArrayList<>(List.of(56, 44, 95)), applicant.getSum()));
                    applicants.add(new Applicant((short) 11, "Chad", "Crawford", "10.02.2002", "THEATRE", "musical_director", new ArrayList<>(List.of(83, 40, 64)), applicant.getSum()));
                    applicants.add(new Applicant((short) 12, "Rhonda", "Henderson", "26.07.2002", "INSTRUMENT", "concert_performer", new ArrayList<>(List.of(77, 53, 80)), applicant.getSum()));
                    applicants.add(new Applicant((short) 13, "George", "Phillips", "13.03.2001", "VOCAL", "vocal_singer", new ArrayList<>(List.of(83, 90, 75)), applicant.getSum()));
                    applicants.add(new Applicant((short) 14, "Ruth", "White", "20.01.2000", "CHOREOGRAPHY", "choreographer", new ArrayList<>(List.of(85, 60, 58)), applicant.getSum()));
                    applicants.add(applicant);
                    LOG.info("The Applicant is registered in the general list of applicants");
                    System.out.println("Congratulations! You have registered and are on the List of Applicants \nLIST OF APPLICANTS:\n");
                    for (Applicant value : applicants) {
                        System.out.println(value);
                    }
                    Pass.give();
                    applicant.changeActivity(activity, new PassExams());
                    applicant.save();
                }
            }
        }
    }

    public static void returnOfDocuments() throws IOException, ClassNotFoundException {
        SaveLoadFiles.load("Lesson2/src/main/resources/state.bin");
        Pass.take();
        System.out.println("Your documents have been returned. We are saying Good Bye");
    }
}
