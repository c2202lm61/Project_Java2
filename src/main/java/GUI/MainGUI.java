package GUI;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class MainGUI extends  JFrame{
    public MainGUI(){
        setTitle("MainGUI");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainGUIPanel);
        setVisible(true);
        TableColumn column1 = new TableColumn();
        column1.setHeaderValue("ID");
        table1.addColumn(column1);
    }
    private JPanel mainGUIPanel;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextField dfedfTextField;
    private JComboBox comboBox1;
    private JButton button1;
    private JTable table1;
}
