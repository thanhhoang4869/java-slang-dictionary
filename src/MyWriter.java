import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MyWriter {

    public static void createFileFromOrigin(String origin, String newFile) throws IOException {
        ArrayList<String> str = MyReader.getFromOrigin(origin);
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));

        for (String s : str) {
            writer.write(s);
        }
        writer.flush();
        writer.close();
    }

    public static void saveDict(HashMap<String, ArrayList<String>> dict, String fname) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fname));

        for (String s : dict.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append('`');

            ArrayList<String> defList = dict.get(s);
            int defSize = defList.size();

            if (defSize < 1) {
                sb.append(defList.get(0));
            } else {
                for (int i = 0; i < defSize - 1; i++) {
                    sb.append(defList.get(i));
                    sb.append("| ");
                }
                sb.append(defList.get(defSize - 1));
            }

            sb.append("\n");
            writer.write(String.valueOf(sb));
        }

        writer.flush();
        writer.close();
    }

    public static void appendLogs(String fname, String log) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fname, true));
        writer.write(log);
        writer.flush();
        writer.close();
    }
}
