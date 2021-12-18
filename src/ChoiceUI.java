import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceUI {
    JFrame frame = new JFrame("Message");
    JButton duplicate = new JButton("Duplicate");
    JButton ovw = new JButton("Overwrite");
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    public static void main(String args[]){
        new ChoiceUI();
    }

    public ChoiceUI(){
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(400, 150));

        JPanel panel = new JPanel(new BorderLayout());

        JPanel button = new JPanel(new FlowLayout());

        button.add(duplicate);
        Component rigidArea = Box.createRigidArea(new Dimension(8, 0));
        button.add(rigidArea);
        button.add(ovw);

        panel.add(new JLabel("Slang existed! What do you want to do?"),BorderLayout.NORTH);
        panel.add(button,BorderLayout.SOUTH);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        ovw.setActionCommand("ovw");
        duplicate.setActionCommand("duplicate");

        ovw.addActionListener(buttonClickListener);
        duplicate.addActionListener(buttonClickListener);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            String slang = AddUI.slangStr;
            String def= AddUI.defStr;
            switch (cmd) {
                case "ovw":
                    frame.dispose();
                    Dictionary.getObject().overwrite(slang,def);
                    break;
                case "duplicate":
                    frame.dispose();
                    Dictionary.getObject().addDefToSlang(slang,def);
                    break;
            }
        }
    }
}
