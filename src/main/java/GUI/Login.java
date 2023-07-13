package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog{
    public Login(){
        setTitle("Login");
        setContentPane(loginPanel);
        setSize(630,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (textField1.getText().equals("admin")&&passwordField1.getText().equals("1111")) {
                    new MainGUI();
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(btnLogin,"Thông tin tài khoản hoặc mật khẩu không chính xác!");
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Register();
            }
        });
    }
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel loginPanel;
    private JButton btnLogin;
    private JButton registerButton;
}
