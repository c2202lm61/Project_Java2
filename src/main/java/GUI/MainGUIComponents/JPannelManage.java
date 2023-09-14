package GUI.MainGUIComponents;

import Controllers.Authenlication.Authenlication;
import Controllers.Authorization.Authorization;
import DAO.JDBCDriver;
import GUI.MainGUIComponents.ManageComponent.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JPannelManage extends JPanel{
    JPanel secondPart = new JPanel();
    public JPannelManage(){
        setLayout(new BorderLayout(10,10));
        JPanel firstPart = new JPanel();
        firstPart.setBackground(mainColor.CYAN);
        firstPart.setPreferredSize(new Dimension(100, 100)); // Đặt kích thước theo ý muốn
        add(new HeaderPanel(), BorderLayout.NORTH);

        // Phần thứ hai
        CardLayout cardLayout = new CardLayout();
        secondPart.setLayout(cardLayout);


// Thêm thành phần 2


// Thêm thành phần 3

        //

        //

        JDesktopPane component1 = new JDesktopPane();
        component1.setLayout(new GridLayout());
        component1.add(new StudentManagement());
        String card1 = "Card 1";
        secondPart.add(component1, card1);
        //
        cardLayout.show(secondPart, "Card 1");
        secondPart.setBackground(mainColor.CYAN);
        add(secondPart, BorderLayout.CENTER);


        // Phần thứ ba
        JPanel thirdPart = new JPanel();
        thirdPart.setBackground(Color.GREEN);
        LeftPanel leftPanel = new LeftPanel();
        leftPanel.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                // Thêm thành phần 1
                JDesktopPane component1 = new JDesktopPane();
                component1.setLayout(new GridLayout());
                component1.add(new StudentManagement());
                String card1 = "Card 1";
                secondPart.add(component1, card1);
                cardLayout.show(secondPart, "Card 1");
            }
        });
        leftPanel.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDesktopPane component2 = new JDesktopPane();
                component2.setLayout(new GridLayout());
                component2.add(new TeacherManagement());
                String card2 = "Card 2";
                secondPart.add(component2, card2);
                cardLayout.show(secondPart, "Card 2");
            }
        });

        leftPanel.button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JDesktopPane component4 = new JDesktopPane();
                component4.setLayout(new GridLayout());
                component4.add(new BlockManagement());
                String card4 = "Card 4";
                secondPart.add(component4, card4);
                cardLayout.show(secondPart, "Card 4");
            }

        });
        leftPanel.button4.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent actionEvent) {
                JDesktopPane component5 = new JDesktopPane();
                component5.setLayout(new GridLayout());
                component5.add(new ClassManagement());
                String card5 = "Card 5";
                secondPart.add(component5, card5);
                cardLayout.show(secondPart, "Card 5");

            }

        });
        leftPanel.button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDesktopPane component6 = new JDesktopPane();
                component6.setLayout(new GridLayout());
                component6.add(new SubjectManagement());
                String card6 = "Card 6";
                secondPart.add(component6, card6);
                cardLayout.show(secondPart, "Card 6");
            }
        });
        leftPanel.button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDesktopPane component7 = new JDesktopPane();
                component7.setLayout(new GridLayout());
                component7.add(new Assignment());
                String card7 = "Card 7";
                secondPart.add(component7, card7);

                cardLayout.show(secondPart, "Card 7");
            }
        });
        leftPanel.button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDesktopPane component8 = new JDesktopPane();
                component8.setLayout(new GridLayout());
                component8.add(new SubjectTeachingTeacher());
                String card8 = "Card 8";
                secondPart.add(component8, card8);
                cardLayout.show(secondPart, "Card 8");
            }
        });

        leftPanel.button8.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDesktopPane component9 = new JDesktopPane();
                component9.setLayout(new GridLayout());
                component9.add(new TypescoreManagement());
                String card9 = "Card 9";
                secondPart.add(component9, card9);
                cardLayout.show(secondPart, "Card 9");
            }
        });
        leftPanel.button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDesktopPane component10 = new JDesktopPane();
                component10.setLayout(new GridLayout());
                    component10.add(new ScoreManagementBeta());

                String card10 = "Card10";
                secondPart.add(component10, card10);
                cardLayout.show(secondPart, "Card10");
            }
        });
        leftPanel.button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDesktopPane component11 = new JDesktopPane();
                component11.setLayout(new GridLayout());
                component11.add(new RoleManagement());

                String card11 = "Card11";
                secondPart.add(component11, card11);
                cardLayout.show(secondPart, "Card11");
            }
        });
        leftPanel.button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDesktopPane component12 = new JDesktopPane();
                component12.setLayout(new GridLayout());
                component12.add(new AuthorizationManagement());

                String card12 = "Card12";
                secondPart.add(component12, card12);
                cardLayout.show(secondPart, "Card12");
            }
        });

        add(leftPanel, BorderLayout.WEST);
    }
}
class LeftPanel extends JPanel{
    JButton button;
    JButton button1;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JButton button8;
    JButton button9;
    JButton button10;
    JButton button11;
    public LeftPanel(){
        setBackground(mainColor.CYAN);
        setBorder(new EmptyBorder(10,10,10,10));
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel(new GridLayout(11,1,0,10));
        panel.setBackground(mainColor.CYAN);
        button = new JButton("Quản lý học sinh");
        button.setBorder(new EmptyBorder(10,0,10,0));
        button1 = new JButton("Quản lý giáo viên");
        button3 = new JButton("Quản lý khoa");
        button4 = new JButton("Quản lý lớp");
        button5 = new JButton("Quản lý môn");
        button6 = new JButton("Phân công");
        button7 = new JButton("Giáo viên giảng dạy bộ môn");
        button8 = new JButton("Loại điểm");
        button9 = new JButton("Chấm điểm");
        button10  = new JButton("Vai trò giáo viên");
        button11 = new JButton("Uỷ quyền");
        button.setEnabled(false);
        button1.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
        button9.setEnabled(false);
        button10.setEnabled(false);
        button11.setEnabled(false);
        if(Authorization.getPermisionForStudent()) button.setEnabled(true);
        if(Authorization.getPermisionForTeacher()) button1.setEnabled(true);
        if(Authorization.getPermisionForBlock()) button3.setEnabled(true);
        if(Authorization.getPermisionForClass()) button4.setEnabled(true);
        if(Authorization.getPermisionForSubject()) button5.setEnabled(true);
        if(Authorization.getPermisionForAsignment()) button6.setEnabled(true);
        if(Authorization.getPermisionForTeacher()) button7.setEnabled(true);
        if(Authorization.getPermisionForTypeScore()) button8.setEnabled(true);
        try {
            ResultSet rs = JDBCDriver.ExecQuery("SELECT * FROM instructor INNER JOIN instructor_subject  ON instructor.ID_NUMBER = instructor_subject.ID_NUMBER WHERE instructor.ID_NUMBER = "+Authenlication.insLogin.getID_NUMBER());
            while (rs.next())
                button9.setEnabled(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JDBCDriver.DestroyConnection();
        if(Authorization.getPermisionForRole()) button10.setEnabled(true);
        if(Authorization.getPermisionForRole()) button11.setEnabled(true);

        panel.add(button);
        panel.add(button1);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);
        panel.add(button10);
        panel.add(button11);
        add(panel);

    }
}