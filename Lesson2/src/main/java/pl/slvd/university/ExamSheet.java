package pl.slvd.university;

import java.util.Arrays;

public class ExamSheet {
    public static int[] grades = ExamBoard.rate();
    public static void getGrades() {
        System.out.println(Arrays.toString(grades));
    }
}