package GUI.MainGUIComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JpanelHome extends JPanel {
    public JpanelHome(){
        setLayout(new GridLayout());
        add(panel1);
        exitButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }
    private JPanel panel1;
    private JButton exitButton;
}
