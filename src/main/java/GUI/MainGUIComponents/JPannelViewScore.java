package GUI.MainGUIComponents;

import Controllers.ViewScoreController;
import DAO.Access.ClassHandle;
import DAO.Access.GrantHandle;
import DAO.Access.SubjectHandle;
import Model.Block;
import Model.MClass;
import Model.Subject;
import Model.ViewScore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class JPannelViewScore extends JPanel {
    public JPannelViewScore(){
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("STT");
        defaultTableModel.addColumn("Mã học sinh");
        defaultTableModel.addColumn("Tên học sinh");
        defaultTableModel.addColumn("Môn");
        defaultTableModel.addColumn("Lop");
        defaultTableModel.addColumn("Khoi");
        defaultTableModel.addColumn("Điểm Hệ Số 1");
        defaultTableModel.addColumn("Điểm Hệ Số 2");
        defaultTableModel.addColumn("Điểm Hệ Số 3");
        defaultTableModel.addColumn("Điểm Hệ Số 4");
        defaultTableModel.addColumn("Tổng Điểm");
        table1.setModel(defaultTableModel);
        refresh();

        setLayout(new  GridLayout(1,1));
        add(panel1);
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                modelScoreManage.setRowCount(0); // Clear existing data in the table
                a =null;
                String sql = "SELECT class.grant_id,class.class_code,student.Name,student.Student_id,subject_student.Subject_student_id,subject_student.Subject_code FROM class INNER JOIN student on student.Class_code = class.class_code INNER JOIN subject_student on student.Student_id = subject_student.student_id";

                if((grantCheckBox.isSelected() || classCheckBox.isSelected() || subjectCheckBox.isSelected())){
                    sql += " WHERE";
                    if(grantCheckBox.isSelected()){
                        sql += " class.grant_id = "+(int)comboBox2.getSelectedItem();
                        if(classCheckBox.isSelected() || subjectCheckBox.isSelected()){
                            sql += " AND";
                        }
                    }
                    if(classCheckBox.isSelected()){
                        sql += " class.class_code = "+(int)comboBox3.getSelectedItem();
                        if( subjectCheckBox.isSelected()){
                            sql += " AND";
                        }
                    }
                    if(subjectCheckBox.isSelected()){
                        sql += " subject_student.Subject_code = "+(int)comboBox4.getSelectedItem();
                    }
                }
                try {
                    a = new ViewScoreController().SELECT(sql);
                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
                Iterator<ViewScore> studentIterator = a.iterator();
                int i = 1;
                while (studentIterator.hasNext()){
                    ViewScore viewScore = studentIterator.next();
                    modelScoreManage.addRow(new Object[]{i,viewScore.getMaHocSinh(),viewScore.getTenHS(),viewScore.getMaMon(),viewScore.getMaLop(),viewScore.getMaKhoi(),viewScore.getDHS1(),viewScore.getDHS2(),viewScore.getDHS3(),viewScore.getDHS4(),viewScore.getTongDien()});
                    i++;
                }
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Search.getText().equals("")){
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0);
                    Iterator<ViewScore> studentIterator = a.iterator();
                    int i = 1;
                    while (studentIterator.hasNext()){
                        ViewScore viewScore = studentIterator.next();
                            modelScoreManage.addRow(new Object[]{i,viewScore.getMaHocSinh(),viewScore.getTenHS(),viewScore.getMaMon(),viewScore.getMaLop(),viewScore.getMaKhoi(),viewScore.getDHS1(),viewScore.getDHS2(),viewScore.getDHS3(),viewScore.getDHS4(),viewScore.getTongDien()});
                            i++;
                    }
                }else {
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0);
                    Iterator<ViewScore> studentIterator = a.iterator();
                    int i = 1;

                    if(checkByNameCheckBox.isSelected()){
                        while (studentIterator.hasNext()){
                            ViewScore viewScore = studentIterator.next();
                            if(viewScore.getTenHS().contains(Search.getText())){
                                modelScoreManage.addRow(new Object[]{i,viewScore.getMaHocSinh(),viewScore.getTenHS(),viewScore.getMaMon(),viewScore.getMaLop(),viewScore.getMaKhoi(),viewScore.getDHS1(),viewScore.getDHS2(),viewScore.getDHS3(),viewScore.getDHS4(),viewScore.getTongDien()});
                                i++;
                            }
                        }
                    }else {
                        while (studentIterator.hasNext()){
                            ViewScore viewScore = studentIterator.next();
                            if(viewScore.getMaHocSinh() == Integer.valueOf(Search.getText()) ){
                                modelScoreManage.addRow(new Object[]{i,viewScore.getMaHocSinh(),viewScore.getTenHS(),viewScore.getMaMon(),viewScore.getMaLop(),viewScore.getMaKhoi(),viewScore.getDHS1(),viewScore.getDHS2(),viewScore.getDHS3(),viewScore.getDHS4(),viewScore.getTongDien()});
                                i++;
                            }
                        }
                    }
                }

            }
        });
        viewProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new myProfile();
            }
        });
    }

    public void refresh(){
        List<Block> idList = null;
        try {
            idList = new GrantHandle().SELECT("SELECT * FROM grants");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        comboBox2.removeAllItems();
        // Add the IDs to the JComboBox
        for (Block obj : idList) {
            comboBox2.addItem(obj.getID());
        }


        List<MClass> mClassList = null;
        try {
            mClassList = new ClassHandle().SELECT("SELECT * FROM `class`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        comboBox3.removeAllItems();
        for (MClass mclass: mClassList){
            comboBox3.addItem(mclass.getID());
        }
        List<Subject> monhoc = null;
        try {
            monhoc = new SubjectHandle().SELECT("SELECT * FROM `subject`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        comboBox4.removeAllItems();
        for(Subject obj1: monhoc){
            comboBox4.addItem(obj1.getID());
        }


        DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table
         this.a =null;
        String sql = "SELECT class.grant_id,class.class_code,student.Name,student.Student_id,subject_student.Subject_student_id,subject_student.Subject_code FROM class INNER JOIN student on student.Class_code = class.class_code INNER JOIN subject_student on student.Student_id = subject_student.student_id";

        try {
            a = new ViewScoreController().SELECT(sql);
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }
        Iterator<ViewScore> studentIterator = a.iterator();
        int i = 1;
        while (studentIterator.hasNext()){
            ViewScore viewScore = studentIterator.next();
            modelScoreManage.addRow(new Object[]{i,viewScore.getMaHocSinh(),viewScore.getTenHS(),viewScore.getMaMon(),viewScore.getMaLop(),viewScore.getMaKhoi(),viewScore.getDHS1(),viewScore.getDHS2(),viewScore.getDHS3(),viewScore.getDHS4(),viewScore.getTongDien()});
            i++;
        }

    }
    public List<ViewScore> a;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton aZButton;
    private JButton zAButton;
    private JButton search;
    private JTextField Search;
    private JTable table1;
    private JButton viewProfile;
    private JCheckBox grantCheckBox;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JCheckBox classCheckBox;
    private JComboBox comboBox4;
    private JCheckBox subjectCheckBox;
    private JButton showButton;
    private JCheckBox checkByNameCheckBox;
}
