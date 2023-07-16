package GUI.MainGUIComponents.ManageComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SubjectTeachingTeacher extends JInternalFrame{
    public SubjectTeachingTeacher(){
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Giáo Viên Bộ Môn");
        modelScoreManage.addColumn("Mã Giáo Viên");
        modelScoreManage.addColumn("Mã Môn Học");
        table1.setModel(modelScoreManage);
        setTitle("");
        setContentPane(subjectTeachingTeacherPanel);
        setVisible(true);
    }


    private JPanel subjectTeachingTeacherPanel;
    private JButton tảiLạiButton;
    private JButton xóaButton;
    private JButton sửaButton;
    private JButton thêmButton;
    private JButton chọnẢnhButton;
    private JCheckBox chọnTấtCảCheckBox;
    private JCheckBox bỏChọnCheckBox;
    private JComboBox comboBox1;
    private JTextField MaGVBM;
    private JTextField MaGV;
    private JTextField MaMH;
    private JTable table1;
}
