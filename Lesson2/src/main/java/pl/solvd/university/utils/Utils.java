package pl.solvd.university.utils;

import java.io.IOException;

import static pl.solvd.university.Main.LOG;

public class Utils {
    public static void checkYesNoInput(String text) throws IOException {
        if (!(text.equals("YES")) && (!(text.equals("NO")))) {
            LOG.error("Exception: Invalid characters were entered in the field");
            throw new IOException("You can only enter \"Yes\" or \"No\"");
        }
    }

    public static void checkLettersInput(String text) throws IOException {
        if (!(text.matches("[a-zA-Z]+"))) {
            LOG.error("Exception: Invalid characters were entered in the field");
            throw new IOException("Your answer must contain only letters");
        }
    }

    public static void checkDateInput(String day, String month, String year) throws IOException {
        if (!(day.matches("0?[1-9]|[12][0-9]|3[01]]") && month.matches("0?[1-9]|1[0-2]") && (year.matches("(19)[6-9][0-9]|(20)0?[0-9]")))) {
            LOG.error("Exception: Invalid characters were entered in the field");
            throw new IOException("No such date. Date entered incorrectly");
        }
    }
}
