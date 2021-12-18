import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogUI extends JFrame{
    JScrollPane scrollPane;
    JList logs;

    public LogUI(){
        setLayout(new BorderLayout());
        setTitle("Search history");

        setPreferredSize(new Dimension(600, 350));

        JLabel label = new JLabel("View your search history");
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(new Font("Verdana", Font.PLAIN, 15));
        label.setPreferredSize(new Dimension(100, 30));
        label.setHorizontalAlignment(JLabel.CENTER);

        ArrayList<String> log = Dictionary.getObject().vwLogs();
        logs=new JList(log.toArray());

        scrollPane = ScrollPane();

        JPanel panel= new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JScrollPane ScrollPane(){
        JScrollPane s = new JScrollPane(logs);
        return s;
    }
}
