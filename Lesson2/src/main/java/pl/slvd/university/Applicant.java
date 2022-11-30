package pl.slvd.university;

import java.util.*;

public class Applicant {
    private short id;
    private String firstLastName;
    private String dateOfBirth;
    private String faculty;
    private String speciality;
    private byte[] values = new byte[3]; //the number of grades will receive in exams

    public Applicant() {

    }

    public Applicant(short id, String firstLastName, String dateOfBirth, String faculty, String speciality, byte[] values) {
        this.id = id;
        this.firstLastName = firstLastName;
        this.dateOfBirth = dateOfBirth;
        this.faculty = faculty;
        this.speciality = speciality;
        this.values = values;
    }

    public Applicant(short id, String firstLastName, String dateOfBirth, String faculty, String speciality) {
        this.id = id;
        this.firstLastName = firstLastName;
        this.dateOfBirth = dateOfBirth;
        this.faculty = faculty;
        this.speciality = speciality;
    }

    public short getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public byte[] getValues() {
        return values;
    }

    @Override
    public String toString() {
        return String.format("|%5s |%20s |%20s |%20s |%20s ", id + ".", firstLastName, dateOfBirth, faculty.toUpperCase(Locale.ROOT), speciality.toUpperCase(Locale.ROOT)) +
                '|';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(speciality, applicant.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speciality);
    }
}