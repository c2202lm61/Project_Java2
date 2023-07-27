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
        defaultTableModel.addColumn("Điểm Hệ Số 3");
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

                new ViewScoreController();
                List<ViewScore> a =null;
                try {
                    a = new ViewScoreController().SELECT((int)comboBox2.getSelectedItem(),(int)comboBox3.getSelectedItem(),(int)comboBox4.getSelectedItem() );
                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
                Iterator<ViewScore> studentIterator = a.iterator();
                while (studentIterator.hasNext()){
                    ViewScore viewScore = studentIterator.next();
                    modelScoreManage.addRow(new Object[]{true});
                }
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
        Iterator<MClass> classIterator = mClassList.iterator();

        while(classIterator.hasNext()){
            MClass mclass = classIterator.next();
            comboBox3.addItem(mclass.getID());
        }
        List<Subject> monhoc = null;
        try {
            monhoc = new SubjectHandle().SELECT("SELECT * FROM `subject`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Subject obj1: monhoc){
            comboBox4.addItem(obj1.getID());
        }
    }
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton aZButton;
    private JButton zAButton;
    private JButton xuấtDữLiệuButton;
    private JTextField textField1;
    private JTable table1;
    private JButton xemĐiểmCủaTôiButton;
    private JCheckBox khốiCheckBox;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JCheckBox lớpCheckBox;
    private JComboBox comboBox4;
    private JCheckBox mônCheckBox;
    private JButton showButton;
}
