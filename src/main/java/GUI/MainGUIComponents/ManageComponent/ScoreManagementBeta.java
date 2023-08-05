package GUI.MainGUIComponents.ManageComponent;

import Controllers.SortZ_A;
import DAO.Access.*;
import DAO.ViewScore;
import GUI.ComboBoxItem;
import Model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ScoreManagementBeta extends JInternalFrame {
    Thread t1, t2, t3;

    List<ViewScoreBeta> scoreBetaList;
    public ScoreManagementBeta() {

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("MãSV");
        tableModel.addColumn("TênSV");
        tableModel.addColumn("Khối");
        tableModel.addColumn("Lớp");
        tableModel.addColumn("Mã Môn học");
        tableModel.addColumn("Điểm");
        tableModel.addColumn("Hệ số");
        table1.setModel(tableModel);
        updateTypeCore();
        updateBlock();
        updateClass();
        updateSubject();
        refreshTable();
        setContentPane(panel1);
        setVisible(true);
//        updateTypeCore();

        scrEval.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ComboBoxItem comboBoxSubject = (ComboBoxItem) scrSubject.getSelectedItem();
                    ComboBoxItem comboBoxTypeScore = (ComboBoxItem) scrTypeScore.getSelectedItem();
                    if (comboBoxSubject != null && comboBoxTypeScore != null) {
                        int subjectID = (int) comboBoxSubject.getHiddenValue();
                        int typeScoreID = (int) comboBoxTypeScore.getHiddenValue();

                        ViewScore viewScore = new ViewScore();
                        float diem;
                        try{
                            diem = Float.valueOf(scrScore.getText());
                        }catch (NumberFormatException e1){
                            JOptionPane.showMessageDialog(null, "Điểm không hợp lệ");
                            return;
                        }
                        viewScore.INSERT(Integer.valueOf(scrStudentID.getText()),subjectID,typeScoreID,diem);
                        refreshTable();
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0);
                        Iterator<ViewScoreBeta> scoreBetaIterator = scoreBetaList.iterator();
                        while (scoreBetaIterator.hasNext()) {
                            ViewScoreBeta viewScoreBeta = scoreBetaIterator.next();
                            if (viewScoreBeta.getStudentID() == Integer.valueOf(scrStudentID.getText()) && viewScoreBeta.getSubjectCode() == subjectID){
                                tableModel.addRow(new Object[]{
                                        viewScoreBeta.getStudentID(),
                                        viewScoreBeta.getName(),
                                        viewScoreBeta.getGrantID(),
                                        viewScoreBeta.getClassCode(),
                                        viewScoreBeta.getSubjectCode(),
                                        viewScoreBeta.getScoreValue(),
                                        viewScoreBeta.getScoreType()
                                });
                            }

                        }
                        refreshTable();
                    } else System.out.println("No item selected in the ComboBox.");
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Mã Học Sinh không hợp lệ");
                }


            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = table1.rowAtPoint(e.getPoint());
