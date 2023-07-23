package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.InstructorHandle;
import Model.Instructor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeacherManagement extends JInternalFrame{
    private List<Instructor> a = new ArrayList<>();
    public TeacherManagement(){

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
        table1.setModel(modelTeacherManage);
        //----------------------------------------------------

        refreshTable();
        //----------------------------------------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(TeacherManagentPanel);
        setVisible(true);
        //----------------------------------------------------
        insertButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Instructor instructor = new Instructor();
                instructor.setName(instName.getText());
                instructor.setPassword(insPassword.getText());
                if(insGender.getSelectedIndex() ==0)
                    instructor.setGender(false);
                else {
                    instructor.setGender(true);
                }

                String dateString = insBirthday.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(dateString, formatter);
                instructor.setBirthday(date);
                instructor.setEmail(insEmail.getText());
                instructor.setPhone(insPhone.getText());
                new InstructorHandle().INSERT(instructor);
                System.out.println("them du lieu thanh cong");
            }
        });
    }
    public void refreshTable() {
        DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table

        InstructorHandle instuctorHandle = new InstructorHandle();
        this.a = null;
        try {
            a = instuctorHandle.SELECT("SELECT * FROM `instructor`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<Instructor> instructorIterator = a.iterator();
        while (instructorIterator.hasNext()){
            Instructor instructor = instructorIterator.next();
            modelScoreManage.addRow(new Object[]{true,instructor.getID_NUMBER(),instructor.getName(),instructor.getGender(),instructor.getBirthday(),instructor.getPassword(),instructor.getEmail(),instructor.getPhone()});
        }

    }
    private JPanel TeacherManagentPanel;
    private JButton insertButton;
    private JButton sửaButton;
    private JButton xóaButton;
    private JButton tảiLạiButton;
    private JButton chọnẢnhButton;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JComboBox comboBox1;
    private JComboBox insGender;
    private JTextField textField1;
    private JTextField instName;
    private JTextField insBirthday;
    private JTextField insEmail;
    private JTextField textField5;
    private JTextField insPhone;
    private JPasswordField insPassword;
    private JTable table1;
}
