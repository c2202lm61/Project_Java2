package GUI.MainGUIComponents.ManageComponent;

import DAO.Access.ClassHandle;
import DAO.Access.GrantHandle;
import DAO.Access.SubjectHandle;
import DAO.Access.TypeScoreHandle;
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

public class ScoreManagementBeta extends JInternalFrame{
    List<Block> blockList;
    public ScoreManagementBeta(){
        updateTypeCore();
        updateBlock();
        updateClass();
        updateSubject();
        setContentPane(panel1);
        setVisible(true);
        updateTypeCore();
        scrBlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClass();
                updateSubject();
            }
        });
    }
    void updateBlock(){
        scrBlock.removeAllItems();
            try {
                blockList = new GrantHandle().SELECT("SELECT * FROM `grants`");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Iterator<Block> blockIterator = blockList.iterator();
        while (blockIterator.hasNext()){
            Block block = blockIterator.next();
            scrBlock.addItem(block.getID());
        }
    }
    void updateClass(){

        List<MClass> mClassList = null;
        try {
            mClassList = new ClassHandle().SELECT("SELECT * FROM `class` WHERE grant_id = "+Integer.parseInt(String.valueOf(scrBlock.getSelectedItem())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<MClass> mClassIterator = mClassList.iterator();
        scrClass.removeAllItems();
        while (mClassIterator.hasNext()){
            MClass mClass = mClassIterator.next();
            scrClass.addItem(mClass.getID());
        }
    }
//    Thread thread1,thread2,thread3;
//    List<TypeScore> typeScores;
//    public ScoreManagementBeta() throws SQLException {
//        updateTypeCore();
//        updateBlock();
//        updateClass();
//        updateSubject();
//        updateTable();
//        setContentPane(panel1);
//        setVisible(true);
//        scrBlock.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Thread thread1 = new Thread(() -> {
//                        SwingUtilities.invokeLater(()->{
//                            try {
//                                updateClass();
//                            } catch (SQLException ex) {
//                                throw new RuntimeException(ex);
//                            }
//                        });
//                });
//                Thread thread2 = new Thread(()->{
//                    SwingUtilities.invokeLater(()->{
//                        try {
//                            updateSubject();
//                        } catch (SQLException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                    });
//                });
//                thread1.start();
//                try {
//                    thread1.join();
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//                thread2.start();
//                try {
//                    thread2.join();
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });
//        scrEval.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ComboBoxItem item  = (ComboBoxItem) scrSubject.getSelectedItem();
//                Integer id = (Integer) item.getHiddenValue();
//             //   System.out.println("ScrSuject:"+id);
//            }
//        });
//        scrClass.addActionListener(new ActionListener() {
//            //event on class combobox
//            @Override
//            public void actionPerformed(ActionEvent e) {
//              thread3 = new Thread(() -> {
//
//                  try {
//                      updateTable();
//                  } catch (SQLException ex) {
//                      throw new RuntimeException(ex);
//                  }
//              });
//              thread3.start();
//            }
//        });
//    }
//    void updateTable() throws SQLException {
//        DefaultTableModel tableModel = new DefaultTableModel();
//        Iterator<TypeScore> typeScoreIterator = typeScores.iterator();
//        tableModel.addColumn("Khối");
//        tableModel.addColumn("Lớp");
//        tableModel.addColumn("Mã sinh viên");
//        tableModel.addColumn("Tên sinh  viên");
//        tableModel.addColumn("Môn học");
//        tableModel.addColumn("SubjectStudentID");
//        while (typeScoreIterator.hasNext()){
//            TypeScore typeScore =  typeScoreIterator.next();
//            tableModel.addColumn(typeScore.getName());
//        }
//        List<ViewScoreBeta> viewScore = new ArrayList<>();
//        ResultSet resultSet = JDBCDriver.ExecQuery("SELECT s.Student_id,s.Name, c.class_code, c.grant_id, g.name AS grant_name, ss.Subject_student_id,ss.Subject_code FROM student AS s LEFT JOIN class AS c ON s.Class_code = c.class_code LEFT JOIN grants AS g ON c.grant_id = g.id LEFT JOIN subject_student AS ss ON s.Student_id = ss.student_id WHERE g.id = "+Integer.parseInt(String.valueOf(scrBlock.getSelectedItem()))+" AND c.class_code = "+Integer.parseInt(String.valueOf(scrClass.getSelectedItem())));
//        while (resultSet.next()){
//            ViewScoreBeta viewScoreBeta = new ViewScoreBeta();
//            viewScoreBeta.setStudentID(resultSet.getInt("Student_id"));
//            viewScoreBeta.setStudentName(resultSet.getString("Name"));
//            viewScoreBeta.setClassCode(resultSet.getInt("class_code"));
//            viewScoreBeta.setGrantId(resultSet.getInt("grant_id"));
//            viewScoreBeta.setGrantName(resultSet.getString("grant_name"));
//            viewScore.add(viewScoreBeta);
//        }
//        Iterator<ViewScoreBeta> viewScoreBetaIterator = viewScore.iterator();
//        while (viewScoreBetaIterator.hasNext()){
//
//            ViewScoreBeta viewScoreBeta  = viewScoreBetaIterator.next();
//            tableModel.addRow(new Object[]{viewScoreBeta.getGrantName(),viewScoreBeta.getClassCode(),viewScoreBeta.getStudentID(),viewScoreBeta.getStudentName()});
//
//        }
//        table1.setModel(tableModel);
//    }
    void updateTypeCore(){
        scrTypeScore.removeAllItems();
        List<TypeScore> typeScores = null;
        try {
            typeScores = new TypeScoreHandle().SELECT("SELECT * FROM `type_score`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<TypeScore> typeScoreIterator = typeScores.iterator();
        while (typeScoreIterator.hasNext()){
            TypeScore typeScore = typeScoreIterator.next();
            scrTypeScore.addItem(new ComboBoxItem(typeScore.getName(),typeScore.getID()));
        }
    }
    void updateSubject(){
        scrSubject.removeAllItems();
        List<Subject> subjectList = null;
        try {
            subjectList = new SubjectHandle().SELECT("SELECT * FROM `subject` WHERE grant_id = "+Integer.parseInt(String.valueOf(scrBlock.getSelectedItem())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Iterator<Subject> subjectIterator = subjectList.iterator();
        while (subjectIterator.hasNext()){
            Subject mClass = subjectIterator.next();
            scrSubject.addItem(new ComboBoxItem(mClass.getName(),mClass.getID()));
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
