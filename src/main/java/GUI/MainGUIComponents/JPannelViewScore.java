package GUI.MainGUIComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JPannelViewScore extends JPanel {
    public JPannelViewScore(){
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("STT");
        defaultTableModel.addColumn("Mã học sinh");
        defaultTableModel.addColumn("Tên học sinh");
        defaultTableModel.addColumn("Điểm 15p");
        defaultTableModel.addColumn("Điểm 45p");
        defaultTableModel.addColumn("Môn");
        defaultTableModel.addColumn("Lớp");
        defaultTableModel.addColumn("Khối");
        defaultTableModel.addColumn("Mã giáo viên");
        table1.setModel(defaultTableModel);
        setLayout(new  GridLayout(1,1));
        add(panel1);
    }
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton aZButton;
    private JButton zAButton;
    private JButton xuấtDữLiệuButton;
    private JTextField textField1;
    private JTable table1;
    private JButton xemĐiểmCủaTôiButton;
    private JCheckBox khốiCheckBox;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JCheckBox lớpCheckBox;
    private JComboBox comboBox4;
    private JCheckBox mônCheckBox;
    private JButton xemButton;
}
