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
        defaultTableModel.addColumn("Môn");
        defaultTableModel.addColumn("Khoi");
        defaultTableModel.addColumn("Điểm Hệ Số 1");
        defaultTableModel.addColumn("Điểm Hệ Số 2");
        defaultTableModel.addColumn("Điểm Hệ Số 3");
        defaultTableModel.addColumn("Điểm Hệ Số 3");
        defaultTableModel.addColumn("Tổng Điểm");
        table1.setModel(defaultTableModel);


        setLayout(new  GridLayout(1,1));
        add(panel1);
    }
    public void refresh(){

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
