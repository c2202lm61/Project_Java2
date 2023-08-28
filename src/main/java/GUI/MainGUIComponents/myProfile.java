package GUI.MainGUIComponents;

import Controllers.Authenlication.Authenlication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myProfile extends JDialog{
    public myProfile(){
        id.setText(String.valueOf(Authenlication.insLogin.getID_NUMBER()));
        email.setText(Authenlication.insLogin.getEmail());
        yname.setText(Authenlication.insLogin.getName());
        setTitle("Thông tin");
        setContentPane(mainPanel);
        setBounds(150,100,300,100);
        setVisible(true);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }
    private JButton closeButton;
    private JLabel id;
    private JLabel email;
    private JLabel yname;
    private JPanel mainPanel;
}
