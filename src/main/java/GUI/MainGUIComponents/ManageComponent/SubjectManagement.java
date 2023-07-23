package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.SubjectHandle;
import Model.MClass;
import Model.Subject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SubjectManagement extends JInternalFrame{
    private List<Subject> a = new ArrayList<>();
    public SubjectManagement(){

        //--------------------------------------
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Môn Mọc");
        modelScoreManage.addColumn("Tên Môn Học");
        modelScoreManage.addColumn("Tín Chỉ");
        modelScoreManage.addColumn("ID Khối");
        //------------------------------------

        table1.setModel(modelScoreManage);
        refreshTable();
        //---------------------------------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(panel1);
        setVisible(true);
        //-----------------------------------------
    }
    public void refreshTable() {
        DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table

        SubjectHandle subjectHandle = new SubjectHandle();
        this.a = null;
        try {
            a = subjectHandle.SELECT("SELECT * FROM `subject`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<Subject> subjectIterator = a.iterator();
        while (subjectIterator.hasNext()){
            Subject subject = subjectIterator.next();
            modelScoreManage.addRow(new Object[]{true,subject.getID(),subject.getName(),subject.getCredits(),subject.getGrandID()});
        }

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
