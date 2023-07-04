package GUI;

import javax.swing.*;

public class MainGUI extends  JFrame{
    public MainGUI(){
        setTitle("MainGUI");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainGUIPanel);
        setVisible(true);
    }
    private JPanel mainGUIPanel;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextField dfedfTextField;
    private JComboBox comboBox1;
    private JButton button1;
}
