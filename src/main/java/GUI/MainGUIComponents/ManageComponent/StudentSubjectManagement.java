package GUI.MainGUIComponents.ManageComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;


public class StudentSubjectManagement extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("StudentSubjectManagement");
        frame.setContentPane(new StudentSubjectManagement().StudentSubjectPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public StudentSubjectManagement(){
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Lớp");
        modelScoreManage.addColumn("Mã khối");
        modelScoreManage.addColumn("Mã Chủ Nhiệm");
        // ----------------------
        table1.setModel(modelScoreManage);

        // set layout (phan nay ko duoc code) -----------------------
        add(StudentSubjectPanel);

        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private JButton chọnẢnhButton;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton tảiLạiButton;
    private JPanel StudentSubjectPanel;
    private JTable table1;
    private JComboBox comboBox2;
    private JTextField textField1;
}
