import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchDefUI extends JFrame implements ActionListener {
    JScrollPane scrollPane;
    JPanel searchPane;
    JButton button;
    JTextField textField;
    DefaultListModel<String> model = new DefaultListModel<>();
    JList slangList = new JList( model );

    public static void main(String args[]){
        new SearchDefUI();
    }

    public SearchDefUI(){
        Dictionary.getObject().getDict();
        setLayout(new BorderLayout());
        setTitle("Search by definition");

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
        JLabel label = new JLabel("Enter definition");
        textField = new JTextField(15);

        button = new JButton("Search");
        button.addActionListener(this);

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
        JScrollPane s = new JScrollPane(slangList);
        return s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String def = textField.getText();
        ArrayList<String> slangList = Dictionary.getObject().findByDef(def);

        if (def == null) {
            JOptionPane.showMessageDialog(null, "No definition!");
        } else {
            model.clear();
            for(String s: slangList){
                model.addElement(s);
            }
        }

    }
}
