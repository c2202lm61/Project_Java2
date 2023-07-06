package GUI;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame{
    public JTabbedPane jTabbedPane =  new JTabbedPane();
    JComboBox comboBox = new JComboBox<>();
    JPanel tab1 = new JPanel();
    JPanel tab2 = new JPanel();
    JPanel tab3 = new JPanel();
    JPanel tab4 = new JPanel();

    public MainGUI(){
        setTitle("MainGUI");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        tab1.add(new JLabel("tab1"));
        tab2.add(new JLabel("tab2"));
        tab3.add(new JLabel("tab3"));
        tab4.add(new JLabel("tab4"));
        tab4.setLayout(new BorderLayout());
        //
        JPanel  panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2,3));
        JLabel label = new JLabel("Tìm kiếm");
        panel1.add(label);
        panel1.add(new JLabel("df"));
        panel1.add(new JComboBox<>());
        tab4.add(panel1,BorderLayout.NORTH);
        //declare tab
        jTabbedPane.addTab("Trang chủ",tab1);
        jTabbedPane.addTab("Quản lý",tab2);
        jTabbedPane.addTab("Xem điểm",tab3);
        jTabbedPane.addTab("Tìm kiếm",tab4);
        setContentPane(jTabbedPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
