package GUI;

import GUI.MainGUIComponents.JPanelHome;
import GUI.MainGUIComponents.JPanelSearch;
import GUI.MainGUIComponents.JPannelManage;
import GUI.MainGUIComponents.JPannelViewScore;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    public MainGUI(){
        setTitle("Quản lý điểm học sinh");
        setSize(1200,600);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Trang chủ",new JPanelHome());
        tabbedPane.addTab("Quản lý", new JPannelManage());
        tabbedPane.addTab("Xem điểm", new JPannelViewScore());
        tabbedPane.addTab("Tìm kiếm",new JPanelSearch());
        add(tabbedPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
