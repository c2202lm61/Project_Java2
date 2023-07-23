package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.TeacherClassHandle;
import Model.TeacherClass;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class Assignment extends JInternalFrame{

    public Assignment(){
        TeacherClassHandle teacherClassHandle = new TeacherClassHandle();
        List<TeacherClass> a = null;
        try {
            a = teacherClassHandle.SELECT("SELECT * FROM `teach_class`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<TeacherClass> teacherClassIterator = a.iterator();
        //-------------------------------------------------------
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Phân Công");
        modelScoreManage.addColumn("Số Học Kì");
        modelScoreManage.addColumn("Mã Giáo viên bộ môn");
        modelScoreManage.addColumn("Mã Lớp");
        //----------------------------------------------------------------
        while (teacherClassIterator.hasNext()){
            TeacherClass teacherClass = teacherClassIterator.next();
            modelScoreManage.addRow(new Object[]{true,teacherClass.getId_tc(),teacherClass.getNumberofsemester(),teacherClass.getID_Teach(),teacherClass.getClass_code()});
        }
        table1.setModel(modelScoreManage);
        //--------------------------------------------------------------------
        setTitle("Phân công");
        setContentPane(AssignmentPanel);
        setVisible(true);
        //------------------------------------------------------------
    }



    DefaultTableModel model;

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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        model  = new DefaultTableModel();

        model.addColumn("Chọn");
        model.addColumn("Tên");

        table1 = new JTable(model) {

            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
    }

}
