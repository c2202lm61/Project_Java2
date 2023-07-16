package GUI.MainGUIComponents.ManageComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentManagement extends JInternalFrame {
    private JPanel StudentManagementPanel;
    private JButton chọnẢnhButton;
    private JButton thêmButton;
    private JButton sửaButton;
    private JButton xóaButton;
    private JButton tảiLạiButton;
    private JCheckBox chọnTấtCảCheckBox;
    private JCheckBox bỏChọnCheckBox;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox2;
    private JTable table1;
    private JScrollPane studentTableManage;

    public static void main(String[] args) {
        JFrame frame = new JFrame("StudentManagement");
        frame.setContentPane(new StudentManagement().StudentManagementPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public StudentManagement(){
        DefaultTableModel modelStudentManage = new DefaultTableModel();
        modelStudentManage.addColumn("Chọn");
        modelStudentManage.addColumn("Mã Học sinh");
        modelStudentManage.addColumn("Họ và tên");
        modelStudentManage.addColumn("Giới tính");
        modelStudentManage.addColumn("Ngày sinh");
        modelStudentManage.addColumn("Dịa chỉ");
        modelStudentManage.addColumn("Mã Lớp");
        table1.setModel(modelStudentManage);

        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(StudentManagementPanel);
        setVisible(true);
    }
}
