package pl.slvd.university.state;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public interface Activity {
    File state = new File("Lesson2/src/main/resources/state.txt");
    public void go () throws IOException;


}
