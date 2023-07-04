package GUI;

import javax.swing.*;

public class Register extends JFrame{
    public Register(){
        setContentPane(registerPanel);
        setTitle("Register");
        setResizable(false);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private JPanel registerPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registerButton;
    private JButton loginButton;
    private JPasswordField passwordField1;
}
