package GUI.MainGUIComponents.ManageComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ScoreManagement extends JInternalFrame {
    private JPanel scoreManageComponent;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JComboBox comboBox1;
    private JTable table1;

    public ScoreManagement(){
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã giáo viên");
        modelScoreManage.addColumn("Họ và tên");
        modelScoreManage.addColumn("Mã  lớp chủ nhiệm");
        modelScoreManage.addColumn("Giới tính");
        modelScoreManage.addColumn("Số điện  thoại");
        modelScoreManage.addColumn("Ngày sinh");
        modelScoreManage.addColumn("Password");
        modelScoreManage.addColumn("Email");
        table1.setModel(modelScoreManage);

        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(scoreManageComponent);
        setVisible(true);
    }
}
