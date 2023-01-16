package pl.solvd.university.departments;

import pl.solvd.university.people.Dean;

public enum Faculty {
    INSTRUMENT(3),
    VOCAL(1),
    THEATRE(4),
    CHOREOGRAPHY(2);

    private Dean dean;
    private final int campus;

    Faculty(int campus) {
        this.campus = campus;
    }

    public void setDean(Dean dean) {
        this.dean = dean;
    }

    public Dean getDean() {
        return dean;
    }

    public int getCampus() {
        return campus;
    }
}
