package pl.slvd.university.state;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.io.FileUtils.writeStringToFile;

public class ReadRules implements Activity {
    @Override
    public void go() throws IOException {
        writeStringToFile(state,"I READ THE RULES FOR VISITING THE UNIVERSITY", StandardCharsets.UTF_8, true);
    }
}
