import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class GameDefUI {
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JFrame frame;

    public static void main(String[] args){
        new GameDefUI();
    }

    public GameDefUI(){
//        String todaySlang = Dictionary.getObject().random();
//        ArrayList<String> defList = Dictionary.getObject().getDef(todaySlang);

        JLabel label = new JLabel("Select the correct slang :D");
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(new Font("Verdana", Font.PLAIN, 15));
        label.setPreferredSize(new Dimension(100, 30));
        label.setHorizontalAlignment(JLabel.CENTER);

        JLabel labelSlang = new JLabel("<2");
        labelSlang.setForeground(Color.blue);
        labelSlang.setFont(new Font("Verdana", Font.PLAIN, 30));
        labelSlang.setPreferredSize(new Dimension(250, 30));

        frame = new JFrame();
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);

        frame.setTitle("Slang Quiz!");
        frame.setPreferredSize(new Dimension(400, 250));

        panel.add(label);
        panel.add(labelSlang);

        JPanel button = ButtonVertical();
        panel.add(button);

        panel.setBorder(new EmptyBorder(10,10,10,10));

        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel ButtonVertical() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 0, 8));

        button1.setText("hihi");
        button2.setText("hihi");
        button3.setText("hihi");
        button4.setText("hihi");


        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

//        slangButton.setActionCommand("slang");
//        defButton.setActionCommand("def");
//        addButton.setActionCommand("add");
//        editButton.setActionCommand("edit");
//        delButton.setActionCommand("del");
//
//        slangButton.addActionListener(buttonClickListener);
//        defButton.addActionListener(buttonClickListener);
//        addButton.addActionListener(buttonClickListener);
//        editButton.addActionListener(buttonClickListener);
//        delButton.addActionListener(buttonClickListener);

        panel.setBorder(new EmptyBorder(10,10,10,10));

        return panel;
    }
}
