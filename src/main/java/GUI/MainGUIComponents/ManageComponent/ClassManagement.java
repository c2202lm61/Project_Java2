package GUI.MainGUIComponents.ManageComponent;

import Controllers.Authorization.Authorization;
import Controllers.ExportData;
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
                    try {
                        if(new ClassHandle().INSERT(mClass))
                            JOptionPane.showMessageDialog(null,"Thêm dữ liệu thành công");
                        else JOptionPane.showMessageDialog(null,"Thêm dữ liệu không thành công");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
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
                    if (new ClassHandle().DELETE(id))
                        JOptionPane.showMessageDialog(null,"Xóa dữ liệu thành công");
                    else  JOptionPane.showMessageDialog(null,"Xóa dữ liệu không thành công");
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
                        if (new ClassHandle().UPDATE(mClass))
                            JOptionPane.showMessageDialog(null,"Cập nhật dữ liệu thành công");
                        else JOptionPane.showMessageDialog(null,"Cập nhật dữ liệu không thành công");

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
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ExportData.exportData(labelTable1,1);
            }
        });
    }

    public void refreshTable() {
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
    private JButton insert;
    private JButton update;
    private JButton delete;
    private JButton reload;
    private JCheckBox chọnCheckBox;
    private JCheckBox chọnTấtCảCheckBox;
    private JTextField Malop;
    private JComboBox MaGVCN;
    private JTextField searchinput;
    private JButton searchButton;
    private JComboBox comboBox1;
    private JButton exportButton;


    private void createUIComponents() {
        labelTable1 = new JTable() {

            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        labelTable1.setPreferredScrollableViewportSize(labelTable1.getPreferredSize());
    }
}
