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
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ComboBoxItem selectedItem = (ComboBoxItem) scrSubject.getSelectedItem();
                if (selectedItem != null) {
                    int classID = (int) selectedItem.getHiddenValue();
                    System.out.println("Selected class ID: " + classID);
                } else System.out.println("No item selected in the ComboBox.");

                Thread thread1 = new Thread(() -> {
                    ResultSet resultSet = null;
                    try {
                        resultSet = JDBCDriver.ExecQuery("SELECT * FROM subject_student WHERE Subject_code =4 AND student_id = 25");
                        if (!resultSet.next()) {
                            if (JDBCDriver.SetQuery("INSERT INTO `subject_student`( `student_id`, `Subject_code`) VALUES (25,4)"))
                                System.out.println("Thêm subject_student thành công");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                Thread thread2 = new Thread(() -> {
                    ResultSet resultSet = null;
                    try {
                        resultSet = JDBCDriver.ExecQuery("SELECT * FROM subject_student WHERE Subject_code =4 AND student_id = 25");
                        if (resultSet.next()) {
                            System.out.println("Có dữ liệu");
                            System.out.println("id  :" + resultSet.getInt("Subject_student_id"));
                            Score score = new Score();
                            score.setScoreID(-1);
                            score.setTypeScoreID(1);
                            score.setStudentSubjectID(resultSet.getInt("Subject_student_id"));
                            score.setScoreValue(8);
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
