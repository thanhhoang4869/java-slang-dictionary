import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DelUI implements ActionListener {
    JFrame frame = new JFrame("Delete a slang");
    static JTextField slang = new JTextField(15);

    public static void main(String args[]) {
        new DelUI();
    }

    public DelUI() {
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(400, 150));
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new FlowLayout(5, 5, 5));
        JPanel panelL = new JPanel(new FlowLayout());
        panelL.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel label = new JLabel("Enter a slang to delete");
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.TOP);
        panelL.add(label);

        frame.add(panelL, BorderLayout.NORTH);
        panel.add(slang);

        JButton del = new JButton("Delete");
        del.addActionListener(this);
        panel.add(del);

        panel.setBorder(new EmptyBorder(5, 10, 5, 10));

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String slangStr = slang.getText();
        if(!Dictionary.getObject().isDuplicate(slangStr)){
            JOptionPane.showMessageDialog(null,"No slang found!");
        } else{
            new ConfirmUI();
        }
    }
}
