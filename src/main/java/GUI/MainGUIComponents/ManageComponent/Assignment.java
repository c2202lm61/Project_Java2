package GUI.MainGUIComponents.ManageComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Assignment extends JInternalFrame{

    DefaultTableModel model;
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        model  = new DefaultTableModel();

        model.addColumn("Chọn");
        model.addColumn("Tên");

        table1 = new JTable(model) {

            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
    }
    public Assignment(){
        setTitle("Phân công");
        model.addRow(new Object[]{false,"John"});
        setContentPane(AssignmentPanel);
        setVisible(true);
    }
}
