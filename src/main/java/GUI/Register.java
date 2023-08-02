package GUI;

import Controllers.Authenlication.Authenlication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JDialog{
    public Register(){
        setContentPane(registerPanel);
        setTitle("Register");
        setSize(600,350);
        setLocationRelativeTo(null);
        setVisible(true);
        registerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Authenlication.Register(Email.getText(),Password.getText(),Fullname.getText());
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new Login();
            }
        });
    }
    private JPanel registerPanel;
    private JTextField Fullname;
    private JTextField Email;
    private JButton registerButton;
    private JButton loginButton;
    private JPasswordField Password;
}
