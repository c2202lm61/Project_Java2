package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.ClassHandle;
import DAO.Access.GrantHandle;
import Model.Block;
import Model.MClass;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public class ClassManagement extends JInternalFrame{
    public ClassManagement(){
        ClassHandle grantHandle = new ClassHandle();
        List<MClass> a = null;
        try {
            a = grantHandle.SELECT("SELECT * FROM ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Lớp");
        modelScoreManage.addColumn("Mã khối");
        labelTable1.setModel(modelScoreManage);

        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(ClassManagementPanel);
        setVisible(true);
    }
    private JPanel ClassManagementPanel;
    private JTable labelTable1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
}
