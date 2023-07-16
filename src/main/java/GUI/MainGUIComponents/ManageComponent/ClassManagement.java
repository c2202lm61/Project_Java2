package GUI.MainGUIComponents.ManageComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClassManagement extends JInternalFrame{
    public ClassManagement(){
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
