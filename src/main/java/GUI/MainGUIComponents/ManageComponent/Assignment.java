package GUI.MainGUIComponents.ManageComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Assignment extends JInternalFrame{
    public Assignment(){
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Phân Công");
        modelScoreManage.addColumn("Số Học Kì");
        modelScoreManage.addColumn("Năm Giảng Dạy");
        modelScoreManage.addColumn("Mã Giáo viên bộ môn");
        modelScoreManage.addColumn("Mã Lớp");
        table1.setModel(modelScoreManage);
        setTitle("Phân công");
        setContentPane(AssignmentPanel);
        setVisible(true);
    }



    private JPanel AssignmentPanel;
    private JPanel panel1;
    private JCheckBox chọnTấtCảCheckBox;
    private JCheckBox bỏChọnCheckBox;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton tảiLạiButton;
    private JButton xóaButton;
    private JButton sửaButton;
    private JButton thêmButton;
    private JButton chọnẢnhButton;
    private JTable table1;
}
