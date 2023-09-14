package GUI.MainGUIComponents.ManageComponent;


import Controllers.Authorization.Authorization;
import Controllers.ExportData;
import Controllers.SortA_Z;
import Controllers.SortZ_A;
import Controllers.Validation;
import DAO.Access.ClassHandle;
import DAO.Access.StudentHandle;

import DAO.JDBCDriver;
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
import java.util.Collections;
import java.util.List;



import java.util.ArrayList;
import java.util.Iterator;
public class StudentManagement extends JInternalFrame {
    public List<MClass> mClassList;

    private List<Student> a = new ArrayList<>();
    public StudentManagement(){

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
                if(!Authorization.getPermisionForStudent()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                Student student = new Student();
                try {
                    if(String.valueOf(stdID.getText()).equals("")){
                        student.setID(-1);
                    }else {
                        int id = Integer.valueOf(stdID.getText());
                      student.setID(id);
                    }
                    if(!Validation.isFullName(stdName.getText())){JOptionPane.showMessageDialog(null,"Tên không hợp lệ"); return;}
                        student.setName(stdName.getText());
                String dateString = stdBirthday.getText();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    student.setBirthday(date);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Ngày sinh  không hợp lệ");
                    return;
                }


                    if(stdGender.getSelectedIndex() == 1){
                    student.setGender(true);
                }else {
                    student.setGender(false);
                }
                    if (stdAddress.getText().length() == 0){JOptionPane.showMessageDialog(null,"Địa chỉ không hợp lệ"); return;}
                student.setAddress(stdAddress.getText());
                    if (stdPhone.getText().length() != 8){JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ"); return;}
                student.setPhone(stdPhone.getText());
                student.setClassID(Integer.parseInt(String.valueOf(stdClass.getSelectedItem())));
                    if (stdSeNumber.getText().length() != 8){JOptionPane.showMessageDialog(null,"Số điện thoại phụ huynh không hợp lệ"); return;}
                student.setSocialSecurtyNumber(stdSeNumber.getText());
                new StudentHandle().INSERT(student);
                JOptionPane.showMessageDialog(null,"Thêm học sinh thành công");
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
                if(!Authorization.getPermisionForStudent()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                try {
                    Integer.parseInt(stdID.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"ID học sinh không hợp lệ");
                    return;
                }
                try {
                    JDBCDriver.SetQuery("DELETE score_student FROM `subject_student` INNER JOIN score_student ON subject_student.Subject_student_id = score_student.ss_id WHERE subject_student.student_id = "+stdID.getText());
                    JDBCDriver.SetQuery("DELETE FROM `subject_student` WHERE student_id = "+stdID.getText());
                    Boolean a = JDBCDriver.SetQuery("DELETE FROM `student` WHERE `Student_id` = "+stdID.getText());
                    if (a)
                        JOptionPane.showMessageDialog(null,"Xóa học sinh thành công");
                    else
                        JOptionPane.showMessageDialog(null,"Xóa học sinh không thành công");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Bạn không thể xóa do điểm của  học sinh đang  tồn tại");
                }
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
                if(!Authorization.getPermisionForStudent()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }

                Student student = new Student();
                try {
                    Integer.parseInt(stdID.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"ID học sinh không hợp lệ");
                    return;
                }
                student.setID(Integer.parseInt(stdID.getText()));
                if(!Validation.isFullName(stdName.getText())){JOptionPane.showMessageDialog(null,"Tên không hợp lệ"); return;};
                student.setName(stdName.getText());
                String dateString = stdBirthday.getText();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    student.setBirthday(date);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Ngày sinh  không hợp lệ");
                    return;
                }
                if(stdGender.getSelectedIndex() == 1){
                    student.setGender(true);
                }else {
                    student.setGender(false);
                }
                if (stdAddress.getText().length() == 0){JOptionPane.showMessageDialog(null,"Địa chỉ không hợp lệ"); return;}
                student.setAddress(stdAddress.getText());
                if (stdPhone.getText().length() != 8){JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ"); return;}
                student.setPhone(stdPhone.getText());
                student.setClassID(Integer.parseInt(String.valueOf(stdClass.getSelectedItem())));
                if (stdSeNumber.getText().length() != 8){JOptionPane.showMessageDialog(null,"Số điện thoại phụ huynh không hợp lệ"); return;}
                student.setSocialSecurtyNumber(stdSeNumber.getText());
                new StudentHandle().UPDATE(student);
                JOptionPane.showMessageDialog(null,"Cập nhật thông tin thành công");
                refreshTable();
            }
        });
        subjectStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  StudentSubjectManagement studentSubjectManagement = new StudentSubjectManagement(Integer.parseInt(stdID.getText()));
                JOptionPane.showMessageDialog(null,"Chức năng đang bảo trì");
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
                DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                modelScoreManage.setRowCount(0); // Clear existing data in the table
                StudentHandle studentHandle = new StudentHandle();
                if(searchByNameCheckBox.isSelected()){
                    Iterator<Student> studentIterator = a.iterator();
                    while (studentIterator.hasNext()){
                        Student student = studentIterator.next();
                        if(student.getName().contains(String.valueOf(searchinput.getText()))){
                            modelScoreManage.addRow(new Object[]{true,student.getID(),student.getName(),student.getGender(),student.getBirthday(),student.getAddress(), student.getPhone(),student.getClassID()});
                        }
                    }
                }else {
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
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox2.getSelectedIndex()==0) {
                    Collections.sort(a, new SortA_Z());
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0); // Clear existing data in the table

                    Iterator<Student> studentIterator = a.iterator();
                    while (studentIterator.hasNext()) {
                        Student student = studentIterator.next();
                        String gender = (student.getGender()) ? "Nam" : "Nữ";
                        modelScoreManage.addRow(new Object[]{true, student.getID(), student.getName(), gender, student.getBirthday(), student.getAddress(), student.getPhone(), student.getClassID()});
                    }
                }
                else if (comboBox2.getSelectedIndex()==1){
                    Collections.sort(a, new SortZ_A());
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0); // Clear existing data in the table

                    Iterator<Student> studentIterator = a.iterator();
                    while (studentIterator.hasNext()) {
                        Student student = studentIterator.next();
                        String gender = (student.getGender()) ? "Nam" : "Nữ";
                        modelScoreManage.addRow(new Object[]{true, student.getID(), student.getName(), gender, student.getBirthday(), student.getAddress(), student.getPhone(), student.getClassID()});
                    }
                }
            }
        });
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ExportData.exportData(table1,1);
            }
        });
        checkAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                modelStudentManage.setValueAt(true,0,0);
            }
        });
        checkAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(checkAll.isSelected()){
                    System.out.println("Đã chọn");
                }else{
                    System.out.println("Chưa chọn");
                }
            }
        });
    }
    public void refreshTable() {
        //get  classitem add combobox

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
    private JCheckBox checkAll;
    private JCheckBox chọnCheckBox;
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
    private JButton exportButton;


    private void createUIComponents() {
        table1 = new JTable() {

            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
    }
}
