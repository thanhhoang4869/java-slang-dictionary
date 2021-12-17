import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyWriter {

    public static void saveLogs(String fname, ArrayList<String> logs) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fname, true));

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

    public static void appendLogs(String fname, String log) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fname, true));
        writer.write(log);
        writer.flush();
        writer.close();
    }
}
