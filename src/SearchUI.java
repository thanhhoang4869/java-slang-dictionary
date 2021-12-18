import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchUI extends JFrame{
    private Dictionary dict = Dictionary.getObject();
    JScrollPane scrollPane;
    JPanel searchPane;
    JButton button;

    public static void main(String args[]){
        new SearchUI();
    }

    public SearchUI(){
        dict.getDict();
        setLayout(new BorderLayout());
        setTitle("Search by slang");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(600, 350));

        searchPane = SearchPane();
        scrollPane = ScrollPane();

        JPanel panel= new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(searchPane, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel SearchPane() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter slang");
        JTextField textField = new JTextField(20);

        button = new JButton("Search");

        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));

        Component rigidArea1 = Box.createRigidArea(new Dimension(8, 0));
        Component rigidArea2 = Box.createRigidArea(new Dimension(8, 0));
        panel.add(label);
        panel.add(rigidArea1);
        panel.add(textField);
        panel.add(rigidArea2);
        panel.add(button);

        return panel;
    }

    public JScrollPane ScrollPane(){
        ArrayList<String> def = dict.findBySlang("<3");
        System.out.println(def);
        JList defList = new JList(def.toArray());

        JScrollPane s = new JScrollPane(defList);
        return s;
    }
}
