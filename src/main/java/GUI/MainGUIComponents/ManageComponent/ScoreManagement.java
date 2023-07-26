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
        modelScoreManage.addColumn("Mã học sinh");
        modelScoreManage.addColumn("Mã Môn Học");
        modelScoreManage.addColumn("Điểm 15p");
        modelScoreManage.addColumn("Điểm 45p");
        modelScoreManage.addColumn("Điểm thi giữa kì 1");
        modelScoreManage.addColumn("Điểm thi cuối kì 1");
        modelScoreManage.addColumn("Điểm thi giữa kì 2");
        modelScoreManage.addColumn("Điểm thi cuối kì 2");
        //-----------------------------------------------
        table1.setModel(modelScoreManage);
        updateData();

        //-----------------------------------------------------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(scoreManageComponent);
        setVisible(true);
        //show studentName when ID true

        //update subject id when change value
        scmSubjectName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Lấy giá trị của scmSubjectCode khi giá trị của JComboBox thay đổi
                    String selectedValue = (String) scmSubjectName.getSelectedItem();
                    System.out.println("Selected scmSubjectName: " + selectedValue);
                    Iterator<Subject> list = subjectList.iterator();
                    while (list.hasNext()){
                        Subject item =  list.next();
                        if (item.getName().equals(scmSubjectName.getSelectedItem())){
                            System.out.println("Subject selectID: "+item.getID());
                            scmSubjectCode.setText(String.valueOf(item.getID()));
                        }
                    }
                }
            }
        });
        scmStudentCode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(scmStudentCode.getText());
                try {
                    studentList = new StudentHandle().SELECT("SELECT * FROM `student`");
                }catch (SQLException exception){
                    exception.printStackTrace();
                }
                Iterator<Student> studentIterator = studentList.iterator();
                while (studentIterator.hasNext()){
                    Student item = studentIterator.next();
                    if (item.getID() == Integer.parseInt(scmStudentCode.getText())){
                        scmStudentName.setText(item.getName());
                    }
                }
            }
        });
    }
}
