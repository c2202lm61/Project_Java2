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
                try {
                    if(String.valueOf(MaKhoi.getText()).equals("")){
                        block.setID(-1);
                    }else {
                        int id = Integer.valueOf(MaKhoi.getText());
                        block.setID(id);
                    }

                    String tenkhoi = TenKhoi.getText();
                    block.setName(tenkhoi);
                    GrantHandle grantHandle = new GrantHandle();
                    grantHandle.INSERT(block);
                    refreshTable();
                } catch (NumberFormatException e1){
                    throw new RuntimeException(e1);
                }

            }

        });

        // mouth over to table
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

        //delete
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
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Block block = new Block();
                int id = Integer.valueOf(MaKhoi.getText());

                String tenkhoi = TenKhoi.getText();
                block.setID(id);
                block.setName(tenkhoi);
                GrantHandle grantHandle = new GrantHandle();
                grantHandle.UPDATE(block);
                refreshTable();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrantHandle grantHandle = new GrantHandle();
                DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                modelScoreManage.setRowCount(0); // Clear existing data in the table
                if(searchByNameCheckBox.isSelected()){
                    Iterator<Block> blockIterator = a.iterator();
                    while (blockIterator.hasNext()){
                        Block block = blockIterator.next();
                        if(String.valueOf(block.getName()).contains(TextSearch.getText())){modelScoreManage.addRow(new Object[]{true,block.getID(),block.getName()});}
                    }
                }else{
                    Iterator<Block> blockIterator = a.iterator();
                    while (blockIterator.hasNext()){
                        Block block = blockIterator.next();
                        if(block.getID() == Integer.valueOf(TextSearch.getText())){modelScoreManage.addRow(new Object[]{true,block.getID(),block.getName()});}
                    }
                }

            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    public List<Block> a = new ArrayList<>();

    private JPanel panel1;
    private JButton chọnẢnhButton;
    private JButton insert;
    private JButton update;
    private JButton delete;
    private JButton refreshButton;
    private JTable table1;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JComboBox comboBox1;
    private JTextField MaKhoi;
    private JTextField TenKhoi;
    private JTextField TextSearch;
    private JButton searchButton;
    private JCheckBox searchByNameCheckBox;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        MaKhoi =new JTextField(8);
    }
}
