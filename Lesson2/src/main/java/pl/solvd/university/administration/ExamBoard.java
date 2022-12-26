package pl.solvd.university.administration;

import pl.solvd.university.departments.Faculty;
import pl.solvd.university.documents.ExamSheet;
import pl.solvd.university.people.Professor;
import pl.solvd.university.state.ReturnDocuments;
import pl.solvd.university.state.SaveLoadFiles;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static pl.solvd.university.Main.*;
import static pl.solvd.university.documents.ExamSheet.grades;

public class ExamBoard {
    public static final byte MIN_PASS_SCORE = 39;

    public static void passExam() throws Exception {
        System.out.printf("\nExam Board:\nNow is time for the exams.\nExams are held in campus %d.\nYou have 3 exams. Passing score for each exam is %d \n", Faculty.valueOf(applicant.getFaculty()).getCampus(), ExamBoard.MIN_PASS_SCORE);
        System.out.println("Are you ready to take your exams? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        if ((!(answer.toUpperCase(Locale.ROOT).equals("YES")) && (!(answer.toUpperCase(Locale.ROOT).equals("NO"))))) {
            LOG.error("Exception: Something went wrong. Invalid input. Try again");
            try {
                throw new IOException("Exception: Something went wrong. Let's try again");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                passExam();
            }
        } else {
            switch (answer) {
                case "NO" -> {
                    LOG.info("The Applicant is not ready to take the exams. Let's breathe in and out and try again");
                    passExam();
                }
                case "YES" -> {
                    LOG.info("Applicant ready to take exams");
                    ExamSheet.getGrades();
                    SaveLoadFiles.load("Lesson2/src/main/resources/state.bin");
                    ExamSheet.scoreCard();
                    LOG.info("Examination grades are checked for a passing score");
                    String result = grades.stream().anyMatch(grade -> grade < MIN_PASS_SCORE) ? "Exam Board:\nUnfortunately, you did not pass the exam and did not enter the University." : "Exam Board:\nCongratulations! You have passed all the exams.";
                    System.out.println(result);
                    if (grades.stream().anyMatch(grade -> grade < MIN_PASS_SCORE)) {
                        LOG.info("The Applicant goes to return his documents");
                        applicant.changeActivity(activity, new ReturnDocuments());
                        applicant.save();
                        AdmissionsOffice.returnOfDocuments();
                    }
                }
            }
        }
    }

    public static ArrayList<Integer> rate() {
        return (ArrayList<Integer>) new Random().ints(3, 37, 100).boxed().collect(Collectors.toList());
    }
}