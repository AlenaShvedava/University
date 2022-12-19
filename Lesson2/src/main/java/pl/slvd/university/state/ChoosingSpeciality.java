package pl.slvd.university.state;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.io.FileUtils.writeLines;
import static org.apache.commons.io.FileUtils.writeStringToFile;

public class ChoosingSpeciality implements Activity {
    @Override
    public void go() throws IOException {
        //FileUtils.writeLines(state,"\nI'M GOING TO CHOOSE A SPECIALTY", list);
        writeStringToFile(state, "\nI'M GOING TO CHOOSE A SPECIALTY", StandardCharsets.UTF_8, true);
    }
}
