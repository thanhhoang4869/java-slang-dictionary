import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GameDefUI {
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    ArrayList<String> option;
    String todaySlang;
    String correct;
    JFrame frame;
    static int point = 0;

    public GameDefUI() {
        todaySlang = Dictionary.getObject().random();
        ArrayList<String> defList = Dictionary.getObject().getDef(todaySlang);
        correct = defList.get(0);

        Dictionary.getObject().remove(todaySlang);
        option = Dictionary.getObject().genDef();
        Dictionary.getObject().put(todaySlang, defList);

        int randomIndex = new Random().nextInt(4);
        option.add(randomIndex, correct);

        frame = new JFrame();
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);

        frame.setTitle("Definition Quiz!");
        frame.setPreferredSize(new Dimension(400, 300));

        JPanel button = ButtonVertical();
        panel.add(button);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel ButtonVertical() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 0, 8));

        JLabel labelSlang = new JLabel(todaySlang);
        labelSlang.setForeground(Color.blue);
        labelSlang.setFont(new Font("Verdana", Font.PLAIN, 30));
        labelSlang.setPreferredSize(new Dimension(250, 30));

        button1.setText(option.get(0));
        button2.setText(option.get(1));
        button3.setText(option.get(2));
        button4.setText(option.get(3));

        panel.add(labelSlang);

        JLabel pointLabel = new JLabel("Point: "+point);

        panel.add(pointLabel);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        button1.setActionCommand("1");
        button2.setActionCommand("2");
        button3.setActionCommand("3");
        button4.setActionCommand("4");

        button1.addActionListener(buttonClickListener);
        button2.addActionListener(buttonClickListener);
        button3.addActionListener(buttonClickListener);
        button4.addActionListener(buttonClickListener);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        return panel;
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "1":
                    if (button1.getText().equals(correct)) {
                        frame.dispose();
                        point+=5;
                        new GameDefUI();
                    } else {
                        JOptionPane.showMessageDialog(null, "Try again!");
                    }
                    break;
                case "2":
                    if (button2.getText().equals(correct)) {
                        frame.dispose();
                        point+=5;
                        new GameDefUI();
                    } else {
                        JOptionPane.showMessageDialog(null, "Try again!");
                    }
                    break;
                case "3":
                    if (button3.getText().equals(correct)) {
                        frame.dispose();
                        point+=5;
                        new GameDefUI();
                    } else {
                        JOptionPane.showMessageDialog(null, "Try again!");
                    }
                    break;
                case "4":
                    if (button4.getText().equals(correct)) {
                        frame.dispose();
                        point+=5;
                        new GameDefUI();
                    } else {
                        JOptionPane.showMessageDialog(null, "Try again!");
                    }
                    break;
            }
        }
    }
}
