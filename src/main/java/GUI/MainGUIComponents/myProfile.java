package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myProfile extends JDialog{
    public myProfile(){
        setTitle("Thông tin");
        setBounds(100,100,300,100);
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
}
