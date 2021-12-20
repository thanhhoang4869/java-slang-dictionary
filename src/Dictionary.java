import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Dictionary {
    private HashMap<String, ArrayList<String>> dict = new HashMap<>();
    private static final String ORIGIN = "slang.txt";
    private static final String MYDICT = "my_slang.txt";
    private static final String LOGS = "history.txt";

    private static Dictionary instance = null;
    private Dictionary(){
    };

    public static Dictionary getObject() {
        if (instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }

    public void getDict(){
        try {
            dict = MyReader.getDict(MYDICT);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void getOrigin(){
        try {
            MyWriter.createFileFromOrigin(ORIGIN,MYDICT);
            dict = MyReader.getDict(MYDICT);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public ArrayList<String> getDef(String slang){
        return dict.get(slang);
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

    public String findByExactDef(String def){
        ArrayList<String> slang = new ArrayList<String>();
        for (String key : dict.keySet()) {
            ArrayList<String> value = dict.get(key);
            if(value.contains(def)){
                return key;
            }
        }
        return null;
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

    public void delete(String slang){
        dict.remove(slang);

        try {
            MyWriter.saveDict(dict,MYDICT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String random(){
        int randomIndex = new Random().nextInt(dict.size());
        ArrayList<String> slangList=new ArrayList<>();

        for(String i: dict.keySet()){
            slangList.add(i);
        }

        return slangList.get(randomIndex);
    }

    public ArrayList<String> randomSlangForDefGame(){
        ArrayList<String> slangList=new ArrayList<>();
        ArrayList<String> res=new ArrayList<>(3);

        for(String i: dict.keySet()){
            slangList.add(i);
        }

        for(int i=0;i<3;i++){
            int randomIndex = new Random().nextInt(slangList.size());
            res.add(slangList.get(randomIndex));
        }
        return res;
    }

    public void edit(String slang,String def){
        ArrayList<String> defList = new ArrayList<>();
        defList.add(def);
        dict.put(slang,defList);

        try {
            MyWriter.saveDict(dict,MYDICT);
        } catch (IOException e) {
            e.printStackTrace();
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

        dict.put(slang,defList);
        JOptionPane.showMessageDialog(null, "Overwrite successfully!");

        try {
            MyWriter.saveDict(dict,MYDICT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSlang(String slang, String def){
        ArrayList<String> defList = new ArrayList<>();
        defList.add(def);

        dict.put(slang,defList);
        JOptionPane.showMessageDialog(null, "Added successfully!");

        try {
            MyWriter.saveDict(dict,MYDICT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDefToSlang(String slang, String def){
        dict.get(slang).add(def);
        JOptionPane.showMessageDialog(null, "Definition added successfully!");

        try {
            MyWriter.saveDict(dict,MYDICT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> genDef(){
        ArrayList<String> res = new ArrayList<>(3);

        for(int i=0;i<3;i++){
            String slang = random();
            ArrayList<String> def = dict.get(slang);
            for(String j:def){
                res.add(j);
            }
        }

        if(res.size()>3){
            for(int i = 3;i<res.size();i++){
                res.remove(i);
            }
        }

        return res;
    }

    public String genDefNoFixed(){
        ArrayList<String> res = new ArrayList<>();

        for(String slang : dict.keySet()){
            ArrayList<String> defList = dict.get(slang);
            for(String def : defList){
                res.add(def);
            }
        }

        int randomIndex = new Random().nextInt(res.size());
        return res.get(randomIndex);
    }

    public void remove(String slang){
        dict.remove(slang);
    }

    public void put(String slang,ArrayList<String> def){
        dict.put(slang,def);
    }
}