package GUI.MainGUIComponents.ManageComponent;

import javax.swing.*;

public class Assignment extends JInternalFrame{
    public Assignment(){
        setTitle("Phân công");
        setContentPane(AssignmentPanel);
        setVisible(true);
    }
    private JPanel AssignmentPanel;
    private JPanel panel1;
}
