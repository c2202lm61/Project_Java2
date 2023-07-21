package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.StudentHandle;
import Model.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class StudentManagement extends JInternalFrame {

    public StudentManagement(){

        StudentHandle studentHandle = new StudentHandle();
        List<Student> a = null;
        try {
            a = studentHandle.SELECT("SELECT * FROM `student`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<Student> studentIterator = a.iterator();


        // table view (phan nay ko duoc code)--------------
        DefaultTableModel modelStudentManage = new DefaultTableModel();
        modelStudentManage.addColumn("Chọn");
        modelStudentManage.addColumn("Mã Học sinh");
        modelStudentManage.addColumn("Họ và tên");
        modelStudentManage.addColumn("Giới tính");
        modelStudentManage.addColumn("Ngày sinh");
        modelStudentManage.addColumn("Dịa chỉ");
        modelStudentManage.addColumn("Phone");
        modelStudentManage.addColumn("Mã Lớp");
        // ----------------------------------------
        while (studentIterator.hasNext()){
            modelStudentManage.addRow(new Object[]{true,studentIterator.next().getID(),studentIterator.next().getName(),studentIterator.next().getGender(),studentIterator.next().getBirthday(),studentIterator.next().getAddress(), studentIterator.next().getPhone(),studentIterator.next().getClassID()});
        }
        table1.setModel(modelStudentManage);

        // set layout (phan nay ko duoc code) -----------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(StudentManagementPanel);
        setVisible(true);
        //--------------------------------------------------
    }
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



}
