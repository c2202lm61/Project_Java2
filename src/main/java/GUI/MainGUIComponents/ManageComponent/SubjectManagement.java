package GUI.MainGUIComponents.ManageComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SubjectManagement extends JInternalFrame{
    public SubjectManagement(){
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Môn Mọc");
        modelScoreManage.addColumn("Tên Môn Học");
        modelScoreManage.addColumn("Tín Chỉ");
        modelScoreManage.addColumn("ID Khối");
        table1.setModel(modelScoreManage);
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(panel1);
        setVisible(true);
    }
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JButton xóaButton;
    private JButton thêmButton;
    private JButton sửaButton;
    private JButton resetButton;
    private JButton chọnButton;
    private JButton bỏChọnButton;
    private JTable table1;
    private JComboBox comboBox2;
    private JButton zAButton;
    private JButton aZButton;
}
