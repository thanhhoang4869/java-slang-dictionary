import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class DictUI extends JFrame {
    private Dictionary dict = Dictionary.getObject();
    JPanel buttonVertical;
    JPanel mainPane;

    public static void main(String args[]){
        new DictUI();
    }

    public DictUI() {
        dict.getDict();

        setLayout(new BorderLayout());
        setTitle("Slang Dictionary 1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(600, 350));

        mainPane = MainPane();
        add(mainPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel MainPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Slang Dictionary");

        title.setVerticalAlignment(JLabel.TOP);
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        title.setPreferredSize(new Dimension(100, 50));
        title.setHorizontalAlignment(JLabel.CENTER);

        panel.add(title, BorderLayout.NORTH);

        buttonVertical = ButtonVertical();

        panel.add(buttonVertical, BorderLayout.CENTER);

        return panel;
    }

    public JPanel ButtonVertical(){
        JPanel panel = new JPanel();
        panel.setLayout( new FlowLayout(FlowLayout.CENTER, 20,20) );

        JPanel buttonV1 = ButtonVertical1();
        JPanel buttonV2 = ButtonVertical2();

        panel.add(buttonV1);
        panel.add(buttonV2);

        return panel;
    }

    public JPanel ButtonVertical1() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 0, 5));

        JButton slangButton = new JButton("Find by slang");
        JButton defButton = new JButton("Find by definition");

        JButton addButton = new JButton("Add slang");
        JButton editButton = new JButton("Edit slang");
        JButton delButton = new JButton("Delete slang");


        panel.add(slangButton);
        panel.add(defButton);

        panel.add(addButton);
        panel.add(editButton);
        panel.add(delButton);

        return panel;
    }

    public JPanel ButtonVertical2() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 0, 5));

        JButton todayButton = new JButton("Today's slang");
        JButton gameButton = new JButton("Game");
        JButton hisButton = new JButton("History");
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");

        panel.add(todayButton);
        panel.add(gameButton);

        panel.add(hisButton);
        panel.add(resetButton);
        panel.add(exitButton);

//        addButton.setActionCommand("Add");
//        editButton.setActionCommand("Edit");
//        deleteButton.setActionCommand("Delete");
//
//        addButton.addActionListener(buttonClickListener);
//        editButton.addActionListener(buttonClickListener);
//        deleteButton.addActionListener(buttonClickListener);
//
//        importButton.addActionListener(buttonClickListener);
//        exportButton.addActionListener(buttonClickListener);
//        configButton.addActionListener(buttonClickListener);
        return panel;
    }
}