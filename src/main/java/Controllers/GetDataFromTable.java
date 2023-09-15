package Controllers;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GetDataFromTable {
    public static DefaultTableModel setAllCheckboxFromTable(JTable table, Boolean bool){
        DefaultTableModel tableModel = new DefaultTableModel();
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            tableModel.addColumn(table.getColumnName(i));
        }
        //
        System.out.println("Số cột là"+columnCount);
        System.out.println("Số hàng là"+rowCount);
        Object[] objRow = new Object[columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
               objRow[col] = table.getValueAt(row, col);
               if (col == 0)
               objRow[col] = bool;
            }
            tableModel.addRow(objRow);
        }
        return  tableModel;
    }
    public static DefaultTableModel setCheckboxTableFromID(JTable table, Boolean bool,int ID){
        DefaultTableModel tableModel = new DefaultTableModel();
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            tableModel.addColumn(table.getColumnName(i));
        }
        //
        System.out.println("Số cột là"+columnCount);
        System.out.println("Số hàng là"+rowCount);
        Object[] objRow = new Object[columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                objRow[col] = table.getValueAt(row, col);
                if (col == 1 && Integer.parseInt(String.valueOf(objRow[col])) == ID){
                    objRow[0] = bool;
                }

            }
            tableModel.addRow(objRow);
        }
        return  tableModel;
    }
}
