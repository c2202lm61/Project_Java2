package GUI.MainGUIComponents.ManageComponent;

import Controllers.Authorization.Authorization;
import DAO.Access.SubjectHandle;
import DAO.Access.SubjectStudentHandle;
import Model.Subject;
import Model.SubjectStudent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class StudentSubjectManagement extends JFrame {

    public List<SubjectStudent> a = new ArrayList<>();
    public StudentSubjectManagement(int id){
        DefaultTableModel modelScoreManage = new DefaultTableModel();

        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Cho Điểm");
        modelScoreManage.addColumn("Mã Học Sinh");
        modelScoreManage.addColumn("Mã Môn");
        // ----------------------
        table1.setModel(modelScoreManage);

        refreshTable(id);
        // set layout (phan nay ko duoc code) -----------------------
        add(StudentSubjectPanel);
        setSize(400,200);

        setLocationRelativeTo(null);

        setVisible(true);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubjectStudent subjectStudent = new SubjectStudent();

                try {
                    if(String.valueOf(machodiem.getText()).equals("")){
                        subjectStudent.setSubject_Student_id(-1);
                    }else {
                        int maChoDiem = Integer.valueOf(machodiem.getText());
                        subjectStudent.setSubject_Student_id(maChoDiem);
                    }

                    subjectStudent.setStudent_id(id);

                    subjectStudent.setSubject_code((int)(mamh.getSelectedItem()));
                    SubjectStudentHandle subjectStudentHandle = new SubjectStudentHandle();
                    subjectStudentHandle.INSERT(subjectStudent);
                    refreshTable(id);
                } catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null,"Mã Cho Điểm không hợp lệ");
                            return;
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ss_id = Integer.valueOf(machodiem.getText());
                    if(ss_id <1){
                        JOptionPane.showMessageDialog(null,"Mã Cho Điểm không hợp lệ");
                        return;
                    }
                    SubjectStudent subjectStudent = new SubjectStudent();
                    int mamon = (int)mamh.getSelectedItem();
                    subjectStudent.setSubject_Student_id(ss_id);
                    subjectStudent.setStudent_id(id);
                    subjectStudent.setSubject_code(mamon);
                    SubjectStudentHandle subjectStudentHandle = new SubjectStudentHandle();
                    subjectStudentHandle.UPDATE(subjectStudent);
                    refreshTable(id);
                } catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null,"Mã Cho Điểm không hợp lệ");
                            return;
                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ss_id = Integer.valueOf(machodiem.getText());
                    if(ss_id <1){
                        JOptionPane.showMessageDialog(null,"Mã Cho Điểm không hợp lệ");
                        return;
                    }
                    SubjectStudentHandle subjectStudentHandle = new SubjectStudentHandle();
                    subjectStudentHandle.DELETE(id);
                    machodiem.setText(null);
                    mamh.setSelectedItem(null);
                    refreshTable(id);
                } catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null,"Mã Cho Điểm không hợp lệ");
                            return;
                }

            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = table1.rowAtPoint(e.getPoint());
                 String ss_id = String.valueOf( table1.getValueAt(clickedRow, 1));
                 int s_code = (int)table1.getValueAt(clickedRow,3);
                 machodiem.setText(ss_id);
                 mamh.setSelectedItem(s_code);
            }
        });
        ScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreManagement scoreManagement = new ScoreManagement(Integer.valueOf(machodiem.getText()));
            }
        });
    }
    public void refreshTable(int stuid) {
        List<Subject> monhoc = null;
        SubjectHandle subjectHandle = new SubjectHandle();
        try {
            monhoc = subjectHandle.SELECT("SELECT * FROM `subject`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Subject obj1: monhoc){
            mamh.addItem(obj1.getID());
        }


        SubjectStudentHandle subjectStudentHandle = new SubjectStudentHandle();

        DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table

        this.a = null;
        try {
            a = subjectStudentHandle.SELECT("SELECT * FROM `subject_student` WHERE student_id = "+Integer.valueOf(stuid)+"");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Iterator<SubjectStudent> subjectStudentIterator = a.iterator();
        while (subjectStudentIterator.hasNext()){
            SubjectStudent subjectStudent = subjectStudentIterator.next();

            modelScoreManage.addRow(new Object[]{true,subjectStudent.getSubject_Student_id(),subjectStudent.getStudent_id(),subjectStudent.getSubject_code()});
        }


    }


    private JButton tảiLạiButton1;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton ScoreButton;
    private JPanel StudentSubjectPanel;
    private JTable table1;
    private JComboBox mamh;
    private JTextField machodiem;
}
