package pl.slvd.university;

public class Deanery extends Faculty {
    String dean;

    public Deanery(String facultyName, String dean) {
        super(facultyName);
        this.dean = dean;
    }
}