import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {
    private static HashMap<String, ArrayList<String>> dict = new HashMap<>();
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

    public static void getDict(){
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

    public static void printSlangWithDef(ArrayList<String> list,String def,String key){
        for(String i: list){
            if(i.toLowerCase().contains(def.toLowerCase())){
                println(key);
                printDef(dict.get(key));
                println("===============");
                break;
            }
        }
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

    public void findByDef(){
        print("Enter definition: ");
        Scanner scanner = new Scanner(System.in);
        String def = scanner.nextLine();

        logs(def,"Definition");

        for (String key : dict.keySet()) {
            ArrayList<String> value = dict.get(key);
            printSlangWithDef(value,def,key);
        }
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

    public void printLogs(){
        println("Search history");
        try {
            ArrayList<String> logs = MyReader.readLogs(LOGS);
            for(String log: logs){
                println(log);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public boolean isDuplicate(String slang){
        ArrayList<String> def = dict.get(slang);
        if(def==null) return false;
        return true;
    }

    public void addSlangUtil(String slang){
        ArrayList<String> defList = new ArrayList<String>();
        print("How many definition?: ");
        Scanner scanner = new Scanner(System.in);
        int numDef = scanner.nextInt();

        for(int i =0;i<numDef; i++){
            print("Definition "+(i+1)+": ");
            scanner = new Scanner(System.in);
            String def = scanner.nextLine();
            defList.add(def);
        }

        dict.put(slang,defList);
        println("Added successfully!");
    }

    public void addDefToSlang(String slang){
        print("How many definition you want to add?: ");
        Scanner scanner = new Scanner(System.in);
        int numDef = scanner.nextInt();

        for(int i =0;i<numDef; i++){
            print("Definition: ");
            scanner = new Scanner(System.in);
            String def = scanner.nextLine();
            dict.get(slang).add(def);
        }
        println("Added successfully!");
    }

    public void addSlang(){
        print("Enter new slang: ");
        Scanner scanner = new Scanner(System.in);
        String slang = scanner.nextLine();

        if(isDuplicate(slang)){
            println("Slang existed, do you want to: ");
            println("1.Duplicate slang");
            println("2.Overwrite slang");
            print("Option: ");
            scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            while (option != 1 && option != 2) {
                print("Enter valid option: ");
                option = scanner.nextInt();
            }
            if(option==1){
                addDefToSlang(slang);
                return;
            } else{
                dict.remove(slang);
            }
        }
        addSlangUtil(slang);
    }

//    public static void main(String args[]) {
//        working = true;
//        System.out.println("Loading data...");
//        getDict();
//        while(working)
//            menu();
//    }
}