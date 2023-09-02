package GUI.MainGUIComponents.ManageComponent;

import DAO.JDBCDriver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPermissions extends JDialog{
    public UserPermissions(){
        updateTable();
        setSize(1000,400);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateTable();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }
    public  void updateTable() {
        DefaultTableModel  tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên người dùng");
        tableModel.addColumn("Vai trò");
        tableModel.addColumn("Học sinh");
        tableModel.addColumn("Giáo viên");
        tableModel.addColumn("Khối");
        tableModel.addColumn("Lớp");
        tableModel.addColumn("Môn");
        tableModel.addColumn("Nhiệm vụ");
        tableModel.addColumn("Loại điểm");
        tableModel.addColumn("Vai trò");
        tableModel.addColumn("Chức vụ");
        try {
            ResultSet rs = JDBCDriver.ExecQuery(
                    "SELECT i.ID_NUMBER,i.name,r.* FROM instructor AS i INNER JOIN instructor_role AS ir ON i.ID_NUMBER = ir.ID_NUMBER INNER JOIN role AS r ON ir.Role_id = r.id"
            );
            while (rs.next()){
                tableModel.addRow(new Object[]{
                        rs.getInt("ID_NUMBER"),
                        rs.getString("name"),
                        rs.getString("role_name"),
                        rs.getBoolean("student_mngs"),
                        rs.getBoolean("teach_mngs"),
                        rs.getBoolean("block_mngs"),
                        rs.getBoolean("class_mngs"),
                        rs.getBoolean("subject_mngs"),
                        rs.getBoolean("asignment_mngs"),
                        rs.getBoolean("type_score_mngs"),
                        rs.getBoolean("role_mngs"),
                        rs.getBoolean("position_mngs"),

                });
            }
            JDBCDriver.DestroyConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        table1.setModel(tableModel);

    }
    private JTable table1;
    private JPanel panel1;
    private JButton closeButton;
    private JButton reloadButton;

    private void createUIComponents() {
        table1 = new JTable() {

            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 1:
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
    }
}
