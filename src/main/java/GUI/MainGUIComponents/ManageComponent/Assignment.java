package GUI.MainGUIComponents.ManageComponent;

import Controllers.Authorization.Authorization;
import Controllers.ExportData;
import Controllers.GetDataFromTable;
import Controllers.Validation;
import DAO.Access.ClassHandle;
import DAO.Access.InstructorSubjectHandle;
import DAO.Access.TeacherClassHandle;
import Model.InstructorSubject;
import Model.MClass;
import Model.TeacherClass;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Assignment extends JInternalFrame{
    public List<TeacherClass> a = new ArrayList();
    public Assignment(){
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
                if(!Authorization.getPermisionForAsignment()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if(!Validation.isNumeric(tc_id.getText())){
                    JOptionPane.showMessageDialog(null,"Mã phân công không hợp lệ");
                    return;
                }
                if(!Validation.isNumeric(textField2.getText())){
                    JOptionPane.showMessageDialog(null,"Số  học kì không hợp lệ");
                    return;
                }
                try{
                    TeacherClass teacherClass = new TeacherClass();
                    if(String.valueOf(tc_id.getText()).equals("")){
                        teacherClass.setId_tc(-1);
                    }else {
                        int maChoDiem = Integer.valueOf(tc_id.getText());
                        teacherClass.setId_tc(maChoDiem);
                    }
                    TeacherClassHandle teacherClassHandle = new TeacherClassHandle();
                    String sohocky = textField2.getText();
                    int MaGVBM = (int) comboBox2.getSelectedItem();
                    int MaLH = (int) comboBox3.getSelectedItem();

                    teacherClass.setNumberofsemester(sohocky);
                    teacherClass.setClass_code(MaLH);
                    teacherClass.setID_Teach(MaGVBM);
                    teacherClassHandle.INSERT(teacherClass);
                    JOptionPane.showMessageDialog(null,"Thêm nhiệm vụ thành công");
                    refreshTable();
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,"Đã đảm nhiệm nhiệm vụ này");
                    return;
                }

            }
            });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = table1.rowAtPoint(e.getPoint());
                String id = String.valueOf( table1.getValueAt(clickedRow, 1));
                String SohocKy =String.valueOf( table1.getValueAt(clickedRow,2));
                int Magvbm = (int)( table1.getValueAt(clickedRow,3));
                int Malop = (int)( table1.getValueAt(clickedRow,4));
                tc_id.setText(id);
                textField2.setText(SohocKy);
                comboBox2.setSelectedItem(Magvbm);
                comboBox3.setSelectedItem(Malop);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Authorization.getPermisionForAsignment()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                try{
                    int id = Integer.valueOf(tc_id.getText());
                    if(id<1){
                        JOptionPane.showMessageDialog(null, "Mã Phân Công không hợp lệ");
                        return;
                    }
                    if(new TeacherClassHandle().DELETE(id))
                        JOptionPane.showMessageDialog(null,"Xóa dữ liệu thành công");
                    else JOptionPane.showMessageDialog(null,"Xóa dữ liệu không thành công");


                    tc_id.setText(null);
                    textField2.setText(null);
                    comboBox2.setSelectedItem(null);
                    comboBox3.setSelectedItem(null);
                    refreshTable();
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "Mã Phân Công không hợp lệ");
                }

            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Authorization.getPermisionForAsignment()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if(!Validation.isNumeric(tc_id.getText())){
                    JOptionPane.showMessageDialog(null,"Mã phân công không hợp lệ");
                    return;
                }
                if(!Validation.isNumeric(textField2.getText())){
                    JOptionPane.showMessageDialog(null,"Số học kì không hợp lệ");
                    return;
                }
                try{
                    TeacherClassHandle teacherClassHandle = new TeacherClassHandle();
                    int id = Integer.valueOf(tc_id.getText());
                    if(id<1){
                        JOptionPane.showMessageDialog(null, "Mã Phân Công không hợp lệ");
                        return;
                    }
                    String sohocky = textField2.getText();
                    int MaGVBM = (int) comboBox2.getSelectedItem();
                    int MaLH = (int) comboBox3.getSelectedItem();
                    TeacherClass teacherClass = new TeacherClass();
                    teacherClass.setId_tc(id);
                    teacherClass.setNumberofsemester(sohocky);
                    teacherClass.setClass_code(MaLH);
                    teacherClass.setID_Teach(MaGVBM);
                    teacherClassHandle.UPDATE(teacherClass);
                    JOptionPane.showMessageDialog(null,"Cập nhật nhiệm vụ thành công");
                    refreshTable();
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ExportData.exportData(table1,1);
            }
        });
        checkAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                table1.setModel(GetDataFromTable.setAllCheckboxFromTable(table1,checkAll.isSelected()));
            }
        });
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!Validation.isNumeric(tc_id.getText())) return;
                table1.setModel(GetDataFromTable.setCheckboxTableFromID(table1,check.isSelected(),Integer.parseInt(tc_id.getText())));
            }
        });
    }
    public void refreshTable() {
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
    private JCheckBox checkAll;
    private JCheckBox check;
    private JComboBox comboBox1;
    private JTextField tc_id;
    private JTextField textField2;
    private JButton tảiLạiButton;
    private JButton delete;
    private JButton update;
    private JButton insert;
    private JButton exportButton;
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
