package GUI.MainGUIComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JpanelHome extends JPanel {
    DefaultListModel listModel;
    public JpanelHome(){

        setLayout(new GridLayout());
        add(panel1);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }
    private JPanel panel1;
    private JButton exitButton;
    private JPanel panelHome;

    private void createUIComponents() {
        panelHome = new JPanel();

    }
}
