package pl.slvd.university;

import java.util.*;

public class AdmissionsOffice {

    public static void registration(String getSpecialty, String getSpecialty2) { //the registration method accepts the applicant and the values of the faculty and specialty chosen by him
        System.out.printf("Apply to faculty: %s, speciality: %s? \n", getSpecialty, getSpecialty2);
        Scanner faculty = new Scanner(System.in);
        String chooseDone = faculty.next();
        if ("YES".equals(chooseDone.toUpperCase(Locale.ROOT))) {
            System.out.println("Enter your first and last name \n"); //enter personal data to complete the registration
            String[] name = new String[2];
            name[0] = faculty.next();
            name[1] = faculty.next();
            String firstLastName = name[0] + " " + name[1];
            System.out.println("Enter your date of birth\n");
            String birthday = faculty.next();

            //the applicant has collected all the documents about himself and is ready for registration
            Applicant applicant = new Applicant((short) 15, "Nancy Thompson", "07.07.2002", getSpecialty2, getSpecialty);

            //Display the list of registered ones on the screen, enter our applicant in the last line
            List<Applicant> applicants = new ArrayList<>();
            applicants.add(new Applicant((short) 1, "Mary Peterson", "22.10.2000", "INSTRUMENT", "concert_performer", new byte[]{56, 44, 95}));
            applicants.add(new Applicant((short) 2, "Barbara Scott", "14.03.2002", "VOCAL", "vocal_singer", new byte[]{66, 40, 39}));
            applicants.add(new Applicant((short) 3, "Andre Roberts", "22.10.2003", "THEATRE", "musical_director", new byte[]{79, 64, 38}));
            applicants.add(new Applicant((short) 4, "John Hampton", "05.11.2000", "VOCAL", "vocal_singer", new byte[]{46, 83, 58}));
            applicants.add(new Applicant((short) 5, "Erin Jones", "23.07.2001", "CHOREOGRAPHY", "choreographer", new byte[]{52, 57, 45}));
            applicants.add(new Applicant((short) 6, "Mildred Blake", "18.09.2002", "CHOREOGRAPHY", "choreographer", new byte[]{94, 89, 86}));
            applicants.add(new Applicant((short) 7, "Amanda Patrick", "02.05.2000", "INSTRUMENT", "concert_performer", new byte[]{79, 87, 100}));
            applicants.add(new Applicant((short) 8, "Margaret Williams", "31.12.1999", "instrument", "concert_performer", new byte[]{31, 44, 68}));
            applicants.add(new Applicant((short) 9, "Debra Roberts", "27.03.2003", "INSTRUMENT", "concert_performer", new byte[]{100, 88, 95}));
            applicants.add(new Applicant((short) 10, "Julie Hines", "09.11.2001", "VOCAL", "vocal_singer", new byte[]{56, 44, 95}));
            applicants.add(new Applicant((short) 11, "Chad Crawford", "10.02.2002", "THEATRE", "musical_director", new byte[]{83, 40, 64}));
            applicants.add(new Applicant((short) 12, "Rhonda Henderson", "26.07.2002", "INSTRUMENT", "concert_performer", new byte[]{77, 53, 80}));
            applicants.add(new Applicant((short) 13, "George Phillips", "13.03.2001", "VOCAL", "vocal_singer", new byte[]{83, 90, 75}));
            applicants.add(new Applicant((short) 14, "Ruth White", "20.01.2000", "CHOREOGRAPHY", "choreographer", new byte[]{85, 60, 58}));
            applicants.add(new Applicant(applicant.getId(), firstLastName, birthday, applicant.getFaculty(), applicant.getSpeciality(), applicant.getValues()));

            System.out.println("Congratulations! You have registered and are on the list of applicants \n");
            for (Applicant value : applicants) {
                System.out.println(value);
            }
        }
    }
}