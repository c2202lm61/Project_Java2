package GUI.MainGUIComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static java.awt.BorderLayout.*;

public class JPannelViewScore extends JPanel {
    public JPannelViewScore(){
        setLayout(new BorderLayout());
        add(new JHeaderVCS(), NORTH);
        add(new JScrollPane(new JScoreTable()), CENTER);
        add(new JFooterVSC(),SOUTH);
    }
}
class JFooterVSC extends JPanel{
    public JFooterVSC(){
        setBackground(mainColor.CYAN);
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        setBorder(new EmptyBorder(10,20,10,20));
        JPanel panelgroup1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelgroup2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelgroup3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelgroup1.setBackground(mainColor.CYAN);
        JComboBox comboBox1 = new JComboBox();
        comboBox1.addItem("Item1");
        panelgroup1.add(comboBox1);
        add(panelgroup1);
        //
        panelgroup2.setBackground(mainColor.CYAN);
        JButton buttonAZ = new JButton("A-Z");
        JButton buttonZA = new JButton("Z-A");
        panelgroup2.add(buttonAZ);
        panelgroup2.add(buttonZA);
        add(panelgroup2);
        //
        panelgroup3.setBackground(mainColor.CYAN);
        JLabel labelsearch = new JLabel("Tìm kiếm");
        panelgroup3.add(labelsearch);
        JTextField textField = new JTextField(15);
        panelgroup3.add(textField);
        add(panelgroup3);
        JButton exportData = new JButton("Xuất dữ liệu");
        add(exportData);
    }
}
class JHeaderVCS extends  JPanel{
    public JHeaderVCS() {
        setBackground(new Color(32, 178, 170));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBorder(new EmptyBorder(20, 10, 20, 10));

        JButton viewMyScore = new JButton("Xem điểm của tôi");
        add(viewMyScore);

        // group1
        JPanel groupPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        groupPanel1.setBackground(mainColor.CYAN);
        JCheckBox checkBoxg1 = new JCheckBox();
        checkBoxg1.setBackground(mainColor.CYAN);
        groupPanel1.add(checkBoxg1);
        JLabel labelg1 = new JLabel("Label1");
        JComboBox comboBoxg1 = new JComboBox();
        comboBoxg1.addItem("Item1");
        groupPanel1.add(labelg1);
        groupPanel1.add(comboBoxg1);
        add(groupPanel1);

        // group2
        JPanel groupPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        groupPanel2.setBackground(mainColor.CYAN);
        JCheckBox checkBoxg2 = new JCheckBox();
        checkBoxg2.setBackground(mainColor.CYAN);
        groupPanel2.add(checkBoxg2);
        JLabel labelg2 = new JLabel("label2");
        JComboBox comboBoxg2 = new JComboBox<>();
        comboBoxg2.addItem("Item1");
        groupPanel2.add(labelg2);
        groupPanel2.add(comboBoxg2);
        add(groupPanel2);

        // group3
        JPanel groupPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        groupPanel3.setBackground(mainColor.CYAN);
        JCheckBox checkBoxg3 = new JCheckBox();
        checkBoxg3.setBackground(mainColor.CYAN);
        groupPanel3.add(checkBoxg3);
        JLabel labelg3 = new JLabel("label2");
        JComboBox comboBoxg3 = new JComboBox<>();
        comboBoxg3.addItem("Item1");
        groupPanel3.add(labelg3);
        groupPanel3.add(comboBoxg3);
        add(groupPanel3);

        // group4
        JPanel groupPanel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        groupPanel4.setBackground(mainColor.CYAN);
        JCheckBox checkBoxg4 = new JCheckBox();
        checkBoxg4.setBackground(mainColor.CYAN);
        groupPanel4.add(checkBoxg4);
        JLabel labelg4 = new JLabel("label4");
        groupPanel4.add(labelg4);
        add(groupPanel4);

        // button
        JButton jview = new JButton("Xem");
        add(jview);
    }
}
class JScoreTable extends JTable{
    public JScoreTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("STT");
        model.addColumn("Mã học sinh");
        model.addColumn("Tên học sinh");
        model.addColumn("Điểm 15p");
        model.addColumn("Điểm 45p");
        model.addColumn("Môn");
        model.addColumn("Lớp");
        model.addColumn("Khối");
        model.addColumn("Mã giáo viên");
        model.addRow(new Object[]{1,"a1023","Nguyen Van A",2,3,"math",1,"a","gv1234"});
        setModel(model);
    }
}