package GUI;

import GUI.MainGUIComponents.JPanelHome;
import GUI.MainGUIComponents.JPanelSearch;
import GUI.MainGUIComponents.JPannelManage;
import GUI.MainGUIComponents.JPannelViewScore;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    public MainGUI(){
        setTitle("Quản lý điểm học sinh");
        setSize(1200,600);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Trang chủ",new JPanelHome());
        tabbedPane.addTab("Quản lý", new JPannelManage());
        tabbedPane.addTab("Xem điểm", new JPannelViewScore());
        tabbedPane.addTab("Tìm kiếm",new JPanelSearch());
        tabbedPane.addTab("Nạp tiền", new JButton("Nạp tiền bằng qua bankking"));
        add(tabbedPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Get the selected tab index
                int selectedIndex = tabbedPane.getSelectedIndex();

                // Check if the selected tab is the "Xem điểm" tab (assuming it's at index 2)
                if (selectedIndex == 2) {
                    // Perform actions to refresh the content of the "Xem điểm" tab
                    // For example, you can call a method in JPannelViewScore to update its content
                    ((JPannelViewScore) tabbedPane.getComponentAt(selectedIndex)).refresh();
                }
            }
        });
//        ViewScore.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Code to be executed when the button is clicked
//                new JPannelViewScore();
//            }
//        });
    }
//    protected JButton ViewScore;
}
