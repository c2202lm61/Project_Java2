package GUI.MainGUIComponents.ManageComponent;

<<<<<<< HEAD
import DAO.Access.ClassHandle;
import DAO.Access.StudentHandle;
import Model.MClass;
=======
import DAO.Access.StudentHandle;
>>>>>>> 2b1a9b6a986d8ce95692a5938cf4d5c13d8c50d1
import Model.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
<<<<<<< HEAD
import java.util.List;

public class StudentManagement extends JInternalFrame {

=======
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentManagement extends JInternalFrame {
    private List<Student> a = new ArrayList<>();
    public StudentManagement(){




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

        table1.setModel(modelStudentManage);

        // set layout (phan nay ko duoc code) -----------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(StudentManagementPanel);
        setVisible(true);
        //--------------------------------------------------
    }
    public void refreshTable() {
        DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table

        StudentHandle studentHandle = new StudentHandle();
                this.a = null;
                try {
                    a = studentHandle.SELECT("SELECT * FROM `student`");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        Iterator<Student> studentIterator = a.iterator();
        while (studentIterator.hasNext()){
            Student student = studentIterator.next();
            modelScoreManage.addRow(new Object[]{true,student.getID(),student.getName(),student.getGender(),student.getBirthday(),student.getAddress(), student.getPhone(),student.getClassID()});
        }


    }
>>>>>>> 2b1a9b6a986d8ce95692a5938cf4d5c13d8c50d1
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
