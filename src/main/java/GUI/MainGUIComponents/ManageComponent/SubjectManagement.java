package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.SubjectHandle;
import Model.MClass;
import Model.Subject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


public class SubjectManagement extends JInternalFrame{
    public SubjectManagement(){
        SubjectHandle subjectHandle = new SubjectHandle();
        List<Subject> a = null;
        try {
            a = subjectHandle.SELECT("SELECT * FROM `subject`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<Subject> subjectIterator = a.iterator();
        //--------------------------------------
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Môn Mọc");
        modelScoreManage.addColumn("Tên Môn Học");
        modelScoreManage.addColumn("Tín Chỉ");
        modelScoreManage.addColumn("ID Khối");
        //------------------------------------

        while (subjectIterator.hasNext()){
            Subject subject = subjectIterator.next();
            modelScoreManage.addRow(new Object[]{true,subject.getID(),subject.getName(),subject.getCredits(),subject.getGrandID()});
        }
        table1.setModel(modelScoreManage);

        //---------------------------------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(panel1);
        setVisible(true);
        //-----------------------------------------
    }
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JButton xóaButton;
    private JButton thêmButton;
    private JButton sửaButton;
    private JButton resetButton;
    private JButton chọnButton;
    private JButton bỏChọnButton;
    private JTable table1;
    private JComboBox comboBox2;
    private JButton zAButton;
    private JButton aZButton;
}
