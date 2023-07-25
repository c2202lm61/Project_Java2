package GUI.MainGUIComponents.ManageComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ScoreManagement extends JInternalFrame {
    private JPanel scoreManageComponent;
    private JButton button1;
    private JButton insert;
    private JButton update;
    private JButton delete;
    private JButton button5;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JComboBox comboBox1;
    private JTable table1;
    private JTextField id;
    private JComboBox typeScore;
    private JTextField value;
    private JLabel Điểm;


    public static void main(String[] args) {
        JFrame frame = new JFrame("ScoreManagement");
        frame.setContentPane(new ScoreManagement().scoreManageComponent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ScoreManagement(){


        //------------------------------------------------------
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
        //-----------------------------------------------
        table1.setModel(modelScoreManage);

        //-----------------------------------------------------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(scoreManageComponent);
        setVisible(true);
        //----------------------------------------------------------------------
    }
}
