package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.StudentHandle;
import DAO.Access.SubjectHandle;
import Model.Student;
import Model.Subject;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ScoreManagement extends JInternalFrame {
    private List<Subject> subjectList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();
    private JPanel scoreManageComponent;

    private JTable table1;
    private JTextField scmStudentName;
    private JTextField scmSubjectCode;
    private JTextField scmStudentCode;
    private JButton chọnẢnhButton;
    private JButton tảiLạiButton;
    private JButton xóaButton;
    private JButton sửaButton;
    private JButton thêmButton;
    private JTextField textField1;
    private JComboBox scmSubjectName;

    public void updateData() throws SQLException {


        //get subject data and add commbocox
        try {
            subjectList = new SubjectHandle().SELECT("SELECT * FROM `subject`");
        }catch (SQLException e){
            e.printStackTrace();
        }
        Iterator<Subject> subjectIterator = subjectList.iterator();
        while (subjectIterator.hasNext()){
            Subject item = subjectIterator.next();
            scmSubjectName.addItem(item.getName());
        }

    }
    public ScoreManagement() throws SQLException {


        //------------------------------------------------------
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Điểm");
        modelScoreManage.addColumn("Mã Môn Học Của Học Sinh");
        modelScoreManage.addColumn("Loại Điểm");
        modelScoreManage.addColumn("Điểm");
        //-----------------------------------------------
        table1.setModel(modelScoreManage);


        //-----------------------------------------------------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(scoreManageComponent);
        setVisible(true);
        //show studentName when ID true

        //update subject id when change value

    }
}
