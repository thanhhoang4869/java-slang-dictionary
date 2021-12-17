import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyWriter{
    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";

    public static void saveFile(String fname, StudentArray arr){
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(fname));

            for(Student s : StudentArray.getArr()){
                String str = ""+s.getId()+COMMA+s.getName()+COMMA+s.getGpa()+COMMA
                        +s.getAddress()+COMMA+s.getImg()+COMMA+s.getNote()+NEW_LINE;
                writer.write(str);
            }
            System.out.println("Successful!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
