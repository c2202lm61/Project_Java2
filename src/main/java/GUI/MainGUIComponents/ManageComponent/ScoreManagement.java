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
    private JTextField MaHocSinh;
    private JTextField MaMonHoc;
    private JTextField Diem15;
    private JTextField Diem45;
    private JTextField DiemThiGiuaKi1;
    private JTextField DiemThiCuoiKi1;
    private JTextField DiemThiGiuaKi2;
    private JTextField DiemThiCuoiKi2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ScoreManagement");
        frame.setContentPane(new ScoreManagement().scoreManageComponent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ScoreManagement(){
        
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã học sinh");
        modelScoreManage.addColumn("Mã Môn Học");
        modelScoreManage.addColumn("Điểm 15p");
        modelScoreManage.addColumn("Điểm 45p");
        modelScoreManage.addColumn("Điểm thi giữa kì 1");
        modelScoreManage.addColumn("Điểm thi cuối kì 1");
        modelScoreManage.addColumn("Điểm thi giữa kì 2");
        modelScoreManage.addColumn("Điểm thi cuối kì 2");
        table1.setModel(modelScoreManage);

        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(scoreManageComponent);
        setVisible(true);
    }
}
