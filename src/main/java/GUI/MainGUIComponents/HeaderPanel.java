package GUI.MainGUIComponents;

import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HeaderPanel extends JPanel{
    public HeaderPanel() {
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
}
