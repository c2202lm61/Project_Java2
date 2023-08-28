package GUI.MainGUIComponents.ManageComponent;

import Controllers.Authorization.Authorization;
import Controllers.Validation;
import DAO.Access.RoleHandle;
import Model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class RoleManagement extends JInternalFrame {

    public RoleManagement() {
        updateTable();
        setContentPane(mainPanel);
        setVisible(true);
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = table1.rowAtPoint(e.getPoint());
                String id = String.valueOf( table1.getValueAt(clickedRow, 0));
                String name =String.valueOf( table1.getValueAt(clickedRow,1));
                roleID.setText(id);
                roleName.setText(name);
                studentCheck.setSelected((Boolean) table1.getValueAt(clickedRow,2));
                teacherCheck.setSelected((Boolean) table1.getValueAt(clickedRow,3));
                blockCheck.setSelected((Boolean) table1.getValueAt(clickedRow,4));
                classCheck.setSelected((Boolean) table1.getValueAt(clickedRow,5));
                subjectCheck.setSelected((Boolean) table1.getValueAt(clickedRow,6));
                asignmentCheck.setSelected((Boolean) table1.getValueAt(clickedRow,7));
                typescoreCheck.setSelected((Boolean) table1.getValueAt(clickedRow,8));
                roleCheck.setSelected((Boolean) table1.getValueAt(clickedRow,9));
                positionCheck.setSelected((Boolean) table1.getValueAt(clickedRow,10));
            }
        });
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!Authorization.getPermisionForRole()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if(!Validation.isFullName(roleName.getText())){
                    JOptionPane.showMessageDialog(null,"Tên không hợp lệ");
                    return;
                }
                new RoleHandle().INSERT(new Role(
                        roleName.getText(),
                        studentCheck.isSelected(),
                        teacherCheck.isSelected(),
                        blockCheck.isSelected(),
                        classCheck.isSelected(),
                        subjectCheck.isSelected(),
                        asignmentCheck.isSelected(),
                        typescoreCheck.isSelected(),
                        roleCheck.isSelected(),
                        positionCheck.isSelected()
                        ));
                JOptionPane.showMessageDialog(null,"Thêm dũ liệu thành công");
                updateTable();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!Authorization.getPermisionForRole()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if(!Validation.isNumeric(roleID.getText())){
                    JOptionPane.showMessageDialog(null,"ID không hợp lệ");
                    return;
                }
                if (!Validation.isFullName(roleName.getText())){
                    JOptionPane.showMessageDialog(null,"Tên không hợp lệ");
                    return;
                }
                boolean result = new RoleHandle().UPDATE(new Role(
                        Integer.parseInt(roleID.getText()),
                        roleName.getText(),
                        studentCheck.isSelected(),
                        teacherCheck.isSelected(),
                        blockCheck.isSelected(),
                        classCheck.isSelected(),
                        subjectCheck.isSelected(),
                        asignmentCheck.isSelected(),
                        typescoreCheck.isSelected(),
                        roleCheck.isSelected(),
                        positionCheck.isSelected()
                ));
                if (result)
                    JOptionPane.showMessageDialog(null,"Cập nhật dữ liệu thành công");
                else JOptionPane.showMessageDialog(null,"Cập nhật dữ liệu không thành công");
                updateTable();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!Authorization.getPermisionForRole()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
               Boolean result = new RoleHandle().DELETE(Integer.parseInt(roleID.getText()));
               if (result) JOptionPane.showMessageDialog(null,"Xóa dữ liệu thành công");
               else JOptionPane.showMessageDialog(null,"Xóa dữ liệu không thành công");
               updateTable();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                roleID.setText("");
                roleName.setText("");
                studentCheck.setSelected(false);
                teacherCheck.setSelected(false);
                blockCheck.setSelected(false);
                classCheck.setSelected(false);
                subjectCheck.setSelected(false);
                asignmentCheck.setSelected(false);
                typescoreCheck.setSelected(false);
                roleCheck.setSelected(false);
                positionCheck.setSelected(false);
                updateTable();
            }
        });
    }
    public void updateTable(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("Tên vai trò");
        tableModel.addColumn("Học sinh");
        tableModel.addColumn("Giáo viên");
        tableModel.addColumn("Khối");
        tableModel.addColumn("Lớp");
        tableModel.addColumn("Môn");
        tableModel.addColumn("Phân công");
        tableModel.addColumn("Loại điểm");
        tableModel.addColumn("Vai trò");
        tableModel.addColumn("Chức vụ");
        List<Role> roleList = new RoleHandle().SELECT("SELECT * FROM role");
        for (Role role:roleList) {
            tableModel.addRow(new Object[]{role.getId()
                    ,role.getName()
                    ,role.getStudent_mngs()
                    ,role.getTeach_mngs()
                    ,role.getBlock_mngs()
                    ,role.getClass_mngs()
                    ,role.getSubject_mngs()
                    ,role.getAsignment_mngs()
                    ,role.getTypescore_mngs()
                    ,role.getRole_mngs()
                    ,role.getPosition_mngs()});
        }
        table1.setModel(tableModel);
    }
    private JPanel mainPanel;
    private JTable table1;
    private JCheckBox blockCheck;
    private JCheckBox classCheck;
    private JCheckBox asignmentCheck;
    private JCheckBox typescoreCheck;
    private JCheckBox roleCheck;
    private JCheckBox positionCheck;
    private JCheckBox subjectCheck;
    private JTextField roleID;
    private JTextField roleName;
    private JButton updateButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton resetButton;
    private JCheckBox teacherCheck;
    private JCheckBox studentCheck;

    private void createUIComponents() {
        table1 = new JTable() {

            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
    }
}
