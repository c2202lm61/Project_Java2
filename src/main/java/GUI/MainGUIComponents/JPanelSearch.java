package GUI.MainGUIComponents;

import DAO.Access.GrantHandle;
import Model.Block;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        setModel(model);
    }
}
class JBlockTable extends JTable{
    public JBlockTable(){
        DefaultTableModel  model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Tên khối");



        GrantHandle grantHandle = new GrantHandle();

        List<Block> a =new ArrayList<>();
        model.setRowCount(0); // Clear existing data in the table

        try {
            a = grantHandle.SELECT("SELECT * FROM grants");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Iterator<Block> blockIterator = a.iterator();
        while (blockIterator.hasNext()){
            Block block = blockIterator.next();
            model.addRow(new Object[]{block.getID(),block.getName()});
        }


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
        setModel(model);
    }
}
class JSubjectTable extends JTable{
    public JSubjectTable(){
        DefaultTableModel  model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Tên môn hoc");
        setModel(model);
    }
}