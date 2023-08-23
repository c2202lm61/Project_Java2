package GUI.MainGUIComponents;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class JpanelHome extends JPanel {
    DefaultListModel listModel;
    public JpanelHome(){
        JFXPanel jfxPanel = new JFXPanel();
        panelHome.setLayout(new GridLayout(1,1));
        // Thêm JFXPanel vào JPanel
        panelHome.add(jfxPanel);

        // Bắt đầu ứng dụng JavaFX trên Event Dispatch Thread (EDT)
        Platform.runLater(() -> initFX(jfxPanel));
        setLayout(new GridLayout());
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        add(panelHome);
    }
    private static void initFX(JFXPanel jfxPanel) {
        // Tạo một Scene JavaFX với một WebView
        WebView webView = new WebView();
        StackPane root = new StackPane();
        root.getChildren().add(webView);
        Scene scene = new Scene(root);

        // Đặt Scene cho JFXPanel
        jfxPanel.setScene(scene);
        File file = new File("src/home.html");
        URL url = null;
        try {
            url = file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        // file:/C:/test/a.html
        System.out.println("Local URL: " + url.toString());
        // Load một URL bất kỳ trong WebView
        webView.getEngine().load(url.toString());
    }
    private JPanel panel1;
    private JButton exitButton;
    private JPanel panelHome;

    private void createUIComponents() {
        panelHome = new JPanel();

    }
}
