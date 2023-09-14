package GUI.MainGUIComponents;

import Controllers.Authenlication.Authenlication;
import DAO.JDBCDriver;

import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HeaderPanel extends JPanel{
    public HeaderPanel() {
        insName.setText(Authenlication.insLogin.getName());
        insID.setText(String.valueOf(Authenlication.insLogin.getID_NUMBER()));
        try {
           ResultSet rs  = JDBCDriver.ExecQuery("SELECT role_name FROM instructor INNER JOIN instructor_role ON instructor.ID_NUMBER = instructor_role.ID_NUMBER INNER  JOIN role ON instructor_role.Role_id = role.id " +
                    "WHERE instructor_role.ID_NUMBER = "+Authenlication.insLogin.getID_NUMBER());
           String text = "";
           int i=0;
           while (rs.next()){
               if (i>0) text+=", ";
               text+=rs.getString("role_name");
               i++;
           }
           if(text.equals("")) text = "Giáo viên";
           roletext.setText(text);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setLayout(new GridLayout());
        add(panel1);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();

                // Định dạng theo 12 giờ
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

                // Chuyển đổi sang định dạng 12 giờ
                String formattedDateTime = now.format(formatter);

                // In ra màn hình
                HeaderDate.setText(formattedDateTime);
            }
        });
        timer.setRepeats(true);
        timer.start();
    }
    private JPanel panel1;
    private JLabel HeaderDate;
    private JLabel insName;
    private JLabel insID;
    private JLabel roletext;
}
