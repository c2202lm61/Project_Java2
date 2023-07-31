package GUI;

import GUI.MainGUIComponents.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    public MainGUI(){
        setDefaultLookAndFeelDecorated(true);
        ImageIcon img = new ImageIcon("./icon.png");
        String relativePath = "./src/main/resources/images/";
        setIconImage(img.getImage());
        setTitle("Quản lý điểm học sinh");
        setSize(1200,600);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Trang chủ",new ImageIcon(relativePath+"house.png"),new JPanelHome());
        tabbedPane.addTab("Quản lý",new ImageIcon(relativePath+"management.png"), new JPannelManage());
        tabbedPane.addTab("Xem điểm",new ImageIcon(relativePath+"analysis.png"), new JPannelViewScore());
        tabbedPane.addTab("Giới thiệu",new ImageIcon(relativePath+"info.png"),new JPanelSearch());
        tabbedPane.addTab("Nạp tiền",new ImageIcon(relativePath+"pay.png"), new SupportDeveloper());
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
