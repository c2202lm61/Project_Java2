package GUI.MainGUIComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class JPanelSearch extends JPanel {
    public JPanelSearch(){
        setLayout(new BorderLayout());
        add(new HeaderPanel(),BorderLayout.NORTH);
        add(new ContentPanel(),BorderLayout.CENTER);
    }
}
class HeaderPanel extends JPanel{
    public HeaderPanel(){
        setBackground(new Color(32,178,170));
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10,20,10,20));
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(32,178,170));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(32,178,170));
        JLabel label = new JLabel("Tìm kiếm");
        topPanel.add(label);
        JComboBox JChooseSeach = new JComboBox();
        JChooseSeach.addItem("Học sinh");
        JChooseSeach.addItem("Giáo viên");
        JChooseSeach.addItem("Môn học");
        JChooseSeach.addItem("Lớp học");
        JChooseSeach.addItem("Khối học");
        bottomPanel.add(JChooseSeach);
        bottomPanel.add(new JTextField(15));
        bottomPanel.add(new JButton("Tìm"));
        add(topPanel,BorderLayout.NORTH);
        add(bottomPanel,BorderLayout.SOUTH);
    }
}
class ContentPanel extends JPanel{
    public ContentPanel(){
        setLayout(new GridLayout());
        add(new searchTabblepane());
    }
}
class searchTabblepane extends JTabbedPane{
    public searchTabblepane(){
        addTab("Sinh Viên",new JScrollPane(new JStudentTable()));
        addTab("Giáo viên",new JScrollPane(new JTeacherTable()));
        addTab("Khối",new JScrollPane(new JBlockTable()));
        addTab("Lớp",new JScrollPane(new JClassTable()));
        addTab("Môn",new JScrollPane(new JSubjectTable()));
    }
}
class JStudentTable extends JTable{
    public JStudentTable(){
        DefaultTableModel  model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Họ tên");
        model.addColumn("Ngày  sinh");
        model.addColumn("Địa chỉ");
        model.addColumn("Giới tính");
        model.addColumn("Số điện thoại");
        model.addRow(new Object[]{1,"do nam tram","1/2/2002","noi nao do","3d","0123456789"});
        setModel(model);
    }
}
class JTeacherTable extends JTable{
    public JTeacherTable(){
        DefaultTableModel  model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Họ tên");
        model.addColumn("Ngày  sinh");
        model.addColumn("Địa chỉ");
        model.addColumn("Giới tính");
        model.addColumn("Số điện thoại");
        model.addRow(new Object[]{1,"giao vien","1/2/2002","noi nao do","3d","0123456789"});
        setModel(model);
    }
}
class JBlockTable extends JTable{
    public JBlockTable(){
        DefaultTableModel  model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Tên khối");
        model.addRow(new Object[]{1,"A"});
        setModel(model);
    }
}
class JClassTable extends JTable{
    public JClassTable(){
        DefaultTableModel  model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Tên Lớp");
        model.addColumn("id khối");
        model.addColumn("TeacherId");
        model.addRow(new Object[]{1,"1a",1,12});
        setModel(model);
    }
}
class JSubjectTable extends JTable{
    public JSubjectTable(){
        DefaultTableModel  model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Tên môn hoc");
        model.addRow(new Object[]{1,"Math"});
        setModel(model);
    }
}