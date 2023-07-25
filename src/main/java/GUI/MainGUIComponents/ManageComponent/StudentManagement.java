package GUI.MainGUIComponents.ManageComponent;


import DAO.Access.ClassHandle;
import DAO.Access.StudentHandle;

import Model.MClass;
import Model.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;



import java.util.ArrayList;
import java.util.Iterator;
public class StudentManagement extends JInternalFrame {
    private List<MClass> mClassList;

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
        refreshTable();
        // set layout (phan nay ko duoc code) -----------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(StudentManagementPanel);
        setVisible(true);
        //--------------------------------------------------
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = new Student();
                student.setName(stdName.getText());
                String dateString = stdBirthday.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(dateString, formatter);
                student.setBirthday(date);

                student.setAddress(stdAddress.getText());
                student.setGender(true);
                student.setPhone(stdPhone.getText());
                student.setClassID(Integer.parseInt(String.valueOf(stdClass.getSelectedItem())));
                student.setSocialSecurtyNumber(stdSeNumber.getText());
                new StudentHandle().INSERT(student);
                refreshTable();
            }
        });
    }
    public void refreshTable() {
        //get  classitem add combobox
        this.mClassList = null;
        try {
            mClassList = new ClassHandle().SELECT("SELECT * FROM `class`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<MClass> classIterator = mClassList.iterator();

        while(classIterator.hasNext()){
            MClass mclass = classIterator.next();
            stdClass.addItem(mclass.getID());
        }

        //prindata
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


    private JPanel StudentManagementPanel;
    private JButton chọnẢnhButton;
    private JButton insertButton;
    private JButton sửaButton;
    private JButton xóaButton;
    private JButton tảiLạiButton;
    private JCheckBox chọnTấtCảCheckBox;
    private JCheckBox bỏChọnCheckBox;
    private JTextField stdID;
    private JTextField stdName;
    private JComboBox stdGender;
    private JTextField stdBirthday;
    private JTextField stdAddress;
    private JComboBox comboBox2;
    private JTable table1;
    private JScrollPane studentTableManage;
    private JComboBox stdClass;
    private JTextField stdPhone;
    private JTextField stdSeNumber;


}