//                int
                int id = (int)( table1.getValueAt(clickedRow, 0));

                int blockID = (int)table1.getValueAt(clickedRow, 2);
                int classID = (int)table1.getValueAt(clickedRow, 3);
                double scoreValue = (double)table1.getValueAt(clickedRow,5);
                int typescore = (int)(table1.getValueAt(clickedRow,6));
                int subjectID = (int)(table1.getValueAt(clickedRow,4));


                tempViewScore.setStudentID(id);
                tempViewScore.setScoreType(typescore);
                tempViewScore.setScoreValue(scoreValue);
                tempViewScore.setName(String.valueOf(table1.getValueAt(clickedRow,1)));
                tempViewScore.setClassCode(classID);
                tempViewScore.setGrantID(blockID);
                tempViewScore.setSubjectCode(subjectID);


                scrStudentID.setText(String.valueOf(id));
                scrBlock.setSelectedItem(blockID);
                scrClass.setSelectedItem(classID);
                scrScore.setText(String.valueOf(scoreValue));

                for (int i = 0; i< scrTypeScore.getItemCount(); i++){
                    ComboBoxItem typescoreCB = (ComboBoxItem) scrTypeScore.getItemAt(i);
                    if((int)typescoreCB.getHiddenValue() == typescore ){
                        scrSubject.setSelectedItem(typescoreCB);
                        break;
                    }
                }

                if(subjectID != 0){
                    for (int i = 0; i< scrSubject.getItemCount(); i++){
                        ComboBoxItem subjectcb = (ComboBoxItem) scrSubject.getItemAt(i);
                        if((int)subjectcb.getHiddenValue() ==  subjectID){
                            scrSubject.setSelectedItem(subjectcb);
                            break;
                        }
                    }
                    DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                    tableModel.setRowCount(0);Iterator<ViewScoreBeta> scoreBetaIterator = scoreBetaList.iterator();
                    while (scoreBetaIterator.hasNext()) {
                        ViewScoreBeta viewScoreBeta = scoreBetaIterator.next();
                        if (viewScoreBeta.getStudentID() == Integer.valueOf(id) && viewScoreBeta.getSubjectCode() == subjectID){
                            tableModel.addRow(new Object[]{
                                    viewScoreBeta.getStudentID(),
                                    viewScoreBeta.getName(),
                                    viewScoreBeta.getGrantID(),
                                    viewScoreBeta.getClassCode(),
                                    viewScoreBeta.getSubjectCode(),
                                    viewScoreBeta.getScoreValue(),
                                    viewScoreBeta.getScoreType()
                            });
                        }

                    }
                }else {
                    DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                    tableModel.setRowCount(0);Iterator<ViewScoreBeta> scoreBetaIterator = scoreBetaList.iterator();
                    while (scoreBetaIterator.hasNext()) {
                        ViewScoreBeta viewScoreBeta = scoreBetaIterator.next();
                        if (viewScoreBeta.getStudentID() == Integer.valueOf(id)){
                            tableModel.addRow(new Object[]{
                                    viewScoreBeta.getStudentID(),
                                    viewScoreBeta.getName(),
                                    viewScoreBeta.getGrantID(),
                                    viewScoreBeta.getClassCode(),
                                    viewScoreBeta.getSubjectCode(),
                                    viewScoreBeta.getScoreValue(),
                                    viewScoreBeta.getScoreType()
                            });
                        }

                    }
                }

            }
        });

        scrStudentID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (hocsinhCheckBox.isSelected()){
                    refreshTable();
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (hocsinhCheckBox.isSelected()){
                    refreshTable();
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                if (hocsinhCheckBox.isSelected()){
                    refreshTable();
                }
            }
        });



        scrBlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(grantCheckBox.isSelected()){
                    updateClass();
                    updateSubject();
                    refreshTable();
                }

            }
        });



        scrClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lopCheckBox.isSelected()) {
                    refreshTable();
                }

            }
        });
        scrSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MonCheckBox.isSelected()) {
                    refreshTable();
                }
            }
        });
        grantCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        lopCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        MonCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        hocsinhCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        scrReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new ViewScore().DELETE(tempViewScore);
                    refreshTable();
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "Xóa Không Hợp Lệ");
                }

            }
        });
        update.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                try{
                    ComboBoxItem typescore = (ComboBoxItem) scrTypeScore.getSelectedItem();
                    new ViewScore().UPDATE(tempViewScore, (int)(typescore.getHiddenValue()),Float.valueOf(scrScore.getText()));
                    refreshTable();
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Điểm không hợp lệ");
                }

            }
        });
        aZButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(scoreBetaList, new SortZ_A());
                DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                modelScoreManage.setRowCount(0); // Clear existing data in the table
                Iterator<ViewScoreBeta> scoreBetaIterator = scoreBetaList.iterator();
                while (scoreBetaIterator.hasNext()) {
                    ViewScoreBeta viewScoreBeta = scoreBetaIterator.next();
                    tableModel.addRow(new Object[]{
                            viewScoreBeta.getStudentID(),
                            viewScoreBeta.getName(),
                            viewScoreBeta.getGrantID(),
                            viewScoreBeta.getClassCode(),
                            viewScoreBeta.getSubjectCode(),
                            viewScoreBeta.getScoreValue(),
                            viewScoreBeta.getScoreType()
                    });
                }
            }
        });
        zAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(scoreBetaList, new SortZ_A());
                DefaultTableModel modelScoreManage = (DefaultTableModel) table1.getModel();
                modelScoreManage.setRowCount(0); // Clear existing data in the table
                Iterator<ViewScoreBeta> scoreBetaIterator = scoreBetaList.iterator();
                while (scoreBetaIterator.hasNext()) {
                    ViewScoreBeta viewScoreBeta = scoreBetaIterator.next();
                    tableModel.addRow(new Object[]{
                            viewScoreBeta.getStudentID(),
                            viewScoreBeta.getName(),
                            viewScoreBeta.getGrantID(),
                            viewScoreBeta.getClassCode(),
                            viewScoreBeta.getSubjectCode(),
                            viewScoreBeta.getScoreValue(),
                            viewScoreBeta.getScoreType()
                    });
                }
            }
        });
    }



    void refreshTable() {
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0);
        t3 = new Thread(() -> {
            ViewScore viewScore = new ViewScore();
            String sql ="SELECT s.Student_id,s.Name AS Student_name, c.class_code, c.grant_id, ss.Subject_code, COALESCE(score.ScoreValue, NULL) AS ScoreValue, ts.ts_id AS ScoreType " +
                "FROM student AS s LEFT JOIN class AS c ON s.Class_code = c.class_code LEFT JOIN grants AS g ON c.grant_id = g.id LEFT JOIN subject_student AS ss ON s.Student_id = ss.student_id LEFT JOIN score_student AS score ON ss.Subject_student_id = score.ss_id LEFT JOIN type_score AS ts ON score.ts_id = ts.ts_id ";
            if(grantCheckBox.isSelected() || lopCheckBox.isSelected() || MonCheckBox.isSelected() || hocsinhCheckBox.isSelected() ){
            sql += "WHERE ";
            if (grantCheckBox.isSelected()) {
                sql += "c.grant_id = "+(int)scrBlock.getSelectedItem()+" ";
                if(lopCheckBox.isSelected() || MonCheckBox.isSelected() || hocsinhCheckBox.isSelected())sql+="AND ";
            }
            if (lopCheckBox.isSelected()) {
                sql += "c.class_code = "+(int)scrClass.getSelectedItem()+" ";
                if( MonCheckBox.isSelected() || hocsinhCheckBox.isSelected())sql+="AND ";
            }
            if (MonCheckBox.isSelected()) {
                ComboBoxItem subjectID= (ComboBoxItem)scrSubject.getSelectedItem();
                sql += "ss.Subject_code = "+(int)subjectID.getHiddenValue()+" ";
                if( hocsinhCheckBox.isSelected())sql+="AND ";
            }
            if (hocsinhCheckBox.isSelected()){
                sql += "s.Student_id = "+Integer.valueOf(scrStudentID.getText())+" ";
            }
        }
            scoreBetaList = viewScore.viewScoreBetaList(sql);
            Iterator<ViewScoreBeta> scoreBetaIterator = scoreBetaList.iterator();
            while (scoreBetaIterator.hasNext()) {
                ViewScoreBeta viewScoreBeta = scoreBetaIterator.next();
                tableModel.addRow(new Object[]{
                        viewScoreBeta.getStudentID(),
                        viewScoreBeta.getName(),
                        viewScoreBeta.getGrantID(),
                        viewScoreBeta.getClassCode(),
                        viewScoreBeta.getSubjectCode(),
                        viewScoreBeta.getScoreValue(),
                        viewScoreBeta.getScoreType()
                });
            }
        });
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    void updateBlock() {
        List<Block> blockList;
        scrBlock.removeAllItems();
        try {
            blockList = new GrantHandle().SELECT("SELECT * FROM `grants`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<Block> blockIterator = blockList.iterator();
        while (blockIterator.hasNext()) {
            Block block = blockIterator.next();
            scrBlock.addItem(block.getID());
        }
    }

    void updateClass() {

        List<MClass> mClassList = null;
        try {
            mClassList = new ClassHandle().SELECT("SELECT * FROM `class` WHERE grant_id = " + (int)(scrBlock.getSelectedItem()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        scrClass.removeAllItems();
        for (MClass mClass: mClassList){
            scrClass.addItem(mClass.getID());
        }
    }

    void updateTypeCore() {
        scrTypeScore.removeAllItems();
        List<TypeScore> typeScores = null;
        try {
            typeScores = new TypeScoreHandle().SELECT("SELECT * FROM `type_score`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (TypeScore typeScore: typeScores) {
            scrTypeScore.addItem(new ComboBoxItem(typeScore.getName(), typeScore.getID()));
        }
    }

    void updateSubject() {
        scrSubject.removeAllItems();
        List<Subject> subjectList = null;
        try {
            subjectList = new SubjectHandle().SELECT("SELECT * FROM `subject` WHERE grant_id = " + (int)(scrBlock.getSelectedItem()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Subject subject: subjectList) {
            scrSubject.addItem(new ComboBoxItem(subject.getName(), subject.getID()));
        }
    }

    public ViewScoreBeta tempViewScore = new ViewScoreBeta() ;
    private JPanel panel1;
    private JTable table1;
    private JComboBox scrBlock;
    private JComboBox scrClass;
    private JButton scrReset;
    private JButton scrResetForm;
    private JButton scrReload;
    private JButton scrEval;
    private JComboBox scrSubject;
    private JTextField scrStudentID;
    private JComboBox scrTypeScore;
    private JTextField scrScore;
    private JCheckBox grantCheckBox;
    private JCheckBox lopCheckBox;
    private JCheckBox MonCheckBox;
    private JCheckBox hocsinhCheckBox;
    private JButton delete;
    private JButton update;
    private JButton aZButton;
    private JButton zAButton;

}
