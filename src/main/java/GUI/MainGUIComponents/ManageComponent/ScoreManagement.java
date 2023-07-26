package GUI.MainGUIComponents.ManageComponent;


import DAO.Access.ScoreStudentHandle;
import DAO.Access.TypeScoreHandle;
import Model.Score;
import Model.TypeScore;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ScoreManagement extends JFrame {

    private JPanel scoreManageComponent;

    private JTable table1;
    private JTextField value;
    private JButton chọnẢnhButton;
    private JButton tảiLạiButton;
    private JButton delete;
    private JButton update;
    private JButton insert;
    private JTextField MaDiem;
    private JComboBox loaidiem;
    private JComboBox scmSubjectName;
    public List<Score> a = new ArrayList<>();


    public ScoreManagement(int ss_id) {


        //------------------------------------------------------
        DefaultTableModel modelScoreManage = new DefaultTableModel();
        modelScoreManage.addColumn("Chọn");
        modelScoreManage.addColumn("Mã Điểm");
        modelScoreManage.addColumn("Mã Môn Học Của Học Sinh");
        modelScoreManage.addColumn("Loại Điểm");
        modelScoreManage.addColumn("Điểm");
        //-----------------------------------------------
        table1.setModel(modelScoreManage);
        refreshTable(ss_id);

        //-----------------------------------------------------------------
        add(scoreManageComponent);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);


        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Score score = new Score();

                try {
                    if(String.valueOf(MaDiem.getText()).equals("")){
                        score.setScoreID(-1);
                    }else {
                        int maChoDiem = Integer.valueOf(MaDiem.getText());
                        score.setScoreID(maChoDiem);
                    }
                    score.setStudentSubjectID(ss_id);
                    score.setTypeScoreID((int)loaidiem.getSelectedItem());
                    score.setScoreValue(Float.valueOf(value.getText()));
                    ScoreStudentHandle scoreStudentHandle = new ScoreStudentHandle();
                    scoreStudentHandle.INSERT(score);
                    refreshTable(ss_id);
                } catch (NumberFormatException e1){
                    throw new RuntimeException(e1);
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = table1.rowAtPoint(e.getPoint());
                 String id = String.valueOf( table1.getValueAt(clickedRow, 1));

                 int type_id =(int)( table1.getValueAt(clickedRow,3));
                 String Value =String.valueOf( table1.getValueAt(clickedRow,4));


                 MaDiem.setText(id);
                 loaidiem.setSelectedItem(type_id);
                 value.setText(Value);
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Score score = new Score();
                int id = Integer.valueOf(MaDiem.getText());

                score.setScoreID(id);
                score.setStudentSubjectID(ss_id);
                score.setTypeScoreID((int)(loaidiem.getSelectedItem()));
                score.setScoreValue(Float.valueOf(value.getText()));
                ScoreStudentHandle scoreStudentHandle = new ScoreStudentHandle();
                scoreStudentHandle.UPDATE(score);
                refreshTable(ss_id);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.valueOf(MaDiem.getText());
                ScoreStudentHandle scoreStudentHandle = new ScoreStudentHandle();
                scoreStudentHandle.DELETE(id);
                MaDiem.setText(null);
                 loaidiem.setSelectedItem(null);
                 value.setText(null);
                refreshTable(ss_id);
            }
        });
    }
    public void refreshTable(int ss_id) {
        List<TypeScore> maloaidiem = null;
        TypeScoreHandle typeScoreHandle = new TypeScoreHandle();
        try {
            maloaidiem = typeScoreHandle.SELECT("SELECT * FROM `type_score`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(TypeScore obj1: maloaidiem){
            loaidiem.addItem(obj1.getID());
        }


        ScoreStudentHandle scoreStudentHandle = new ScoreStudentHandle();

        DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
        modelScoreManage.setRowCount(0); // Clear existing data in the table

        this.a = null;
        try {
            a = scoreStudentHandle.SELECT("SELECT * FROM score_student WHERE `ss_id` = "+ss_id+"");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Iterator<Score> scoreIterator = a.iterator();
        while (scoreIterator.hasNext()){
            Score score = scoreIterator.next();
            modelScoreManage.addRow(new Object[]{true,score.getScoreID(),score.getStudentSubjectID(),score.getTypeScoreID(),score.getScoreValue()});
        }


    }
}
