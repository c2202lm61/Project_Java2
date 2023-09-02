package GUI.MainGUIComponents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JDialog{
    public  About(){
        setSize(400,200);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }
    private JButton closeButton;
    private JPanel mainPanel;
}
