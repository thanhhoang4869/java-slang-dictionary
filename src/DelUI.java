import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DelUI implements ActionListener {
    JFrame frame = new JFrame("Delete a slang");
    JTextField slang = new JTextField(15);
    JButton yes = new JButton("Yes");
    JButton no = new JButton("Cancel");
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    JFrame confirmFrame;

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

    public JFrame confirm(){
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(400, 150));

        JPanel panel = new JPanel(new BorderLayout());

        JPanel button = new JPanel(new FlowLayout());

        button.add(yes);
        Component rigidArea = Box.createRigidArea(new Dimension(8, 0));
        button.add(rigidArea);
        button.add(no);

        panel.add(new JLabel("Are you sure to delete the slang?"),BorderLayout.NORTH);
        panel.add(button,BorderLayout.SOUTH);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        yes.setActionCommand("yes");
        no.setActionCommand("no");

        yes.addActionListener(buttonClickListener);
        no.addActionListener(buttonClickListener);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String slangStr = slang.getText();
        if(!Dictionary.getObject().isDuplicate(slangStr)){
            JOptionPane.showMessageDialog(null,"No slang found!");
        } else{
            confirmFrame = confirm();
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            String slangStr = slang.getText();
            switch (cmd) {
                case "yes":
                    confirmFrame.dispose();
                    Dictionary.getObject().delete(slangStr);
                    JOptionPane.showMessageDialog(null,"Deleted");
                    break;
                case "no":
                    confirmFrame.dispose();
                    break;
            }
        }
    }
}
