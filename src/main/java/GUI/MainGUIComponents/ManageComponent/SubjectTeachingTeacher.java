package GUI.MainGUIComponents.ManageComponent;

import Controllers.Authorization.Authorization;
import Controllers.ExportData;
import Controllers.GetDataFromTable;
import Controllers.Validation;
import DAO.Access.InstructorHandle;
import DAO.Access.InstructorSubjectHandle;
import DAO.Access.SubjectHandle;
import DAO.JDBCDriver;
import Model.Instructor;
import Model.InstructorSubject;
import Model.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubjectTeachingTeacher extends JInternalFrame{
    private List<InstructorSubject> a = new ArrayList<>();
    public SubjectTeachingTeacher(){

        List<Instructor> maGV = null;
        InstructorHandle instructorHandle = new InstructorHandle();
        try {
            maGV = instructorHandle.SELECT("SELECT * FROM `instructor`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Instructor obj: maGV){
            MaGV.addItem(obj.getID_NUMBER());
        }

        List<Subject> maMH = null;
        SubjectHandle subjectHandle = new SubjectHandle();
        try {
            maMH = subjectHandle.SELECT("SELECT * FROM `subject`");
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }
        for(Subject obj1: maMH){
            MaMH.addItem(obj1.getID());
        }
        //-----------------------------------------------
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Giáo Viên Bộ Môn");
        modelScoreManage.addColumn("Mã Giáo Viên");
        modelScoreManage.addColumn("Mã Môn Học");
        //-----------------------------------------------

        table1.setModel(modelScoreManage);
        refreshTable();
        //-----------------------------------------------
        setTitle("");
        setContentPane(subjectTeachingTeacherPanel);
        setVisible(true);
        //-----------------------------------------------
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Authorization.getPermisionForTeacher()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if(!Validation.isNumeric(MaGVBM.getText())){
                    JOptionPane.showMessageDialog(null,"Mã giáo viên bộ môn không hợp lệ");
                    return;
                }
                try {
                    ResultSet rs = JDBCDriver.ExecQuery("SELECT * FROM `instructor_subject` WHERE ID_Teach = "+MaGVBM.getText());
                    while (rs.next()){
                        JOptionPane.showMessageDialog(null,"Mã giáo viên bộ môn đã tồn tại");
                        return;
                    }
                    JDBCDriver.DestroyConnection();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                InstructorSubject instructorSubject = new InstructorSubject();
                try {
                    if(String.valueOf(MaGVBM.getText()).equals("")){
                        instructorSubject.setID_Teach(-1);
                    }else {
                        int id = Integer.valueOf(MaGVBM.getText());
                        instructorSubject.setID_Teach(id);
                    }
                    int magv = (int)MaGV.getSelectedItem();
                    int mamh = (int)MaMH.getSelectedItem();
                    instructorSubject.setSubject_code(mamh);
                    instructorSubject.setID_NUMBER(magv);
                    InstructorSubjectHandle instructorSubjectHandle = new InstructorSubjectHandle();
                    Boolean result = instructorSubjectHandle.INSERT(instructorSubject);
                    if (result)
                        JOptionPane.showMessageDialog(null,"Thêm dữ liệu thành công");
                    refreshTable();
                }
                catch (NumberFormatException e1){
                    throw new RuntimeException(e1);
                }

            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = table1.rowAtPoint(e.getPoint());
                String id = String.valueOf( table1.getValueAt(clickedRow, 1));
                int maMon = (int)( table1.getValueAt(clickedRow,2));
                int Tinchi  = (int)(table1.getValueAt(clickedRow,3));
                MaGVBM.setText(id);
                MaGV.setSelectedItem(maMon);
                MaMH.setSelectedItem(Tinchi);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Authorization.getPermisionForTeacher()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if(!Validation.isNumeric(MaGVBM.getText())){
                    JOptionPane.showMessageDialog(null,"Mã giáo viên bộ môn không hợp lệ");
                    return;
                }
                int id = Integer.valueOf(MaGVBM.getText());
                InstructorSubjectHandle instructorSubjectHandle = new InstructorSubjectHandle();
                if (instructorSubjectHandle.DELETE(id))
                    JOptionPane.showMessageDialog(null,"Xóa dữ liệu thành công");
                else JOptionPane.showMessageDialog(null,"Xóa dữ liệu không thành công");
                MaGVBM.setText(null);
                MaGV.setSelectedItem(null);
                MaMH.setSelectedItem(null);
                refreshTable();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Authorization.getPermisionForTeacher()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if(!Validation.isNumeric(MaGVBM.getText())){
                    JOptionPane.showMessageDialog(null,"Mã giáo viên bộ môn không hợp lệ");
                    return;
                }
                int id = Integer.valueOf(MaGVBM.getText());
                int magv = (int)MaGV.getSelectedItem();
                int mamh = (int)MaMH.getSelectedItem();
                InstructorSubject instructorSubject = new InstructorSubject();

                instructorSubject.setID_Teach(id);
                instructorSubject.setSubject_code(mamh);
                instructorSubject.setID_NUMBER(magv);
                InstructorSubjectHandle instructorSubjectHandle = new InstructorSubjectHandle();
                Boolean result = instructorSubjectHandle.UPDATE(instructorSubject);
                if (result) JOptionPane.showMessageDialog(null,"Cập nhật dữ liệu thành công");
                else JOptionPane.showMessageDialog(null,"Cập nhật dữ liệu không thành công");
                refreshTable();
            }
        });
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ExportData.exportData(table1,1);
            }
        });
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refreshTable();
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
                if (!Validation.isNumeric(MaGVBM.getText())) return;
                table1.setModel(GetDataFromTable.setCheckboxTableFromID(table1,check.isSelected(),Integer.parseInt(MaGVBM.getText())));
            }
        });
    }

    public void refreshTable() {
        DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table

        InstructorSubjectHandle instructorSubjectHandle = new InstructorSubjectHandle();
        this.a = null;
        try {
            a = instructorSubjectHandle.SELECT("SELECT * FROM `instructor_subject`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<InstructorSubject> instructorsubjectIterator = a.iterator();
        while (instructorsubjectIterator.hasNext()){
            InstructorSubject instructorSubject = instructorsubjectIterator.next();
            modelScoreManage.addRow(new Object[]{true,instructorSubject.getID_Teach(),instructorSubject.getID_NUMBER(),instructorSubject.getSubject_code()});
        }

    }
    private JPanel subjectTeachingTeacherPanel;
    private JButton reloadButton;
    private JButton delete;
    private JButton update;
    private JButton insert;
    private JButton exportButton;
    private JCheckBox checkAll;
    private JCheckBox check;
    private JComboBox comboBox1;
    private JTextField MaGVBM;
    private JTable table1;
    private JComboBox MaGV;
    private JComboBox MaMH;

    private void createUIComponents() {
        table1 = new JTable() {

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
