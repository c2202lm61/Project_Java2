package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.InstructorSubjectHandle;
import Model.InstructorSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubjectTeachingTeacher extends JInternalFrame{
    private List<InstructorSubject> a = new ArrayList<>();
    public SubjectTeachingTeacher(){


        //-----------------------------------------------
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Giáo Viên Bộ Môn");
        modelScoreManage.addColumn("Mã Giáo Viên");
        modelScoreManage.addColumn("Mã Môn Học");
        //-----------------------------------------------

        table1.setModel(modelScoreManage);
        refreshTable();
        //-----------------------------------------------
        setTitle("");
        setContentPane(subjectTeachingTeacherPanel);
        setVisible(true);
        //-----------------------------------------------
    }

    public void refreshTable() {
        DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table

        InstructorSubjectHandle instuctorHandle = new InstructorSubjectHandle();
        this.a = null;
        try {
            a = instuctorHandle.SELECT("SELECT * FROM `instructor_subject`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<InstructorSubject> instructorsubjectIterator = a.iterator();
        while (instructorsubjectIterator.hasNext()){
            InstructorSubject instructorSubject = instructorsubjectIterator.next();
            modelScoreManage.addRow(new Object[]{true,instructorSubject.getID_Teach(),instructorSubject.getID_NUMBER(),instructorSubject.getSubject_code()});
        }

    }
    private JPanel subjectTeachingTeacherPanel;
    private JButton tảiLạiButton;
    private JButton xóaButton;
    private JButton sửaButton;
    private JButton thêmButton;
    private JButton chọnẢnhButton;
    private JCheckBox chọnTấtCảCheckBox;
    private JCheckBox bỏChọnCheckBox;
    private JComboBox comboBox1;
    private JTextField MaGVBM;
    private JTextField MaGV;
    private JTextField MaMH;
    private JTable table1;
}
