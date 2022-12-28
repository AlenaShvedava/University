package pl.solvd.university.state;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.io.FileUtils.writeStringToFile;
import static pl.solvd.university.Main.applicant;

public class Registration implements Activity, Serializable {
    @Override
    public void saveState() throws IOException {
        writeStringToFile(state, "\nI'M GOING TO REGISTER", StandardCharsets.UTF_8,true);
        SaveLoadFiles.save(applicant, "Lesson2/src/main/resources/state.bin");
    }
}
