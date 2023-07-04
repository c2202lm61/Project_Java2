package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainGUI extends  JFrame{
    public MainGUI(){
        setTitle("MainGUI");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainGUIPanel);
        setVisible(true);
        //student
        DefaultTableModel  model =  new DefaultTableModel();
        model.addColumn("Mã sinh viên");
        model.addColumn("Họ và tên");
        model.addColumn("Mã  lớp");
        model.addColumn("Mã khoa");
        model.addColumn("Ngày  sinh");
        model.addColumn("Địa  chỉ");
        model.addColumn("Giới tính");
        model.addColumn("Số điện thoại");
        model.addRow(new Object[]{"1","dinh xuan tung","c220?",1,"1/1/2001","175 ts","Nam","0123456789"});
        table1.setModel(model);
        //teacher
        DefaultTableModel modelTeacher = new DefaultTableModel();
        modelTeacher.addColumn("Mã giáo viên");
        modelTeacher.addColumn("Họ và tên");
        modelTeacher.addColumn("Mã môn dạy");
        modelTeacher.addColumn("Mã khối");
        modelTeacher.addColumn("Mã lớp");
        modelTeacher.addColumn("Ngày sinh");
        modelTeacher.addColumn("Giới tính");
        modelTeacher.addColumn("Email");
        modelTeacher.addColumn("Số điện thoại");
        modelTeacher.addRow(new Object[]{1,"Nguyen van a","MH0001","k01","LH01","01/01/2001","#D","abc@gmail.com","0123456789"});
        teacherTable.setModel(modelTeacher);
        DefaultTableModel modelBlock = new DefaultTableModel();
        modelBlock.addColumn("Mã khối");
        modelBlock.addColumn("Tên khối");
        modelBlock.addRow(new Object[]{"k1","khoi 1"});
        BlockTable.setModel(modelBlock);
        DefaultTableModel modelClass = new DefaultTableModel();
        modelClass.addColumn("Mã lớp");
        modelClass.addColumn("Tên lớp");
        modelClass.addColumn("Mã khối");
        modelClass.addColumn("Khóa học");
        Classtable.setModel(modelClass);
        DefaultTableModel modelSubject = new DefaultTableModel();
        modelSubject.addColumn("Mã môn học");
        modelSubject.addColumn("Tên môn học");
        modelSubject.addColumn("Số tiết");
        modelSubject.addColumn("Mã khối                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ");
        modelSubject.addColumn("Mã lớp");

        SubjectTable.setModel(modelSubject);
    }
    private JPanel mainGUIPanel;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextField dfedfTextField;
    private JComboBox comboBox1;
    private JButton button1;
    private JTable table1;
    private JTable teacherTable;
    private JTable BlockTable;
    private JTable Classtable;
    private JTable SubjectTable;
}
