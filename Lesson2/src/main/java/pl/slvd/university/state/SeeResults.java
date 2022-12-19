package pl.slvd.university.state;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.io.FileUtils.writeStringToFile;

public class SeeResults implements Activity {
    @Override
    public void go() throws IOException {
        writeStringToFile(state, "\nI'M GOING TO SEE THE FINAL RESULTS", StandardCharsets.UTF_8,true);
    }
}
