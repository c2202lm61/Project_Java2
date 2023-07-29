package GUI.MainGUIComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JPanelSearch extends JPanel{
    public static void main(String[] args) {
        JFrame frame = new JFrame("JPanelSearch");
        frame.setContentPane(new JPanelSearch().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanelSearch(){
        setLayout(new GridLayout(1,1));
        add(panel1);
    }
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTextField textField1;
    private JButton tìmButton;
    private JTable table2;
    private JPasswordField passwordField1;
    private JButton tìmButton1;
    private JTextField textField2;
    private JButton tìmButton2;
    private JTable table3;
    private JTable table4;
    private JTextField textField3;
    private JButton tìmButton4;
    private JButton tìmButton3;
    private JTextField textField4;
    private JTable SubjecTable;

    private void createUIComponents() {
        SubjecTable = new JTable();
        DefaultTableModel SubjectTableModel =new DefaultTableModel();
        SubjectTableModel.addColumn("Chọn");
        SubjectTableModel.addColumn("Mã Môn Mọc");
        SubjectTableModel.addColumn("Tên Môn Học");
        SubjectTableModel.addColumn("Tín Chỉ");
        SubjectTableModel.addColumn("ID Khối");
        SubjecTable.setModel(SubjectTableModel);
    }
}