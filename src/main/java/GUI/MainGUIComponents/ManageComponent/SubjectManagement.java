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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

                String TenMonHoc = TenMon.getText();


                int TinChi = Integer.parseInt(tinchi.getText());
                int Khoi = (int)comboBox1.getSelectedItem();

                Subject subject = new Subject();
                try {
                    if(String.valueOf(mamh.getText()).equals("")){
                        subject.setID(-1);
                    }else {
                        int id = Integer.valueOf(mamh.getText());
                        subject.setID(id);
                    }
                subject.setName(TenMonHoc);
                subject.setCredits(TinChi);
                subject.setGrandID(Khoi);
                SubjectHandle subjectHandle = new SubjectHandle();
                subjectHandle.INSERT(subject);
                refreshTable();


            }
                catch (NumberFormatException e1){
                    throw new RuntimeException(e1);
                }}
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = table1.rowAtPoint(e.getPoint());
                String id = String.valueOf( table1.getValueAt(clickedRow, 1));
                String maMon = String.valueOf( table1.getValueAt(clickedRow,2));
                String Tinchi  = String.valueOf(table1.getValueAt(clickedRow,3));
                int khoi  = (int)table1.getValueAt(clickedRow,4);
                System.out.println(khoi);
                mamh.setText(id);
                TenMon.setText(maMon);
                tinchi.setText(Tinchi);
                comboBox1.setSelectedItem(khoi);

            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.valueOf(mamh.getText());
                SubjectHandle subjectHandle = new SubjectHandle();
                subjectHandle.DELETE(id);
                mamh.setText(null);
                TenMon.setText(null);
                tinchi.setText(null);
                comboBox1.setSelectedItem(null);
                refreshTable();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.valueOf(mamh.getText());
                String TenMonHoc = TenMon.getText();
                int TinChi = Integer.parseInt(tinchi.getText());
                int Khoi = (int)comboBox1.getSelectedItem();

                Subject subject = new Subject();
                subject.setID(id);
                subject.setName(TenMonHoc);
                subject.setCredits(TinChi);
                subject.setGrandID(Khoi);
                SubjectHandle subjectHandle = new SubjectHandle();
                subjectHandle.UPDATE(subject);
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
    private JButton delete;
    private JButton insert;
    private JButton update;
    private JButton resetButton;
    private JButton chọnButton;
    private JButton bỏChọnButton;
    private JTable table1;
    private JComboBox comboBox2;
    private JButton zAButton;
    private JButton aZButton;
    private JTextField TenMon;
}
