package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.ClassHandle;
<<<<<<< HEAD
import DAO.Access.GrantHandle;
import Model.Block;
import Model.MClass;
=======
import Model.MClass;

>>>>>>> 2b1a9b6a986d8ce95692a5938cf4d5c13d8c50d1
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
<<<<<<< HEAD
import java.util.Collections;
import java.util.List;

=======
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
>>>>>>> 2b1a9b6a986d8ce95692a5938cf4d5c13d8c50d1

public class ClassManagement extends JInternalFrame{
    public List<MClass> a = new ArrayList<>();
    public ClassManagement(){
<<<<<<< HEAD
        ClassHandle grantHandle = new ClassHandle();
        List<MClass> a = null;
        try {
            a = grantHandle.SELECT("SELECT * FROM ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
=======




        // table view (phan nay ko duoc code)--------------
>>>>>>> 2b1a9b6a986d8ce95692a5938cf4d5c13d8c50d1
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Lớp");
        modelScoreManage.addColumn("Mã khối");
        modelScoreManage.addColumn("Mã Nhân Viên");
        // ----------------------
        labelTable1.setModel(modelScoreManage);

        refreshTable();
        // set layout (phan nay ko duoc code) -----------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(ClassManagementPanel);
        setVisible(true);
        //---------------------------------

    }
    public void refreshTable() {
        DefaultTableModel modelScoreManage = (DefaultTableModel) labelTable1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table

        ClassHandle classHandle = new ClassHandle();
        this.a = null;
        try {
            a = classHandle.SELECT("SELECT * FROM `class`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<MClass> classIterator = a.iterator();

        while(classIterator.hasNext()){
            MClass mclass = classIterator.next();
            modelScoreManage.addRow(new Object[]{true,mclass.getID(),mclass.getGrandID(),mclass.getManagerID()});
        }

    }
    private JComboBox MaKhoi;
    private JPanel ClassManagementPanel;
    private JTable labelTable1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JComboBox comboBox1;
    private JTextField Malop;
}
