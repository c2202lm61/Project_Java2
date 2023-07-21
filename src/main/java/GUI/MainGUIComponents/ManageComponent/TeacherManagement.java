package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.InstructorHandle;
import DAO.Access.TeacherClassHandle;
import Model.Instructor;
import Model.TeacherClass;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class TeacherManagement extends JInternalFrame{
    public TeacherManagement(){
        InstructorHandle instuctorHandle = new InstructorHandle();
        List<Instructor> a = null;
        try {
            a = instuctorHandle.SELECT("SELECT * FROM `instructor`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<Instructor> instructorIterator = a.iterator();
        //----------------------------------------------------
        DefaultTableModel modelTeacherManage = new DefaultTableModel();
        modelTeacherManage.addColumn("Chọn");
        modelTeacherManage.addColumn("Mã giáo viên");
        modelTeacherManage.addColumn("Họ và tên");
        modelTeacherManage.addColumn("Giới tính");
        modelTeacherManage.addColumn("Ngày sinh");
        modelTeacherManage.addColumn("Password");
        modelTeacherManage.addColumn("Email");
        modelTeacherManage.addColumn("Phone");

        //----------------------------------------------------
        while (instructorIterator.hasNext()){
            modelTeacherManage.addRow(new Object[]{true,instructorIterator.next().getID_NUMBER(),instructorIterator.next().getName(),instructorIterator.next().getGender(),instructorIterator.next().getBirthday(),instructorIterator.next().getPassword(),instructorIterator.next().getEmail(),instructorIterator.next().getPhone()});
        }
        table1.setModel(modelTeacherManage);
        //----------------------------------------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(TeacherManagentPanel);
        setVisible(true);
        //----------------------------------------------------
    }

    private JPanel TeacherManagentPanel;
    private JButton thêmButton;
    private JButton sửaButton;
    private JButton xóaButton;
    private JButton tảiLạiButton;
    private JButton chọnẢnhButton;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JComboBox comboBox1;
    private JComboBox comboBox3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JPasswordField passwordField1;
    private JTable table1;
}
