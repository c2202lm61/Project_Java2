package GUI.MainGUIComponents.ManageComponent;

import Controllers.*;
import Controllers.Authorization.Authorization;
import DAO.Access.InstructorHandle;
import DAO.JDBCDriver;
import Model.Instructor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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
            String dateString = insBirthday.getText();
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Authorization.getPermisionForTeacher()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if(!Validation.isFullName(instName.getText())){JOptionPane.showMessageDialog(null,"Tên không hợp lệ"); return;};
                String dateString = insBirthday.getText();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dateString, formatter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Ngày sinh  không hợp lệ");
                    return;
                }
                if (!Validation.isEmail(insEmail.getText())){
                    JOptionPane.showMessageDialog(null,"Email  không hợp lệ");
                    return;
                }
                if (insPhone.getText().length() != 8){
                    JOptionPane.showMessageDialog(null,"Số điện thoại  không hợp lệ");
                    return;
                }
                if (!Validation.isStrongPassword(insPassword.getText())){
                    JOptionPane.showMessageDialog(null,"Mật khẩu  không hợp lệ");
                    return;
                };
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
                    JOptionPane.showMessageDialog(null,"Thêm  giáo viên thành công");
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
                insGender.setSelectedItem(String.valueOf( table1.getValueAt(clickedRow, 3)));

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
                if(!Authorization.getPermisionForTeacher()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                try {
                    ResultSet rs = JDBCDriver.ExecQuery("SELECT * FROM instructor JOIN class ON instructor.ID_NUMBER = class.ID_manager WHERE instructor.ID_NUMBER = "+insID.getText());
                    while (rs.next()){
                        String a ="";
                        ResultSet rs1 = JDBCDriver.ExecQuery("SELECT * FROM instructor JOIN class ON instructor.ID_NUMBER = class.ID_manager WHERE instructor.ID_NUMBER = "+insID.getText());
                        while (rs1.next()){
                             a += rs1.getString("class_code")+",";
                        }
                        if (a.length() > 0) {
                            a = a.substring(0, a.length() - 1);
                        }
                        JOptionPane.showMessageDialog(null,"Bạn cần thay  đổi giáo viên chủ nhiệm cho các lớp "+a+" để xóa giáo viên này!");
                        JDBCDriver.DestroyConnection();
                        return;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (!Validation.isNumeric(insID.getText())){
                    JOptionPane.showMessageDialog(null,"ID  không hợp lệ");
                    return;
                }
                new InstructorHandle().DELETE(Integer.parseInt(insID.getText()));
                JOptionPane.showMessageDialog(null,"Xóa giáo viên thành công");
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
                if(!Authorization.getPermisionForTeacher()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if (!Validation.isNumeric(insID.getText())){
                    JOptionPane.showMessageDialog(null,"ID  không hợp lệ");
                    return;
                }
                if(!Validation.isFullName(instName.getText())){JOptionPane.showMessageDialog(null,"Tên không hợp lệ"); return;};
                String dateString = insBirthday.getText();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dateString, formatter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Ngày sinh  không hợp lệ");
                    return;
                }
                if (!Validation.isEmail(insEmail.getText())){
                    JOptionPane.showMessageDialog(null,"Email  không hợp lệ");
                    return;
                }
                if (insPhone.getText().length() != 8){
                    JOptionPane.showMessageDialog(null,"Số điện thoại  không hợp lệ");
                    return;
                }
                if (!Validation.isStrongPassword(insPassword.getText())){
                    JOptionPane.showMessageDialog(null,"Mật khẩu  không hợp lệ");
                    return;
                };
                Instructor instructor = new Instructor();
                instructor.setID_NUMBER(Integer.parseInt(insID.getText()));
                instructor.setName(instName.getText());
                instructor.setPassword(insPassword.getText());
                if(insGender.getSelectedIndex() ==0)
                    instructor.setGender(false);
                else {
                    instructor.setGender(true);
                }


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(dateString, formatter);
                instructor.setBirthday(date);
                instructor.setEmail(insEmail.getText());
                instructor.setPhone(insPhone.getText());
                new InstructorHandle().UPDATE(instructor);
                JOptionPane.showMessageDialog(null,"Cập nhật giáo viên thành công");
                refreshTable();
            }
        });
        searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        InstructorHandle instructorHandle = new InstructorHandle();
                        DefaultTableModel modelTeacherManage = (DefaultTableModel) table1.getModel();
                        modelTeacherManage.setRowCount(0);
                        if(searchByNameCheckBox.isSelected()){
                            Iterator<Instructor> instructorIterator = a.iterator();;
                            while (instructorIterator.hasNext()){
                                Instructor instructor = instructorIterator.next();
                                if(String.valueOf(instructor.getName()).contains(searchinput.getText())){modelTeacherManage.addRow(new Object[]{true,instructor.getID_NUMBER(),instructor.getName(),instructor.getPassword(),instructor.getBirthday(),instructor.getGender(),instructor.getPhone(),instructor.getEmail()});}
                            }
                        }else {
                            Iterator<Instructor> instructorIterator = a.iterator();
                            while (instructorIterator.hasNext()){
                                Instructor instructor = instructorIterator.next();
                                if(instructor.getID_NUMBER() == Integer.valueOf(searchinput.getText())){modelTeacherManage.addRow(new Object[]{true,instructor.getID_NUMBER(),instructor.getName(),instructor.getPassword(),instructor.getBirthday(),instructor.getGender()
                                        ,instructor.getPhone(),instructor.getEmail()});}
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
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedIndex() == 0) {
                    Collections.sort(a, new SortA_Z());
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0); // Clear existing data in the table

                    Iterator<Instructor> instructorIterator = a.iterator();
                    while (instructorIterator.hasNext()) {
                        Instructor instructor = instructorIterator.next();
                        String gender = (instructor.getGender()) ? "Nam" : "Nữ";
                        modelScoreManage.addRow(new Object[]{true, instructor.getID_NUMBER(), instructor.getName(), gender, instructor.getBirthday(), instructor.getPassword(), instructor.getEmail(), instructor.getPhone()});
                    }
                } else if (comboBox1.getSelectedIndex() == 1) {
                    Collections.sort(a, new SortZ_A());
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0); // Clear existing data in the table

                    Iterator<Instructor> instructorIterator = a.iterator();
                    while (instructorIterator.hasNext()){
                        Instructor instructor = instructorIterator.next();
                        String gender = (instructor.getGender()) ? "Nam":"Nữ";
                        modelScoreManage.addRow(new Object[]{true,instructor.getID_NUMBER(),instructor.getName(),gender,instructor.getBirthday(),instructor.getPassword(),instructor.getEmail(),instructor.getPhone()});
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
                table1.setModel(GetDataFromTable.setAllCheckboxFromTable(table1,checkAll.isSelected()));
            }
        });
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!Validation.isNumeric(insID.getText())) return;
                table1.setModel(GetDataFromTable.setCheckboxTableFromID(table1,check.isSelected(),Integer.parseInt(insID.getText())));
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
            String gender = (instructor.getGender()) ? "Nam":"Nữ";
            modelScoreManage.addRow(new Object[]{true,instructor.getID_NUMBER(),instructor.getName(),gender,instructor.getBirthday(),instructor.getPassword(),instructor.getEmail(),instructor.getPhone()});
        }

    }
    private JPanel TeacherManagentPanel;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton tảiLạiButton;
    private JButton exportButton;
    private JCheckBox check;
    private JCheckBox checkAll;
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
