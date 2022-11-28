package pl.slvd.university;

import java.util.Arrays;

public class ExamSheet {
    byte[] examsValue = new byte[3];

    public ExamSheet(byte[] examsValue) {
        this.examsValue = examsValue;
    }

    public ExamSheet(String examValue) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamSheet examSheet = (ExamSheet) o;
        return Arrays.equals(examsValue, examSheet.examsValue);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(examsValue);
    }

    public byte[] getExamsValue() {
        return examsValue;
    }

    public String toString(int[] exam) {
        return "ExamSheet{" +
                "examsValue=" + Arrays.toString(examsValue) +
                '}';
    }
}
