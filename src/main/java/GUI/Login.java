package GUI;

import Controllers.Authenlication.Authenlication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JFrame{
    public Login(){
        setDefaultLookAndFeelDecorated(true);
        ImageIcon img = new ImageIcon("./icon.png");
        String relativePath = "./src/main/resources/images/";
        setIconImage(img.getImage());
        setTitle("Login");
        setContentPane(loginPanel);
        setSize(630,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Authenlication.Login(EmailTextFiled.getText(),passwordField.getText());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
//                if (textField1.getText().equals("admin")&&passwordField1.getText().equals("1111")) {
//                    new MainGUI();
//                    dispose();
//                }
//                else
//                    JOptionPane.showMessageDialog(btnLogin,"Thông tin tài khoản hoặc mật khẩu không chính xác!");
             //   Authenlication.Login();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new Register();
            }
        });
    }
    private JTextField EmailTextFiled;
    private JPasswordField passwordField;
    private JPanel loginPanel;
    private JButton btnLogin;
    private JButton registerButton;
}
