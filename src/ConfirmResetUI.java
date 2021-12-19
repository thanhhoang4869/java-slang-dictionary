import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmResetUI extends JFrame {
    JButton yes = new JButton("Yes");
    JButton no = new JButton("Cancel");
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    public ConfirmResetUI(){
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(400, 150));

        JPanel panel = new JPanel(new BorderLayout());

        JPanel button = new JPanel(new FlowLayout());

        button.add(yes);
        Component rigidArea = Box.createRigidArea(new Dimension(8, 0));
        button.add(rigidArea);
        button.add(no);

        panel.add(new JLabel("Are you sure to reset?"),BorderLayout.NORTH);
        panel.add(button,BorderLayout.SOUTH);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        yes.setActionCommand("yes");
        no.setActionCommand("no");

        yes.addActionListener(buttonClickListener);
        no.addActionListener(buttonClickListener);

        add(panel);
        pack();
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "yes":
                    dispose();
                    Dictionary.getObject().getOrigin();
                    JOptionPane.showMessageDialog(null,"Already reset!");
                    break;
                case "no":
                    dispose();
                    break;
            }
        }
    }
}
