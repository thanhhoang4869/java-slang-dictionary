import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyWriter {

    public static void saveLogs(String fname, ArrayList<String> logs) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fname));

        for (String s : logs) {
            writer.write(s);
        }
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
