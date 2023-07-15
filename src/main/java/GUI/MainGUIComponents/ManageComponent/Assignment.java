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
    private JCheckBox chọnTấtCảCheckBox;
    private JCheckBox bỏChọnCheckBox;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton tảiLạiButton;
    private JButton xóaButton;
    private JButton sửaButton;
    private JButton thêmButton;
    private JButton chọnẢnhButton;
    private JTable table1;
}
