import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyReader {

    public static String[] parseKeyDef(String csvStr) {
        String[] res = null;
        if (csvStr != null) {
            res = csvStr.split("`");
        }
        return res;
    }

    public static ArrayList<String> parseDef(String csvStr) {
        String[] res = null;
        if (csvStr != null) {
            res = csvStr.split("\\| ");
        }

        ArrayList<String> a = new ArrayList<>();
        for (String i : res) {
            a.add(i);
        }
        return a;
    }

    public static HashMap<String, ArrayList<String>> getDict(String fname) throws IOException {
        HashMap<String, ArrayList<String>> dict = new HashMap<>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(fname));
        while ((line = reader.readLine()) != null) {
            String res[] = parseKeyDef(line);
            ArrayList<String> def = parseDef(res[1]);
            dict.put(res[0], def);
        }
        return dict;
    }

    public static ArrayList<String> getFromOrigin(String fname) throws IOException {
        ArrayList<String> str = new ArrayList<>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(fname));
        while ((line = reader.readLine()) != null) {
            str.add(line+"\n");
        }
        return str;
    }

    public static ArrayList<String> readLogs(String fname) throws IOException {
        ArrayList<String> logs = new ArrayList<>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(fname));
        while ((line = reader.readLine()) != null) {
            logs.add(line);
        }
        return logs;
    }
}