import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
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
        setLayout(new BorderLayout());
        setTitle("Search by definition");

        setPreferredSize(new Dimension(600, 350));

        searchPane = SearchPane();
        scrollPane = ScrollPane();

        slangList.addMouseListener(mouseListener);

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
        System.out.print(slangList);

        if (slangList.size()<1) {
            JOptionPane.showMessageDialog(null, "No slang found!");
        } else {
            model.clear();
            for(String s: slangList){
                model.addElement(s);
            }
        }
    }

    MouseListener mouseListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                String slang = (String) slangList.getSelectedValue();
                ArrayList<String> defList = Dictionary.getObject().findBySlang(slang);
                String defStr="";
                for(String s: defList){
                    defStr+=s+"\n";
                }
                JOptionPane.showMessageDialog(null,defStr,"Definitions", JOptionPane.PLAIN_MESSAGE);
            }
        }
    };
}
