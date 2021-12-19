import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

class DictUI extends JFrame {
    private Dictionary dict = Dictionary.getObject();
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    JPanel buttonVertical;
    JPanel mainPane;

    public static void main(String args[]){
        new DictUI();
    }

    public DictUI() {
        dict.getDict();

        setLayout(new BorderLayout());
        setTitle("Slang Dictionary 1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(600, 350));

        mainPane = MainPane();
        add(mainPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel MainPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Slang Dictionary");

        title.setVerticalAlignment(JLabel.TOP);
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        title.setPreferredSize(new Dimension(100, 50));
        title.setHorizontalAlignment(JLabel.CENTER);

        panel.add(title, BorderLayout.NORTH);

        buttonVertical = ButtonVertical();

        panel.add(buttonVertical, BorderLayout.CENTER);

        return panel;
    }

    public JPanel ButtonVertical(){
        JPanel panel = new JPanel();
        panel.setLayout( new FlowLayout(FlowLayout.CENTER, 20,20) );

        JPanel buttonV1 = ButtonVertical1();
        JPanel buttonV2 = ButtonVertical2();

        panel.add(buttonV1);
        panel.add(buttonV2);

        return panel;
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd){
                case "slang":
                    new SearchSlangUI();
                    break;
                case "def":
                    new SearchDefUI();
                    break;
                case "add":
                    new AddUI();
                    break;
                case "history":
                    new LogUI();
                    break;
                case "del":
                    new DelUI();
                    break;
                case "edit":
                    new EditUI();
                    break;
                case "today":
                    TodaySlang();
                    break;
                case "game":

                    break;
                case "reset":
                    Dictionary.getObject().getDict();
                    break;
                case "exit":

                    break;
            }
        }
    }

    public JFrame TodaySlang(){
        String todaySlang = Dictionary.getObject().random();
        ArrayList<String> defList = Dictionary.getObject().getDef(todaySlang);

        StringBuilder def = new StringBuilder();

        JLabel title = new JLabel("Today slang is");
        JLabel labelSlang = new JLabel(todaySlang);
        labelSlang.setForeground(Color.blue);
        labelSlang.setFont(new Font("Verdana", Font.PLAIN, 20));
        labelSlang.setPreferredSize(new Dimension(250, 30));

        def.append("Definition(s): ");
        for(int i=0;i<defList.size()-1;i++){
            def.append(defList.get(i));
            def.append(" / ");
        }
        def.append(defList.get(defList.size() - 1));

        JLabel labelDef = new JLabel(String.valueOf(def));

        JFrame frame =new JFrame();
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);

        frame.setTitle("Today's slang");
        frame.setPreferredSize(new Dimension(400, 200));

        panel.add(title);
        panel.add(labelSlang);
        panel.add(labelDef);

        panel.setBorder(new EmptyBorder(10,10,10,10));

        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }

    public JPanel ButtonVertical1() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 0, 5));

        JButton slangButton = new JButton("Find by slang");
        JButton defButton = new JButton("Find by definition");

        JButton addButton = new JButton("Add slang");
        JButton editButton = new JButton("Edit slang");
        JButton delButton = new JButton("Delete slang");

        panel.add(slangButton);
        panel.add(defButton);
        panel.add(addButton);
        panel.add(editButton);
        panel.add(delButton);

        slangButton.setActionCommand("slang");
        defButton.setActionCommand("def");
        addButton.setActionCommand("add");
        editButton.setActionCommand("edit");
        delButton.setActionCommand("del");

        slangButton.addActionListener(buttonClickListener);
        defButton.addActionListener(buttonClickListener);
        addButton.addActionListener(buttonClickListener);
        editButton.addActionListener(buttonClickListener);
        delButton.addActionListener(buttonClickListener);

        return panel;
    }

    public JPanel ButtonVertical2() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 0, 5));

        JButton todayButton = new JButton("Today's slang");
        JButton gameButton = new JButton("Game");
        JButton hisButton = new JButton("History");
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");

        panel.add(todayButton);
        panel.add(gameButton);
        panel.add(hisButton);
        panel.add(resetButton);
        panel.add(exitButton);

        todayButton.setActionCommand("today");
        gameButton.setActionCommand("game");
        hisButton.setActionCommand("history");
        resetButton.setActionCommand("reset");
        exitButton.setActionCommand("exit");

        todayButton.addActionListener(buttonClickListener);
        gameButton.addActionListener(buttonClickListener);
        hisButton.addActionListener(buttonClickListener);
        resetButton.addActionListener(buttonClickListener);
        exitButton.addActionListener(buttonClickListener);

        return panel;
    }
}