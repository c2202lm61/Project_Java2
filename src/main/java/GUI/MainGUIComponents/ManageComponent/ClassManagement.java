package GUI.MainGUIComponents.ManageComponent;

import Controllers.Authorization.Authorization;
import DAO.Access.ClassHandle;

import DAO.Access.GrantHandle;
import DAO.Access.InstructorHandle;
import Model.Block;
import Model.Instructor;
import Model.MClass;

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


public class ClassManagement extends JInternalFrame{
    public List<MClass> a = new ArrayList<>();

    public ClassManagement(){


        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Lớp");
        modelScoreManage.addColumn("Mã khối");
        modelScoreManage.addColumn("Mã Chủ Nhiệm");
        // ----------------------
        labelTable1.setModel(modelScoreManage);

        refreshTable();
        // set layout (phan nay ko duoc code) -----------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(ClassManagementPanel);
        setVisible(true);
        //---------------------------------

        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Authorization.getPermisionForClass()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                MClass mClass = new MClass();
                try {
                    if(String.valueOf(Malop.getText()).equals("")){
                        mClass.setID(-1);
                    }else {
                        int id = Integer.valueOf(Malop.getText());
                        mClass.setID(id);
                    }
                    int makhoi = (int)MaKhoi.getSelectedItem();
                    int magvcn = (int)MaGVCN.getSelectedItem();
                    mClass.setGrandID(makhoi);
                    mClass.setManagerID(magvcn);
                    new ClassHandle().INSERT(mClass);
                    refreshTable();
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null,"Mã Lớp không hợp lệ");
                    return;
                }
            }
        });

        labelTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = labelTable1.rowAtPoint(e.getPoint());
                String id = String.valueOf( labelTable1.getValueAt(clickedRow, 1));
                int Makhoi = (int)labelTable1.getValueAt(clickedRow,2);
                int Magv  = (int)(labelTable1.getValueAt(clickedRow,3));
                Malop.setText(id);
                MaGVCN.setSelectedItem(Magv);
                MaKhoi.setSelectedItem(Makhoi);


            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Authorization.getPermisionForClass()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                try {
                    int id = Integer.valueOf(Malop.getText());
                    ClassHandle classHandle = new ClassHandle();
                    classHandle.DELETE(id);
                    Malop.setText(null);
                    MaGVCN.setSelectedItem(null);
                    MaKhoi.setSelectedItem(null);
                    refreshTable();
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null,"Mã Lớp không hợp lệ");
                    return;
                }

            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Authorization.getPermisionForClass()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                try {
                    MClass mClass = new MClass();
                    int id = Integer.valueOf(Malop.getText());
                    if(id < 1){
                        JOptionPane.showMessageDialog(null,"Mã Lớp không hợp lệ");
                    return;
                    }else {
                        int makhoi = (int)MaKhoi.getSelectedItem();
                        int magvcn = (int)MaGVCN.getSelectedItem();
                        mClass.setGrandID(makhoi);
                        mClass.setID(id);
                        mClass.setManagerID(magvcn);
                        new ClassHandle().UPDATE(mClass);
                        refreshTable();
                    }
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null,"Mã Lớp không hợp lệ");
                    return;
                }

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelScoreManage = (DefaultTableModel) labelTable1.getModel();
                modelScoreManage.setRowCount(0); // Clear existing data in the table
                Iterator<MClass> classIterator = a.iterator();

                while(classIterator.hasNext()){
                    MClass mclass = classIterator.next();
                    if(mclass.getID() == Integer.valueOf(searchinput.getText())){
                        modelScoreManage.addRow(new Object[]{true,mclass.getID(),mclass.getGrandID(),mclass.getManagerID()});
                    }
                }
            }
        });
        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            MaKhoi.addItem(obj.getID());
        }

        InstructorHandle instructorHandle = new InstructorHandle();
        List<Instructor> maGVCNlist = new ArrayList<>();
        try {
            maGVCNlist = instructorHandle.SELECT("SELECT * FROM `instructor`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Add the IDs to the JComboBox
        for (Instructor inst : maGVCNlist) {
            MaGVCN.addItem(inst.getID_NUMBER());
        }


        // Assuming you want to preselect an ID in the JComboBox (let's call it selectedID):

        DefaultTableModel modelScoreManage = (DefaultTableModel) labelTable1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table

        ClassHandle classHandle = new ClassHandle();
        this.a = null;
        try {
            a = classHandle.SELECT("SELECT * FROM `class`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<MClass> classIterator = a.iterator();

        while(classIterator.hasNext()){
            MClass mclass = classIterator.next();
            modelScoreManage.addRow(new Object[]{true,mclass.getID(),mclass.getGrandID(),mclass.getManagerID()});
        }

    }
    private JComboBox MaKhoi;
    private JPanel ClassManagementPanel;
    private JTable labelTable1;
    private JButton button1;
    private JButton insert;
    private JButton update;
    private JButton delete;
    private JButton reload;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JTextField Malop;
    private JComboBox MaGVCN;
    private JTextField searchinput;
    private JButton searchButton;


}
