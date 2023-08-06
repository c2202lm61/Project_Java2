package GUI.MainGUIComponents.ManageComponent;

import Controllers.Validation;
import DAO.Access.InstructorRoleHandle;
import DAO.Access.RoleHandle;
import DAO.JDBCDriver;
import GUI.ComboBoxItem;
import Model.InstructorRole;
import Model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import DAO.Access.InstructorRoleHandle;

public class RoleManagement extends JInternalFrame {

    public RoleManagement(){
        updateClassCombobox();
        updateRoleCombobox();
        updateTableAsigment();
        setContentPane(mainPanel);
        setVisible(true);
        chooseTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(chooseTable.getSelectedIndex() == 0){
                    updateTableAsigment();
                    updateRoleCombobox();
                }else {
                    updateTableRole();
                    updateRoleCombobox();
                };
            }
        });
        roleInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!Validation.isFullName(roleName.getText())){
                    JOptionPane.showMessageDialog(null,"Tên không hợp lệ");
                    return;
                }
                Role role = new Role();
                role.setName(roleName.getText());
                new RoleHandle().INSERT(role);
                JOptionPane.showMessageDialog(null,"Thêm dữ liệu thành công");
                updateTableRole();
                updateRoleCombobox();
            }
        });
        RoleUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!Validation.isNumeric(roleID.getText())){
                    JOptionPane.showMessageDialog(null,"ID không hợp lệ");
                    return;
                }
                if(!Validation.isFullName(roleName.getText())){
                    JOptionPane.showMessageDialog(null,"Tên không hợp lệ");
                    return;
                }
                Role  role = new Role();
                role.setId(Integer.parseInt(roleID.getText()));
                role.setName(roleName.getText());
                new  RoleHandle().UPDATE(role);
                JOptionPane.showMessageDialog(null,"Cập nhật dữ liệu thành công");
                updateTableRole();
                updateRoleCombobox();
            }
        });
        RoleDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!Validation.isNumeric(roleID.getText())){
                    JOptionPane.showMessageDialog(null,"ID không hợp lệ");
                    return;
                }
                new  RoleHandle().DELETE(Integer.parseInt(roleID.getText()));
                JOptionPane.showMessageDialog(null,"Xóa dữ liệu thành công");
                updateTableRole();
                updateRoleCombobox();
            }
        });
        insertIR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!Validation.isNumeric(insID.getText())){
                    JOptionPane.showMessageDialog(null,"ID không hợp lệ");
                    return;
                }
                InstructorRole instructorRole = new InstructorRole();
                instructorRole.setTclass_id((int)ClassID.getSelectedItem());
                instructorRole.setID_NUMBER(Integer.parseInt(insID.getText()));
                ComboBoxItem comboBoxItem =  (ComboBoxItem) roleCombobox.getSelectedItem();
                instructorRole.setRole_id((Integer) comboBoxItem.getHiddenValue());
                new InstructorRoleHandle().INSERT(instructorRole);
                JOptionPane.showMessageDialog(null,"Thêm dữ liệu thành công");
                updateTableAsigment();
            }
        });
        deleteIR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new InstructorRoleHandle().DELETE(Integer.parseInt(insRoleID.getText()));
                JOptionPane.showMessageDialog(null,"Xóa dữ liệu thành công");
                updateTableAsigment();
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(chooseTable.getSelectedIndex() == 0){
                    int clickedRow = table1.rowAtPoint(e.getPoint());
                    int teachid =  Integer.parseInt(String.valueOf( table1.getValueAt(clickedRow, 2)));
                    try {
                        List<InstructorRole> a = new InstructorRoleHandle().SELECT("SELECT * FROM `instructor_role` WHERE `tclass_id` =" +teachid);
                        for(InstructorRole instructorRole : a){
                            roleCombobox.setSelectedItem(instructorRole.getRole_id());
                            insID.setText(String.valueOf(instructorRole.getID_NUMBER()));
                        }

                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    }
                }else if(chooseTable.getSelectedIndex() == 1){
                    int clickedRow = table1.rowAtPoint(e.getPoint());
                    roleID.setText( String.valueOf( table1.getValueAt(clickedRow, 0)));
                    roleName.setText(String.valueOf( table1.getValueAt(clickedRow,1)));
                }
            }
        });
    }
    public void updateTableRole(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã  vai trò");
        tableModel.addColumn("Tên vai trò");
        try {
            List<Role> roleList = new RoleHandle().SELECT("SELECT * FROM `role`");
            Iterator<Role>  roleIterator = roleList.iterator();
            while (roleIterator.hasNext()){
               Role role  = roleIterator.next();
               tableModel.addRow(new Object[]{role.getId(),role.getName()});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        table1.setModel(tableModel);
    }

    public void updateTableAsigment(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã  giáo  viên");
        tableModel.addColumn("Tên giáo viên");
        tableModel.addColumn("Mã vai  trò đảm nhiệm");
        tableModel.addColumn("Vai trò đảm nhiệm");
        try {
            ResultSet resultSet = JDBCDriver.ExecQuery("SELECT ins.ID_NUMBER AS insID,ins.name  AS insName, insRole.Role_id, insRole.tclass_id  FROM instructor AS ins LEFT  JOIN instructor_role AS insRole ON ins.ID_NUMBER =  insRole.ID_NUMBER");
            while (resultSet.next()){
                tableModel.addRow(new Object[]{resultSet.getString("insID"), resultSet.getString("insName"),resultSet.getString("tclass_id"), resultSet.getString("Role_id")});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        table1.setModel(tableModel);
    }
    public void updateRoleCombobox(){
        roleCombobox.removeAllItems();
        try {
            ResultSet resultSet = JDBCDriver.ExecQuery("SELECT * FROM role");
            while (resultSet.next()) {
                Role role = new Role();
                role.setName(resultSet.getString("role_name"));
                role.setId(resultSet.getInt("id"));
                // Thêm mục vào ComboBox
                ComboBoxItem comboBoxItem = new ComboBoxItem(role.getName(), role.getId());
                roleCombobox.addItem(comboBoxItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void updateClassCombobox() {
        ResultSet resultSet = null;
        try {
            resultSet = JDBCDriver.ExecQuery("SELECT * FROM  class");
            while (resultSet.next()){
                ClassID.addItem(resultSet.getInt("class_code"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private JPanel mainPanel;
    private JTable table1;
    private JButton insertIR;
    private JButton deleteIR;
    private JTextField roleID;
    private JComboBox roleCombobox;
    private JTextField roleName;
    private JButton roleInsert;
    private JButton RoleDelete;
    private JButton RoleUpdate;
    private JComboBox chooseTable;
    private JTextField insID;
    private JComboBox ClassID;
    private JLabel insRoleID;

    private void createUIComponents() {
        insRoleID = new JLabel();
    }
}
