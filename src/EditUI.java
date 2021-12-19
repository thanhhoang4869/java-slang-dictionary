import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUI implements ActionListener {
    JFrame frame = new JFrame("Edit a slang");
    JTextField slang = new JTextField(10);
    JTextField def = new JTextField(10);
    public static String slangStr;
    public static String defStr;

    public static void main(String args[]) {
        new EditUI();
    }

    public EditUI() {
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(300, 150));
        JPanel panel = new JPanel(new GridLayout(3, 2, 0, 5));

        panel.add(new JLabel("Slang"));
        panel.add(slang);
        panel.add(new JLabel("New definition"));
        panel.add(def);

        JButton add = new JButton("Save");
        add.addActionListener(this);
        panel.add(new JLabel());
        panel.add(add);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        slangStr = slang.getText();
        defStr = def.getText();
        System.out.print(slangStr);
        System.out.print(defStr);
        if(!Dictionary.getObject().isDuplicate(slangStr)){
            JOptionPane.showMessageDialog(null,"No slang found!");
        } else{
            frame.dispose();
            Dictionary.getObject().edit(slangStr,defStr);
            JOptionPane.showMessageDialog(null,"Slang edited");
        }
    }
}
