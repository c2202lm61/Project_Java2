package GUI.MainGUIComponents.ManageComponent;

import Controllers.Authorization.Authorization;
import Controllers.SortA_Z;
import Controllers.SortZ_A;
import Controllers.Validation;
import DAO.Access.TypeScoreHandle;
import Model.TypeScore;
import javax.swing.*;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TypescoreManagement extends JInternalFrame {
    public List<TypeScore> a = new ArrayList<>();
    public TypescoreManagement(){
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Loại điểm");
        modelScoreManage.addColumn("Tên Loại điểm");
        table1.setModel(modelScoreManage);

        refreshTable();


        // set layout (phan nay ko duoc code) -----------------------
        setBorder(new LineBorder(new Color(168, 167, 167, 226),1));
        setContentPane(panel1);
        setVisible(true);
        //---------------------------------
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!Authorization.getPermisionForTypeScore()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if(name.getText().length()<2){
                    JOptionPane.showMessageDialog(null,"Tên loại điềm không hợp lệ");
                    return;
                }
                TypeScore typeScore = new TypeScore();
                try{
                    if(String.valueOf(ts_id.getText()).equals("")){
                        typeScore.setID(-1);
                    }else{
                        int id = Integer.valueOf(ts_id.getText());
                        typeScore.setID(id);
                    }
                    String tenloaidiem = name.getText();
                    typeScore.setName(tenloaidiem);
                    TypeScoreHandle typeScoreHandle = new TypeScoreHandle();
                    if (typeScoreHandle.INSERT(typeScore))
                        JOptionPane.showMessageDialog(null,"Thêm dữ liệu thành công");
                    else JOptionPane.showMessageDialog(null,"Thêm dữ liệu không thành công");
                    refreshTable();
                }catch (NumberFormatException e1){
                    throw new RuntimeException(e1);
                }

            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = table1.rowAtPoint(e.getPoint());
                String id = String.valueOf( table1.getValueAt(clickedRow, 1));
                String ten = String.valueOf( table1.getValueAt(clickedRow,2));
                ts_id.setText(id);
                name.setText(ten);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Authorization.getPermisionForTypeScore()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if(!Validation.isNumeric(ts_id.getText())){
                    JOptionPane.showMessageDialog(null,"ID không hợp lệ");
                    return;
                }
                int id = Integer.valueOf(ts_id.getText());
                TypeScoreHandle typeScoreHandle = new TypeScoreHandle();
                if (typeScoreHandle.DELETE(id)){
                    JOptionPane.showMessageDialog(null,"Xóa dữ liệu thành công");
                }else JOptionPane.showMessageDialog(null,"Xóa dũ liệu không thành công");
                ts_id.setText(null);
                name.setText(null);
                refreshTable();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Authorization.getPermisionForTypeScore()){
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền truy  cập");
                    return;
                }
                if(!Validation.isNumeric(ts_id.getText())){
                    JOptionPane.showMessageDialog(null,"ID không hợp lệ");
                    return;
                }
                if(name.getText().length()<2){
                    JOptionPane.showMessageDialog(null,"Tên loại điềm không hợp lệ");
                    return;
                }
                TypeScore typeScore = new TypeScore();
                int id = Integer.valueOf(ts_id.getText());
                String tenloaidiem = name.getText();
                typeScore.setID(id);
                typeScore.setName(tenloaidiem);
                TypeScoreHandle typeScoreHandle = new TypeScoreHandle();
                if (typeScoreHandle.UPDATE(typeScore)) JOptionPane.showMessageDialog(null,"Cập nhật dũ liệu thành công");
                else  JOptionPane.showMessageDialog(null,"Cập nhật dữ liệu không thành công");
                refreshTable();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedIndex()==0) {
                    Collections.sort(a, new SortA_Z());
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0); // Clear existing data in the table
                    Iterator<TypeScore> typeScoreIterator = a.iterator();
                    while (typeScoreIterator.hasNext()){
                        TypeScore typeScore = typeScoreIterator.next();
                        modelScoreManage.addRow(new Object[]{true,typeScore.getID(),typeScore.getName()});
        }

                }
                else if (comboBox1.getSelectedIndex()==1){
                    Collections.sort(a, new SortZ_A());
                    DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                    modelScoreManage.setRowCount(0); // Clear existing data in the table

                    Iterator<TypeScore> typeScoreIterator = a.iterator();
                    while (typeScoreIterator.hasNext()){
                        TypeScore typeScore = typeScoreIterator.next();
                        modelScoreManage.addRow(new Object[]{true,typeScore.getID(),typeScore.getName()});
        }
                }
            }
        });
    }
    public void refreshTable(){
        TypeScoreHandle TypeScoreHandle = new TypeScoreHandle();
        DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
        modelScoreManage.setRowCount(0);
        this.a = null;
        try {
            a = TypeScoreHandle.SELECT("SELECT * FROM type_score");
        } catch (SQLException e){ throw  new RuntimeException(e);}
        Iterator<TypeScore> typeScoreIterator = a.iterator();
        while (typeScoreIterator.hasNext()){
            TypeScore typeScore = typeScoreIterator.next();
            modelScoreManage.addRow(new Object[]{true,typeScore.getID(),typeScore.getName()});
        }
    }
    private JPanel panel1;
    private JTable table1;
    private JButton chọnẢnhButton;
    private JButton insert;
    private JButton update;
    private JButton tảiLạiButton;
    private JButton delete;
    private JComboBox comboBox1;
    private JTextField ts_id;
    private JTextField name;
    private JCheckBox chọnTấtCảCheckBox;
    private JCheckBox chọnCheckBox;
}
