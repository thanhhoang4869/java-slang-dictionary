import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Dictionary {
    private static HashMap<String, ArrayList<String>> dict = new HashMap<>();
    private static final String FILENAME = "slang.txt";
    private static final String LOGS = "history.txt";
    static boolean working = false;
    private static ArrayList<String> logsList = new ArrayList<>();

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

    public static void findBySlang(){
        print("Enter slang: ");
        Scanner scanner = new Scanner(System.in);
        String slang = scanner.nextLine();

        logs(slang,"Slang");

        ArrayList<String> value = dict.get(slang);

        if(value==null){
            println("No definition found");
        } else{
            printDef(value);
        }
    }

    public static void findByDef(){
        print("Enter definition: ");
        Scanner scanner = new Scanner(System.in);
        String def = scanner.nextLine();

        logs(def,"Definition");

        for (String key : dict.keySet()) {
            ArrayList<String> value = dict.get(key);
            printSlangWithDef(value,def,key);
        }
    }

    public static void logs(String searchStr, String type){
        String saveStr = type+": "+searchStr+"\n";
        logsList.add(saveStr);

    }

    public static void printLogs(String historyFile){

    }

    public static void menu() {
        println("--------------------------------------");
        println("|  01. Find by slang word            |");
        println("|  02. Find by slang definition      |");
        println("|  03. Search history                |");
        println("|  04. Add new slang word            |");
        println("|  05. Edit slang word               |");
        println("|  06. Delete slang word             |");
        println("|  07. Today's slang word            |");
        println("|  08. Reset dictionary              |");
        println("|  09. Definition-guess Game         |");
        println("|  10. Slang-word-guess Game         |");
        println("|  11. Exit                          |");
        println("--------------------------------------");

        print("Option: ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        while (option < 1 || option > 10) {
            print("Enter valid number: ");
            option = scanner.nextInt();
        }

        switch (option) {
            case 1:
                findBySlang();
                prompt();
                break;
            case 2:
                findByDef();
                prompt();
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:
                working = false;
        }
    }

    public static void main(String args[]) {
        working = true;
        System.out.println("Loading data...");
        getDict();
        while(working)
            menu();
    }
}

