package GUI.MainGUIComponents;

import GUI.MainGUIComponents.ManageComponent.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPannelManage extends JPanel{
    JPanel secondPart = new JPanel();
    public JPannelManage(){
        setLayout(new BorderLayout(10,10));
        JPanel firstPart = new JPanel();
        firstPart.setBackground(mainColor.CYAN);
        firstPart.setPreferredSize(new Dimension(100, 100)); // Đặt kích thước theo ý muốn
        add(firstPart, BorderLayout.NORTH);

        // Phần thứ hai

        CardLayout cardLayout = new CardLayout();
        secondPart.setLayout(cardLayout);
        // Thêm thành phần 1
        JDesktopPane component1 = new JDesktopPane();
        component1.setLayout(new GridLayout());
        component1.add(new StudentManagement());
        String card1 = "Card 1";
        secondPart.add(component1, card1);

// Thêm thành phần 2
        JDesktopPane component2 = new JDesktopPane();
        component2.setLayout(new GridLayout());
        component2.add(new TeacherManagement());
        String card2 = "Card 2";
        secondPart.add(component2, card2);

// Thêm thành phần 3
        JDesktopPane component3 = new JDesktopPane();
        component3.setLayout(new GridLayout());
        component3.add(new ScoreManagement());
        String card3 = "Card 3";
        secondPart.add(component3, card3);
        //
        JDesktopPane component4 = new JDesktopPane();
        component4.setLayout(new GridLayout());
        component4.add(new BlockManagement());
        String card4 = "Card 4";
        secondPart.add(component4, card4);
        //
        JDesktopPane component5 = new JDesktopPane();
        component5.setLayout(new GridLayout());
        component5.add(new ClassManagement());
        String card5 = "Card 5";
        secondPart.add(component5, card5);
        //
        JDesktopPane component6 = new JDesktopPane();
        component6.setLayout(new GridLayout());
        component6.add(new SubjectManagement());
        String card6 = "Card 6";
        secondPart.add(component6, card6);

        JDesktopPane component7 = new JDesktopPane();
        component7.setLayout(new GridLayout());
        component7.add(new Assignment());
        String card7 = "Card 7";
        secondPart.add(component7, card7);
        //
        JDesktopPane component8 = new JDesktopPane();
        component8.setLayout(new GridLayout());
        component8.add(new SubjectTeachingTeacher());
        String card8 = "Card 8";
        secondPart.add(component8, card8);
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
                cardLayout.show(secondPart, "Card 1");
            }
        });
        leftPanel.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(secondPart, "Card 2");
            }
        });
        leftPanel.button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(secondPart, "Card 3");
            }
        });
        leftPanel.button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(secondPart, "Card 4");
            }
        });
        leftPanel.button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(secondPart, "Card 5");
            }
        });
        leftPanel.button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(secondPart, "Card 6");
            }
        });
        leftPanel.button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(secondPart, "Card 7");
            }
        });
        leftPanel.button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(secondPart, "Card 8");
            }
        });
        add(leftPanel, BorderLayout.WEST);
    }
}
class LeftPanel extends JPanel{
    JButton button;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    public LeftPanel(){
        setBackground(mainColor.CYAN);
        setBorder(new EmptyBorder(10,10,10,10));
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel(new GridLayout(8,1,0,10));
        panel.setBackground(mainColor.CYAN);
        button = new JButton("Quản lý học sinh");
        button.setBorder(new EmptyBorder(10,0,10,0));
        button1 = new JButton("Quản lý giáo viên");
        button2 = new JButton("Quản lý điểm");
        button3 = new JButton("Quản lý khoa");
        button4 = new JButton("Quản lý lớp");
        button5 = new JButton("Quản lý môn");
        button6 = new JButton("Phân công");
        button7 = new JButton("Giáo viên giảng dạy bộ môn");
        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button7);
        add(panel);

    }
}