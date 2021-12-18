import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {
    private HashMap<String, ArrayList<String>> dict = new HashMap<>();
    private static final String FILENAME = "slang.txt";
    private static final String LOGS = "history.txt";
    static boolean working = false;

    private static Dictionary instance = null;
    private Dictionary(){
    };

    public static Dictionary getObject() {
        if (instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public void getDict(){
        try {
            dict = MyReader.getDict(FILENAME);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void printDef(ArrayList<String> def){
        for(int i=0;i<def.size(); i++){
            println("Definition "+(i+1)+": "+ def.get(i));
        }
    }

    public ArrayList<String> findSlangWithDef(ArrayList<String> list,String def,String key){
        ArrayList<String> slang = new ArrayList<String>();
        for(String i: list){
            if(i.toLowerCase().contains(def.toLowerCase())){
                slang.add(key);
                break;
            }
        }
        return slang;
    }

    public static void prompt(){
        print("Press ENTER to continue");
        Scanner keyIn = new Scanner(System.in);
        keyIn.nextLine();
    }

    public ArrayList<String> findBySlang(String slang){
        logs(slang,"Slang");

        ArrayList<String> value = dict.get(slang);
        return value;
    }

    public ArrayList<String> findByDef(String def){
        logs(def,"Definition");
        ArrayList<String> slang = new ArrayList<String>();
        for (String key : dict.keySet()) {
            ArrayList<String> value = dict.get(key);
            slang.addAll(findSlangWithDef(value,def,key));
        }
        return slang;
    }

    public void logs(String searchStr, String type){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String saveStr = dtf.format(now)+" "+type+": "+searchStr+"\n";
        try {
            MyWriter.appendLogs(LOGS,saveStr);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public ArrayList<String> vwLogs(){
        ArrayList<String> logs = new ArrayList<>();
        try {
            logs = MyReader.readLogs(LOGS);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return logs;
    }

    public boolean isDuplicate(String slang){
        ArrayList<String> def = dict.get(slang);
        if(def==null) return false;
        return true;
    }

    public void overwrite(String slang, String def){
        dict.remove(slang);
        ArrayList<String> defList = new ArrayList<>();
        defList.add(def);
        System.out.print(defList);

        dict.put(slang,defList);
        JOptionPane.showMessageDialog(null, "Overwrite successfully!");
    }

    public void addSlang(String slang, String def){
        ArrayList<String> defList = new ArrayList<>();
        defList.add(def);

        dict.put(slang,defList);
        JOptionPane.showMessageDialog(null, "Added successfully!");
    }

    public void addDefToSlang(String slang, String def){
        dict.get(slang).add(def);
        JOptionPane.showMessageDialog(null, "Definition added successfully!");
    }
}