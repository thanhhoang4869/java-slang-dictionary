import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchSlangUI extends JFrame implements ActionListener {
    JScrollPane scrollPane;
    JPanel searchPane;
    JButton button;
    JTextField textField;
    DefaultListModel<String> model = new DefaultListModel<>();
    JList defList = new JList( model );

    public static void main(String args[]){
        SearchSlangUI ui = new SearchSlangUI();
    }

    public SearchSlangUI(){
        Dictionary.getObject().getDict();
        setLayout(new BorderLayout());
        setTitle("Search by slang");

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
        textField = new JTextField(20);

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
        JScrollPane s = new JScrollPane(defList);
        return s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String slang = textField.getText();
        ArrayList<String> def = Dictionary.getObject().findBySlang(slang);

        if (def == null) {
            JOptionPane.showMessageDialog(null, "No definition!");
        } else {
            model.clear();
            for(String s: def){
                model.addElement(s);
            }
        }

    }
}
