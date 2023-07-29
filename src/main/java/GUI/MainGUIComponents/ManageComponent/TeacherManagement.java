package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.InstructorHandle;
import Model.Instructor;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeacherManagement extends JInternalFrame{
    public List<Instructor> a = new ArrayList<>();
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

            @Override
            public void actionPerformed(ActionEvent e) {
                Instructor instructor = new Instructor();
                try{
                    if(String.valueOf(insID.getText()).equals("")){
                        instructor.setID_NUMBER(-1);
                    }else{
                        int id = Integer.valueOf(insID.getText());
                        instructor.setID_NUMBER(id);
                    }
                    instructor.setName(instName.getText());
                    instructor.setPassword(insPassword.getText());
                    String dateString = insBirthday.getText();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    instructor.setBirthday(date);
                    if(insGender.getSelectedIndex() ==0)
                        instructor.setGender(false);
                    else {
                        instructor.setGender(true);
                    }
                    instructor.setEmail(insEmail.getText());
                    instructor.setPhone(insPhone.getText());
                    new InstructorHandle().INSERT(instructor);
                    System.out.println("them du lieu thanh cong");
                    refreshTable();
                } catch (NumberFormatException e1){
                    throw new RuntimeException(e1);
                }

            }

        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = table1.rowAtPoint(e.getPoint());
                String id = String.valueOf( table1.getValueAt(clickedRow, 1));
                String Name =String.valueOf( table1.getValueAt(clickedRow,2));
                insID.setText(id);
                instName.setText(Name);
                if (Boolean.parseBoolean(String.valueOf( table1.getValueAt(clickedRow, 3)))){
                    insGender.setSelectedIndex(0);
                }else {
                    insGender.setSelectedIndex(1);
                }
                insBirthday.setText(String.valueOf( table1.getValueAt(clickedRow,4)));
                insPassword.setText(String.valueOf( table1.getValueAt(clickedRow,5)));
                insEmail.setText(String.valueOf( table1.getValueAt(clickedRow,6)));
                insPhone.setText(String.valueOf( table1.getValueAt(clickedRow,7)));
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new InstructorHandle().DELETE(Integer.parseInt(insID.getText()));
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
                Instructor instructor = new Instructor();
                instructor.setID_NUMBER(Integer.parseInt(insID.getText()));
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
                new InstructorHandle().UPDATE(instructor);
                System.out.println("update du lieu thanh cong");
                refreshTable();
            }
        });
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(searchByNameCheckBox.isSelected()){
                            InstructorHandle instructorHandle = new InstructorHandle();
                            DefaultTableModel modelTeacherManage = (DefaultTableModel) table1.getModel();
                            modelTeacherManage.setRowCount(0);
                            a = null;
                            try {
                                a = instructorHandle.SELECT("SELECT * FROM instructor");
                            } catch (SQLException e1){throw new RuntimeException(e1);}

                            Iterator<Instructor> instructorIterator = a.iterator();;
                            while (instructorIterator.hasNext()){
                                Instructor instructor = instructorIterator.next();
                                if(String.valueOf(instructor.getName()).contains(searchinput.getText())){modelTeacherManage.addRow(new Object[]{true, instructor.getID_NUMBER(),instructor.getName()});}

                            }
                        }else {
                            InstructorHandle instructorHandle = new InstructorHandle();
                            DefaultTableModel modelTeacherManage = (DefaultTableModel) table1.getModel();
                            modelTeacherManage.setRowCount(0);
                            a = null;
                            try {
                                a = instructorHandle.SELECT("SELECT * FROM instructor");
                            }catch (SQLException e1){
                                throw  new RuntimeException(e1);
                            }
                            Iterator<Instructor> instructorIterator = a.iterator();
                            while (instructorIterator.hasNext()){
                                Instructor instructor = instructorIterator.next();
                                if(instructor.getID_NUMBER() == Integer.valueOf(searchinput.getText())){modelTeacherManage.addRow(new Object[]{true,instructor.getID_NUMBER(),instructor.getName()});}
                            }
                        }

                    }
                });

        tảiLạiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
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
    private JButton updateButton;
    private JButton deleteButton;
    private JButton tảiLạiButton;
    private JButton chọnẢnhButton;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JComboBox comboBox1;

    private JTextField insID;

    private JTextField instName;
    private JTextField insBirthday;
    private JTextField insEmail;
    private JTextField insPhone;
    private JPasswordField insPassword;
    private JTable table1;
    private JComboBox insGender;
    private JTextField searchinput;
    private JButton searchButton;
    private JCheckBox searchByNameCheckBox;
}
