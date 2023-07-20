package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.GrantHandle;
import Model.Block;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class BlockManagement extends JInternalFrame{
    public BlockManagement(){
        GrantHandle grantHandle = new GrantHandle();
        List<Block> a = null;
        try {
            a = grantHandle.SELECT("SELECT * FROM grants");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        Iterator<Block> blockIterator = a.iterator();

        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Khối");
        modelScoreManage.addColumn("Tên Khối");
        while (blockIterator.hasNext()){
            modelScoreManage.addRow(new Object[]{true,blockIterator.next().getID(),blockIterator.next().getName()});
        }
        table1.setModel(modelScoreManage);


        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(panel1);
        setVisible(true);
    }


    private JPanel panel1;
    private JButton chọnẢnhButton;
    private JButton thêmButton;
    private JButton sửaButton;
    private JButton xóaButton;
    private JButton tảiLạiButton;
    private JTable table1;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        textField1 =new JTextField(8);
    }
}
