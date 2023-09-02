package GUI.MainGUIComponents.ManageComponent;

import Controllers.Authorization.Authorization;
import Controllers.Validation;
import DAO.Access.InstructorHandle;
import DAO.Access.InstructorRoleHandle;
import DAO.Access.RoleHandle;
import GUI.ComboBoxItem;
import GUI.MainGUI;
import Model.Instructor;
import Model.InstructorRole;
import Model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class AuthorizationManagement extends JInternalFrame{
    public AuthorizationManagement() {
        updateRoleCombobox();
        DefaultTableModel tableRole = new DefaultTableModel();
        tableRole.addColumn("ID");
        tableRole.addColumn("Vai trò");
        roleTable.setModel(tableRole);
        updateInsTable();
        setContentPane(panel1);
        setVisible(true);
        insTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = insTable.rowAtPoint(e.getPoint());
                String id=String.valueOf(insTable.getValueAt(clickedRow, 0));
                insID.setText(id);
                updateRoleTable(Integer.parseInt(id));
            }
        });

        roleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = roleTable.rowAtPoint(e.getPoint());
                String id=String.valueOf(roleTable.getValueAt(clickedRow, 0));
                deleteRole.setText(id);
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!Authorization.getPermisionForPosition()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if (!Validation.isNumeric(insID.getText())){
                    JOptionPane.showMessageDialog(null,"ID người dùng không hợp lệ");
                    return;
                }
                ComboBoxItem  item =  (ComboBoxItem) insRole.getSelectedItem();
                int roleID = (int) item.getHiddenValue();
                int insiD = Integer.parseInt(insID.getText());
                Boolean result = new InstructorRoleHandle().INSERT(new InstructorRole(roleID,insiD));
                if(result) JOptionPane.showMessageDialog(null,"Ủy quyền thành công");
                else JOptionPane.showMessageDialog(null,"Uỷ quyền thất bại");
                updateRoleTable(insiD);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!Authorization.getPermisionForPosition()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                int IDUser = 0;
                if(Validation.isNumeric(insID.getText()))
                    IDUser = Integer.parseInt(insID.getText());
                int ID = Integer.parseInt(deleteRole.getText());
                System.out.println(ID);
                Boolean result = new InstructorRoleHandle().DELETE(ID);
                deleteRole.setText("");
                if (result) JOptionPane.showMessageDialog(null,"Xóa dữ liệu thành công");
                else JOptionPane.showMessageDialog(null,"Xóa dữ liệu không thành công");
                updateRoleTable(IDUser);
            }
        });
        asignmentListButton.addComponentListener(new ComponentAdapter() {
        });
        asignmentListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new UserPermissions();
            }
        });
    }
    public void updateInsTable(){
        DefaultTableModel tableTeacher = new DefaultTableModel();
        tableTeacher.addColumn("ID");
        tableTeacher.addColumn("Tên giáo viên");
        try {
            List<Instructor>  list = new InstructorHandle().SELECT("SELECT * FROM `instructor`");
            for (Instructor instructor: list) {
                tableTeacher.addRow(new Object[]{instructor.getID_NUMBER(),instructor.getName()});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        insTable.setModel(tableTeacher);
    }
    public void updateRoleTable(int ID){
        DefaultTableModel tableRole = new DefaultTableModel();
        tableRole.addColumn("ID");
        tableRole.addColumn("Vai trò");
        String sql = "SELECT  ir.id,r.role_name FROM instructor_role AS ir INNER JOIN role AS r ON ir.Role_id = r.id WHERE ID_NUMBER = "+ID;
        List<InstructorRole> instructorRoleList = new InstructorRoleHandle().SELECT(sql);
        for (InstructorRole instructorRole:instructorRoleList) {
            tableRole.addRow(new Object[]{instructorRole.getID(),instructorRole.getRole_name()});
        }
        roleTable.setModel(tableRole);
    }
    public void updateRoleCombobox(){
        List<Role> roleList = new RoleHandle().SELECT("SELECT * FROM role");
        for (Role role:roleList) {
            ComboBoxItem comboBoxItem = new ComboBoxItem(role.getName(),role.getId());
            insRole.addItem(comboBoxItem);
        }
    }

    private JPanel panel1;
    private JTable insTable;
    private JTable roleTable;
    private JTextField insID;
    private JComboBox insRole;
    private JButton deleteButton;
    private JButton insertButton;
    private JButton asignmentListButton;
    private JLabel deleteRole;
}
