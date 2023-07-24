package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.GrantHandle;
import DAO.Access.SubjectHandle;
import Model.Block;
import Model.Subject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String TenMonHoc = TenMon.getName();
                int TinChi = Integer.parseInt(tinchi.getText());
                int Khoi = (int)comboBox1.getSelectedItem();

                Subject subject = new Subject();
                subject.setName(TenMonHoc);
                subject.setCredits(TinChi);
                subject.setGrandID(Khoi);
                SubjectHandle subjectHandle = new SubjectHandle();
                subjectHandle.INSERT(subject);
                refreshTable();


            }
        });
    }
    public void refreshTable() {
        GrantHandle grantHandle = new GrantHandle();
        List<Block> idList = new ArrayList<>();
        try {
            idList = grantHandle.SELECT("SELECT * FROM grants");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Add the IDs to the JComboBox
        for (Block obj : idList) {
            comboBox1.addItem(obj.getID());
        }



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
    private JTextField mamh;
    private JTextField tinchi;
    private JComboBox comboBox1;
    private JButton xóaButton;
    private JButton insert;
    private JButton sửaButton;
    private JButton resetButton;
    private JButton chọnButton;
    private JButton bỏChọnButton;
    private JTable table1;
    private JComboBox comboBox2;
    private JButton zAButton;
    private JButton aZButton;
    private JTextField TenMon;
}
