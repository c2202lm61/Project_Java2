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

    public StudentManagement(){
        DefaultTableModel modelStudentManage = new DefaultTableModel();
        modelStudentManage.addColumn("Chọn");
        modelStudentManage.addColumn("Mã giáo viên");
        modelStudentManage.addColumn("Họ và tên");
        modelStudentManage.addColumn("Mã  lớp chủ nhiệm");
        modelStudentManage.addColumn("Giới tính");
        modelStudentManage.addColumn("Số điện  thoại");
        modelStudentManage.addColumn("Ngày sinh");
        modelStudentManage.addColumn("Password");
        modelStudentManage.addColumn("Email");
        table1.setModel(modelStudentManage);

        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(StudentManagementPanel);
        setVisible(true);
    }
}
