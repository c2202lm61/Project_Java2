package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.ClassHandle;
import DAO.Access.InstructorSubjectHandle;
import DAO.Access.TeacherClassHandle;
import Model.InstructorSubject;
import Model.MClass;
import Model.TeacherClass;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Assignment extends JInternalFrame{
    public List<TeacherClass> a = new ArrayList();
    public Assignment(){


        //-------------------------------------------------------
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Phân Công");
        modelScoreManage.addColumn("Số Học Kì");
        modelScoreManage.addColumn("Mã Giáo viên bộ môn");
        modelScoreManage.addColumn("Mã Lớp");
        table1.setModel(modelScoreManage);
        refreshTable();
        //----------------------------------------------------------------


        //--------------------------------------------------------------------
        setTitle("Phân công");
        setContentPane(AssignmentPanel);
        setVisible(true);
        //------------------------------------------------------------
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeacherClassHandle teacherClassHandle = new TeacherClassHandle();
                String sohocky = textField2.getText();
                int MaGVBM = (int) comboBox2.getSelectedItem();
                int MaLH = (int) comboBox3.getSelectedItem();
                TeacherClass teacherClass = new TeacherClass();
                teacherClass.setNumberofsemester(sohocky);
                teacherClass.setClass_code(MaLH);
                teacherClass.setID_Teach(MaGVBM);
                teacherClassHandle.INSERT(teacherClass);
                refreshTable();
            }
        });
    }
    public void refreshTable() {
        InstructorSubjectHandle instructorSubjectHandle = new InstructorSubjectHandle();
        List<InstructorSubject> maGVBMlist = new ArrayList<>();


        try {
            maGVBMlist = instructorSubjectHandle.SELECT("SELECT * FROM `instructor_subject`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Add the IDs to the JComboBox
        for (InstructorSubject inst : maGVBMlist) {
            comboBox2.addItem(inst.getID_Teach());
        }

        ClassHandle classHandle = new ClassHandle();
        List<MClass> classList = new ArrayList<>();

        try {
            classList = classHandle.SELECT("SELECT * FROM `class`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Add the IDs to the JComboBox
        for (MClass lop : classList) {
            comboBox3.addItem(lop.getID());
        }


        DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table

        TeacherClassHandle teacherClassHandle = new TeacherClassHandle();
        this.a = null;
        try {
            a = teacherClassHandle.SELECT("SELECT * FROM `teach_class`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Iterator<TeacherClass> teacherClassIterator = a.iterator();
        while (teacherClassIterator.hasNext()){
            TeacherClass teacherClass = teacherClassIterator.next();
            modelScoreManage.addRow(new Object[]{true,teacherClass.getId_tc(),teacherClass.getNumberofsemester(),teacherClass.getID_Teach(),teacherClass.getClass_code()});
        }
    }


    DefaultTableModel model;

    private JPanel AssignmentPanel;
    private JPanel panel1;
    private JCheckBox chọnTấtCảCheckBox;
    private JCheckBox bỏChọnCheckBox;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton tảiLạiButton;
    private JButton xóaButton;
    private JButton sửaButton;
    private JButton insert;
    private JButton chọnẢnhButton;
    private JTable table1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

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
