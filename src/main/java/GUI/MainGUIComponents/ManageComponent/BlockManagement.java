package GUI.MainGUIComponents.ManageComponent;


import DAO.Access.GrantHandle;
import Model.Block;

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

public class BlockManagement extends JInternalFrame{
    public List<Block> a = new ArrayList<>();
    public BlockManagement(){

        // table view (phan nay ko duoc code)--------------
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Khối");
        modelScoreManage.addColumn("Tên Khối");
        table1.setModel(modelScoreManage);
        // --------------------------

        refreshTable();


        // set layout (phan nay ko duoc code) -----------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(panel1);
        setVisible(true);
        //---------------------------------
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Block block = new Block();
                String tenkhoi = TenKhoi.getText();

                block.setName(tenkhoi);
                GrantHandle grantHandle = new GrantHandle();
                grantHandle.INSERT(block);
                refreshTable();
            }

        });


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 int clickedRow = table1.rowAtPoint(e.getPoint());
                 String id = String.valueOf( table1.getValueAt(clickedRow, 1));
                 String Name =String.valueOf( table1.getValueAt(clickedRow,2));

                 MaKhoi.setText(id);
                 TenKhoi.setText(Name);


            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.valueOf(MaKhoi.getText());
                GrantHandle grantHandle = new GrantHandle();
                grantHandle.DELETE(id);
                MaKhoi.setText(null);
                TenKhoi.setText(null);
                refreshTable();
            }
        });
    }

    public void refreshTable() {
        GrantHandle grantHandle = new GrantHandle();


        DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table

        this.a = null;
        try {
            a = grantHandle.SELECT("SELECT * FROM grants");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Iterator<Block> blockIterator = a.iterator();
        while (blockIterator.hasNext()){
            Block block = blockIterator.next();

            modelScoreManage.addRow(new Object[]{true,block.getID(),block.getName()});
        }


    }

    private JPanel panel1;
    private JButton chọnẢnhButton;
    private JButton insert;
    private JButton sửaButton;
    private JButton delete;
    private JButton tảiLạiButton;
    private JTable table1;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JComboBox comboBox1;
    private JTextField MaKhoi;
    private JTextField TenKhoi;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        MaKhoi =new JTextField(8);
    }
}
