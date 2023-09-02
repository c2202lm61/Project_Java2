package GUI.MainGUIComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyInfo extends JPanel{
    public MyInfo(){
        setLayout(new GridLayout());
        add(panel1);
        LogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(1);
            }
        });
        giớiThiệuỨngDụngButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new About();
            }
        });
        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Tutorial();
            }
        });
    }
    private JPanel panel1;
    private JButton tutorialButton;
    private JButton LogoutButton;
    private JButton ủngHộNhàPhátButton;
    private JButton giớiThiệuỨngDụngButton;
}
