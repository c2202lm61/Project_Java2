package GUI.MainGUIComponents.ManageComponent;


import Controllers.*;
import Controllers.Authenlication.Authenlication;
import Controllers.Authorization.Authorization;
import DAO.Access.GrantHandle;
import DAO.JDBCDriver;
import Model.Block;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BlockManagement extends JInternalFrame {
    public BlockManagement() {

        // table view (phan nay ko duoc code)--------------
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Khối");
        modelScoreManage.addColumn("Tên Khối");
        table1.setModel(modelScoreManage);
        // --------------------------

        refreshTable();


        // set layout (phan nay ko duoc code) -----------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226), 1));
        setContentPane(panel1);
        setVisible(true);
        //---------------------------------
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Authorization.getPermisionForBlock()) {
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy  cập");
                    return;
                }
                Object[] options = { "Có", "Không" };
                int result = JOptionPane.showOptionDialog(
                        null,
                        "Bạn có chắc không?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );
                if(result != 0)return;
                Block block = new Block();
                try {
                    if (String.valueOf(MaKhoi.getText()).equals("")) {
                        block.setID(-1);
                    } else {
                        int id = Integer.valueOf(MaKhoi.getText());
                        block.setID(id);
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Mã Khối không hợp lệ");
                    return;
                }
                String tenkhoi = TenKhoi.getText();
                if (tenkhoi.equals("")) {
                    JOptionPane.showMessageDialog(null, "Tên Khối không hợp lệ");
                    return;
                } else {
                    block.setName(tenkhoi);
                    GrantHandle grantHandle = new GrantHandle();
                    try {
                        if (grantHandle.INSERT(block))
                            JOptionPane.showMessageDialog(null,"Thêm khối thành công");
                        else JOptionPane.showMessageDialog(null,"Thêm khối không thành công");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    refreshTable();
                }
            }

        });

        // mouth over to table
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = table1.rowAtPoint(e.getPoint());
                String id = String.valueOf(table1.getValueAt(clickedRow, 1));
                String Name = String.valueOf(table1.getValueAt(clickedRow, 2));

                MaKhoi.setText(id);
                TenKhoi.setText(Name);
            }
        });

        //delete
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Authorization.getPermisionForBlock()) {
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy  cập");
                    return;
                }
                if (!Validation.isNumeric(MaKhoi.getText())) {
                    JOptionPane.showMessageDialog(null, "Mã khối không hợp lệ");
                    return;
                }
                Object[] options = { "Có", "Không" };
                int result = JOptionPane.showOptionDialog(
                        null,
                        "Xóa khối sẽ gây mất mát dữ liệu, bạn có muốn tiếp tục không?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );
                if(result != 0)return;
                JPasswordField pwd = new JPasswordField();
                Object[] message = { "Nhập mật khẩu của bạn:", pwd };

                int option = JOptionPane.showConfirmDialog(null, message, "Nhập Mật Khẩu", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.CANCEL_OPTION) return;
                Boolean pwdtrue = false;
                try {
                    ResultSet resultSet = JDBCDriver.ExecQuery("SELECT * FROM instructor WHERE ID_NUMBER="+ Authenlication.insLogin.getID_NUMBER()+" AND password ='"+pwd.getText()+"'");
                    if (resultSet.next()){
                        pwdtrue = true;
                    }
                    JDBCDriver.DestroyConnection();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if(!pwdtrue) {
                    JOptionPane.showMessageDialog(null,"Mật khẩu không chính xác");
                    return;
                }

                try {
                    int id = Integer.valueOf(MaKhoi.getText());
                    if(new GrantHandle().DELETE(id))
                        JOptionPane.showMessageDialog(null,"Xóa khối thành công");
                    else
                        JOptionPane.showMessageDialog(null,"Xóa khối không thành công");
                    MaKhoi.setText(null);
                    TenKhoi.setText(null);
                    refreshTable();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Authorization.getPermisionForBlock()) {
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy  cập");
                    return;
                }
                Object[] options = { "Có", "Không" };
                int result = JOptionPane.showOptionDialog(
                        null,
                        "Bạn có chắc không?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );
                if(result != 0)return;

                if (!Validation.isNumeric(MaKhoi.getText())) {
                    JOptionPane.showMessageDialog(null, "Mã khối không hợp lệ");
                    return;
                }
                String tenkhoi = TenKhoi.getText();
                if (tenkhoi.equals("")) {
                    JOptionPane.showMessageDialog(null, "Tên khối không hợp lệ");
                    return;
                }
                Block block = new Block();
                try {

                    int id = Integer.valueOf(MaKhoi.getText());

                    block.setID(id);
                    block.setName(tenkhoi);
                    GrantHandle grantHandle = new GrantHandle();
                    if (grantHandle.UPDATE(block))
                        JOptionPane.showMessageDialog(null,"Cập nhật dữ liệu thành công");
                    else JOptionPane.showMessageDialog(null,"Cập nhật dữ liệu không thành công");
                    refreshTable();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Mã Khối không hợp lệ");
                    return;
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrantHandle grantHandle = new GrantHandle();
                DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                modelScoreManage.setRowCount(0); // Clear existing data in the table
                if (searchByNameCheckBox.isSelected()) {
                    Iterator<Block> blockIterator = a.iterator();
                    while (blockIterator.hasNext()) {
                        Block block = blockIterator.next();
                        if (String.valueOf(block.getName()).contains(TextSearch.getText())) {
                            modelScoreManage.addRow(new Object[]{true, block.getID(), block.getName()});
                        }
                    }
                } else {
                    Iterator<Block> blockIterator = a.iterator();
                    while (blockIterator.hasNext()) {
                        Block block = blockIterator.next();
                        if (block.getID() == Integer.valueOf(TextSearch.getText())) {
                            modelScoreManage.addRow(new Object[]{true, block.getID(), block.getName()});
                        }
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
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox2.getSelectedIndex() == 0) {
                    Collections.sort(a, new SortA_Z());
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0); // Clear existing data in the table

                    Iterator<Block> blockIterator = a.iterator();
                    while (blockIterator.hasNext()) {
                        Block block = blockIterator.next();
                        if (String.valueOf(block.getName()).contains(TextSearch.getText())) {
                            modelScoreManage.addRow(new Object[]{true, block.getID(), block.getName()});
                        }
                    }
                } else if (comboBox2.getSelectedIndex() == 1) {
                    Collections.sort(a, new SortZ_A());
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0); // Clear existing data in the table

                    Iterator<Block> blockIterator = a.iterator();
                    while (blockIterator.hasNext()) {
                        Block block = blockIterator.next();
                        if (String.valueOf(block.getName()).contains(TextSearch.getText())) {
                            modelScoreManage.addRow(new Object[]{true, block.getID(), block.getName()});
                        }
                    }

                }
            }
        });
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ExportData.exportData(table1,1);
            }
        });
        checkAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                table1.setModel(GetDataFromTable.setAllCheckboxFromTable(table1,checkAll.isSelected()));
            }
        });
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!Validation.isNumeric(MaKhoi.getText())) return;
                table1.setModel(GetDataFromTable.setCheckboxTableFromID(table1,check.isSelected(),Integer.valueOf(MaKhoi.getText())));
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
        while (blockIterator.hasNext()) {
            Block block = blockIterator.next();

            modelScoreManage.addRow(new Object[]{true, block.getID(), block.getName()});
        }


    }

    public List<Block> a = new ArrayList<>();

    private JPanel panel1;
    private JButton insert;
    private JButton update;
    private JButton delete;
    private JButton refreshButton;
    private JTable table1;
    private JCheckBox check;
    private JCheckBox checkAll;
    private JTextField MaKhoi;
    private JTextField TenKhoi;
    private JTextField TextSearch;
    private JButton searchButton;
    private JCheckBox searchByNameCheckBox;
    private JComboBox comboBox2;
    private JButton exportButton;
    private JPanel e;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        MaKhoi = new JTextField(8);
        table1 = new JTable() {

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
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
    }
}
