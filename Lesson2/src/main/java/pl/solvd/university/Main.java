package pl.solvd.university;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.university.administration.AdmissionsOffice;
import pl.solvd.university.administration.Deanery;
import pl.solvd.university.administration.ExamBoard;
import pl.solvd.university.departments.Speciality;
import pl.solvd.university.people.Applicant;
import pl.solvd.university.people.Dean;
import pl.solvd.university.people.Professor;
import pl.solvd.university.state.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static pl.solvd.university.administration.AdmissionsOffice.applicants;
import static pl.solvd.university.administration.ExamBoard.MIN_PASS_SCORE;
import static pl.solvd.university.departments.Faculty.*;
import static pl.solvd.university.documents.ExamSheet.grades;

public class Main {
    public static final Logger LOG = LogManager.getLogger(Main.class.getName());
    public static Activity activity = new ReadRules();
    public static Applicant applicant;

    public static void main(String[] args) throws Exception {
        LOG.info("Program start");
        applicant = new Applicant();
        System.out.println("\nWELCOME TO THE UNIVERSITY OF ARTS!\nPlease read the procedure for conducting entrance examinations at the University\n");
        LOG.info("First of all, the Applicant should read about the procedure for conducting entrance examinations at the University");
        applicant.setActivity(new ReadRules());
        applicant.save();
        readUniversityRules();
        System.out.println("\nConfirm that you have read the rules (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        if ((!(answer.toUpperCase(Locale.ROOT).equals("YES")) && (!(answer.toUpperCase(Locale.ROOT).equals("NO"))))) {
            try {
                throw new IOException("Exception: You entered an invalid value. Return to the beginning");
            } catch (IOException e) {
                LOG.error("Exception: Something went wrong. Incorrect keyboard input. Return to the beginning");
                System.out.println(e.getMessage());
                main(null);
            }
        } else {
            switch (answer) {
                case "NO" -> {
                    LOG.info("The Applicant has not read the University Rules. Start from beginning and just read it");
                    main(null);
                }
                case "YES" -> {
                    LOG.info("The Applicant received information about the stages of admission to the University");
                    applicant.changeActivity(activity, new ChoosingSpeciality());
                    applicant.save();
                    AdmissionsOffice.chooseSpeciality();
                    Professor professor1 = new Professor("Mario", "Hill");
                    Professor professor3 = new Professor("Jon", "King");
                    Professor professor2 = new Professor("Lorraine", "Wilson");
                    Professor professor4 = new Professor("Matthew", "Banks");
                    Professor professor5 = new Professor("John", "Woods");
                    Professor professor6 = new Professor("Matthew", "Banks");
                    professor2 = new Dean(professor2.firstName, professor1.lastName);
                    professor3 = new Dean(professor3.firstName, professor2.lastName);
                    professor5 = new Dean(professor5.firstName, professor3.lastName);
                    professor6 = new Dean(professor6.firstName, professor4.lastName);
                    INSTRUMENT.setDean((Dean) professor5);
                    VOCAL.setDean((Dean) professor2);
                    THEATRE.setDean((Dean) professor6);
                    CHOREOGRAPHY.setDean((Dean) professor3);
                    AdmissionsOffice.registration();
                    LOG.info("Applicant preparing to take exams");
                    SaveLoadFiles.load("Lesson2/src/main/resources/state.bin");
                    List<Applicant> sortedList = applicants.stream().filter(e -> e.getSpeciality().equalsIgnoreCase(applicant.getSpeciality())).toList();
                    Professor.consult();
                    ExamBoard.passExam();
                    if (!(grades.get(0) < MIN_PASS_SCORE || grades.get(1) < MIN_PASS_SCORE || grades.get(2) < MIN_PASS_SCORE)) {
                        LOG.info("The list of applicants is transferred to the Deanery");
                        Deanery.sortByGrades(sortedList, Speciality.valueOf(applicant.getSpeciality()).getNumOfBudgetPlaces(), Speciality.valueOf(applicant.getSpeciality()).getNumOfPaidPlaces(), applicant.getSpeciality());
                        LOG.info("Admission tests completed");
                    }
                }
            }
        }
    }

    private static void readUniversityRules() throws IOException {
        try {
            File start = new File("Lesson2/src/main/resources/start.txt");
            List<String> lines = FileUtils.readLines(start, StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            LOG.error("FileNotFoundException");
            System.out.println("File not found" + e);
        }
    }
}
