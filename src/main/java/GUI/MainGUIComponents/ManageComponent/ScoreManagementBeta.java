package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.*;
import DAO.JDBCDriver;
import DAO.ViewScore;
import GUI.ComboBoxItem;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScoreManagementBeta extends JInternalFrame {
    Thread t1, t2, t3;
    List<Block> blockList;

    public ScoreManagementBeta() {
        updateTypeCore();
        updateBlock();
        updateClass();
        updateSubject();
        updateTable();
        setContentPane(panel1);
        setVisible(true);
        updateTypeCore();
        scrBlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClass();
                updateSubject();
                updateTable();
            }
        });
        scrClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });
        scrEval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComboBoxItem subjectItem = (ComboBoxItem) scrSubject.getSelectedItem();
                int subjectCode;
                if (subjectItem != null) {
                    subjectCode = (int) subjectItem.getHiddenValue();
                } else return;
                Thread thread1 = new Thread(() -> {
                    ResultSet resultSet = null;
                    try {
                        resultSet = JDBCDriver.ExecQuery("SELECT * FROM subject_student WHERE Subject_code ="+subjectCode+" AND student_id = "+Integer.parseInt(scrStudentID.getText()));
                        if (!resultSet.next()) {
                            if (JDBCDriver.SetQuery("INSERT INTO `subject_student`( `student_id`, `Subject_code`) VALUES ("+scrStudentID.getText()+","+subjectCode+")"));
                                System.out.println("Thêm subject_student thành công");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                Thread thread2 = new Thread(() -> {
                    ResultSet resultSet = null;
                    try {

                        resultSet = JDBCDriver.ExecQuery("SELECT * FROM subject_student WHERE Subject_code ="+subjectCode+" AND student_id = "+Integer.parseInt(scrStudentID.getText()));
                        if (resultSet.next()) {
                            System.out.println("Có dữ liệu");
                            Score score = new Score();
                            score.setScoreID(-1);
                            //
                            ComboBoxItem tsc = (ComboBoxItem) scrTypeScore.getSelectedItem();
                            if (tsc != null) {
                                int TypeScoreID = (int) tsc.getHiddenValue();
                                score.setTypeScoreID(TypeScoreID);
                            } else return;

                            score.setStudentSubjectID(resultSet.getInt("Subject_student_id"));
                            try {
                                score.setScoreValue(Double.valueOf(scrScore.getText()));
                            } catch (NumberFormatException ex) {
                                System.out.println("Giá trị điểm nhập  vào không hợp lệ");
                                return;
                            }
                            new ScoreStudentHandle().INSERT(score);
                            System.out.println("Chấm điểm thành công");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                thread1.start();
                try {
                    thread1.join();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                thread2.start();
                try {
                    thread2.join();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    void updateTable() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("MãSV");
        tableModel.addColumn("TênSV");
        tableModel.addColumn("Khối");
        tableModel.addColumn("Lớp");
        tableModel.addColumn("Mã Môn học");
        tableModel.addColumn("Điểm");
        tableModel.addColumn("Hệ số");
        int classID;
        if (scrClass.getSelectedItem() == null)
            classID = 0;
        else classID = Integer.parseInt(String.valueOf(scrClass.getSelectedItem()));
        t3 = new Thread(() -> {
            List<ViewScoreBeta> scoreBetaList = ViewScore.viewScoreBetaList(Integer.parseInt(String.valueOf(scrBlock.getSelectedItem())), classID);
            Iterator<ViewScoreBeta> scoreBetaIterator = scoreBetaList.iterator();
            while (scoreBetaIterator.hasNext()) {
                ViewScoreBeta viewScoreBeta = scoreBetaIterator.next();
                tableModel.addRow(new Object[]{
                        viewScoreBeta.getStudentID(),
                        viewScoreBeta.getStudentName(),
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
        table1.setModel(tableModel);
    }

    void updateBlock() {
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
            mClassList = new ClassHandle().SELECT("SELECT * FROM `class` WHERE grant_id = " + Integer.parseInt(String.valueOf(scrBlock.getSelectedItem())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<MClass> mClassIterator = mClassList.iterator();
        scrClass.removeAllItems();
        while (mClassIterator.hasNext()) {
            MClass mClass = mClassIterator.next();
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
        Iterator<TypeScore> typeScoreIterator = typeScores.iterator();
        while (typeScoreIterator.hasNext()) {
            TypeScore typeScore = typeScoreIterator.next();
            scrTypeScore.addItem(new ComboBoxItem(typeScore.getName(), typeScore.getID()));
            System.out.println(typeScore.getID());
        }
    }

    void updateSubject() {
        scrSubject.removeAllItems();
        List<Subject> subjectList = null;
        try {
            subjectList = new SubjectHandle().SELECT("SELECT * FROM `subject` WHERE grant_id = " + Integer.parseInt(String.valueOf(scrBlock.getSelectedItem())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<Subject> subjectIterator = subjectList.iterator();
        while (subjectIterator.hasNext()) {
            Subject mClass = subjectIterator.next();
            scrSubject.addItem(new ComboBoxItem(mClass.getName(), mClass.getID()));
        }
    }

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
    private JTextField textField3;
    private JButton tìmKiếmButton;
}
