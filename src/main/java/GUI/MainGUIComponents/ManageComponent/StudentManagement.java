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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;



import java.util.ArrayList;
import java.util.Iterator;
public class StudentManagement extends JInternalFrame {
    public List<MClass> mClassList;

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
                try {
                    if(String.valueOf(stdID.getText()).equals("")){
                        student.setID(-1);
                    }else {
                        int id = Integer.valueOf(stdID.getText());
                      student.setID(id);
                    }
                student.setName(stdName.getText());
                String dateString = stdBirthday.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(dateString, formatter);
                student.setBirthday(date);
                if(stdGender.getSelectedIndex() == 1){
                    student.setGender(true);
                }else {
                    student.setGender(false);
                }
                student.setAddress(stdAddress.getText());
                student.setPhone(stdPhone.getText());
                student.setClassID(Integer.parseInt(String.valueOf(stdClass.getSelectedItem())));
                student.setSocialSecurtyNumber(stdSeNumber.getText());
                new StudentHandle().INSERT(student);
                System.out.println("them du lieu thanh cong");
                refreshTable();
            }catch (NumberFormatException e1){
                    throw new RuntimeException(e1);
                }}
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = table1.rowAtPoint(e.getPoint());
                stdID.setText(String.valueOf( table1.getValueAt(clickedRow,1)));
                stdName.setText(String.valueOf( table1.getValueAt(clickedRow,2)));
                stdBirthday.setText(String.valueOf( table1.getValueAt(clickedRow,4)));
                stdAddress.setText(String.valueOf( table1.getValueAt(clickedRow,5)));
                stdPhone.setText(String.valueOf( table1.getValueAt(clickedRow,6)));
                stdSeNumber.setText(String.valueOf( table1.getValueAt(clickedRow,6)));
                stdClass.setSelectedItem(table1.getValueAt(clickedRow,7));
                stdGender.setSelectedItem(table1.getValueAt(clickedRow,3));
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentHandle().DELETE(Integer.parseInt(stdID.getText()));

                refreshTable();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                Student student = new Student();
                student.setID(Integer.parseInt(stdID.getText()));
                student.setName(stdName.getText());
                String dateString = stdBirthday.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(dateString, formatter);
                student.setBirthday(date);
                if(stdGender.getSelectedIndex() == 1){
                    student.setGender(true);
                }else {
                    student.setGender(false);
                }
                student.setAddress(stdAddress.getText());
                student.setPhone(stdPhone.getText());
                student.setClassID(Integer.parseInt(String.valueOf(stdClass.getSelectedItem())));
                student.setSocialSecurtyNumber(stdSeNumber.getText());
                new StudentHandle().UPDATE(student);
                refreshTable();
            }
        });
        subjectStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentSubjectManagement studentSubjectManagement = new StudentSubjectManagement(Integer.parseInt(stdID.getText()));

            }
        });
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchByNameCheckBox.isSelected()){
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0); // Clear existing data in the table

                    StudentHandle studentHandle = new StudentHandle();
                    a = null;
                    try {
                        a = studentHandle.SELECT("SELECT * FROM `student`");
                    } catch (SQLException e2) {
                        throw new RuntimeException(e2);
                    }
                    Iterator<Student> studentIterator = a.iterator();
                    while (studentIterator.hasNext()){
                        Student student = studentIterator.next();
                        if(student.getName().contains(String.valueOf(searchinput.getText()))){
                            modelScoreManage.addRow(new Object[]{true,student.getID(),student.getName(),student.getGender(),student.getBirthday(),student.getAddress(), student.getPhone(),student.getClassID()});
                        }
                    }
                }else {
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0); // Clear existing data in the table

                    StudentHandle studentHandle = new StudentHandle();
                    a = null;
                    try {
                        a = studentHandle.SELECT("SELECT * FROM `student`");
                    } catch (SQLException e2) {
                        throw new RuntimeException(e2);
                    }
                    Iterator<Student> studentIterator = a.iterator();
                    while (studentIterator.hasNext()){
                        Student student = studentIterator.next();
                        if(student.getID() == Integer.valueOf(searchinput.getText())){
                            modelScoreManage.addRow(new Object[]{true,student.getID(),student.getName(),student.getGender(),student.getBirthday(),student.getAddress(), student.getPhone(),student.getClassID()});
                        }
                    }
                }
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
            String gender = (student.getGender()) ? "Nam":"Nữ";
            modelScoreManage.addRow(new Object[]{true,student.getID(),student.getName(),gender,student.getBirthday(),student.getAddress(), student.getPhone(),student.getClassID()});
        }


    }


    private JPanel StudentManagementPanel;
    private JButton reloadButton;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
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
    private JButton subjectStudentButton;
    private JTextField searchinput;
    private JButton Search;
    private JCheckBox searchByNameCheckBox;


}
