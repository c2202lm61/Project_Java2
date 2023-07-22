package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.InstructorSubjectHandle;
import Model.InstructorSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class SubjectTeachingTeacher extends JInternalFrame{

    public SubjectTeachingTeacher(){
        InstructorSubjectHandle instuctorHandle = new InstructorSubjectHandle();
        List<InstructorSubject> a = null;
        try {
            a = instuctorHandle.SELECT("SELECT * FROM `instructor_subject`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<InstructorSubject> instructorsubjectIterator = a.iterator();
        //-----------------------------------------------
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Giáo Viên Bộ Môn");
        modelScoreManage.addColumn("Mã Giáo Viên");
        modelScoreManage.addColumn("Mã Môn Học");
        //-----------------------------------------------
        while (instructorsubjectIterator.hasNext()){
            modelScoreManage.addRow(new Object[]{true,instructorsubjectIterator.next().getID_Teach(),instructorsubjectIterator.next().getID_NUMBER(),instructorsubjectIterator.next().getSubject_code()});
        }
        table1.setModel(modelScoreManage);
        //-----------------------------------------------
        setTitle("");
        setContentPane(subjectTeachingTeacherPanel);
        setVisible(true);
        //-----------------------------------------------
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
