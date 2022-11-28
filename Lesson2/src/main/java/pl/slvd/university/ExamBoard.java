package pl.slvd.university;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

public class ExamBoard {
    static final byte MIN = 39;
    static String result;

    static void passExam() {
        System.out.printf("It's time for the exams. You have 3 exams. Passing score for each exam is %d \n", ExamBoard.MIN);
        getValue(); //asking for grades for our exams

    }

    @NotNull
    @Contract(" -> new")
    public static byte[] getValue() {
        System.out.println("Your exam grades (0-100): \n");
        int[] exam = new Random(100).ints(3,0,100).toArray();

        ExamSheet examSheet = new ExamSheet(Arrays.toString(exam));
        System.out.println(examSheet.toString(exam));

        //result = exam[0] < MIN || exam[1] < MIN || exam[2] < MIN ? "Unfortunately, you did not pass the exam and did not enter the University.\n" : "Congratulations! You have passed all the exams.\n";
        return new byte[]{(byte) exam[0], (byte) exam[1], (byte) exam[2]};
    }
}