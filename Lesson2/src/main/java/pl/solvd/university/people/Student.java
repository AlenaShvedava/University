package pl.solvd.university.people;

import pl.solvd.university.departments.Faculty;
import pl.solvd.university.state.SaveLoadFiles;

import java.io.IOException;
import java.util.List;

import static pl.solvd.university.Main.LOG;
import static pl.solvd.university.Main.applicant;

public class Student extends Applicant {
    public Student(short id, String firstName, String lastName, String dateOfBirth, String faculty, String speciality) {
        super(id, firstName, lastName, dateOfBirth, faculty, speciality);
    }

    public static void getFirstCourse(List<Applicant> approvalList) throws IOException, ClassNotFoundException {
        SaveLoadFiles.showInformationFromFile("Lesson2/src/main/resources/state.bin");
        LOG.info("Enrolled Applicants are included in the list of 1st year Students");
        List<Applicant> firstCourse = Faculty.valueOf(applicant.getFaculty()).getDean().approve(approvalList);
        System.out.println("FOR ENROLLMENT:\n");
        for (Applicant value : firstCourse) {
            System.out.println(value);
        }
    }
}
