package GUI;

import javax.swing.*;

public class Login extends JDialog{
    public Login(){
        setTitle("Login");
        setContentPane(loginPanel);
        setSize(630,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel loginPanel;
    private JButton btnLogin;
    private JButton registerButton;
}
