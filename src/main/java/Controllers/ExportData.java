package Controllers;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExportData {
    public static String getLocation(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showDialog(null, "Chọn thư mục");
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
            String absolutePath = selectedFolder.getAbsolutePath();
           // JOptionPane.showMessageDialog(null, "Đường dẫn tuyệt đối: " + absolutePath);
            return  absolutePath;
        }
        return null;
    }
    public static void  exportData(JTable table, int index){
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Dữ liệu");
            Row headerRow = sheet.createRow(0);

            for (int i = index; i < columnCount; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(table.getColumnName(i));
            }
            for (int row = 0; row < rowCount; row++) {
                Row r1 = sheet.createRow(row + 1);
                if (!(Boolean) table.getValueAt(row,0) && index > 0){
                    continue;
                }
                for (int col = 0; col < columnCount; col++) {
                    if (index > 0 && col == 0)  continue;
                    Cell cell = r1.createCell(col);
                    cell.setCellValue(table.getValueAt(row, col).toString());
                }
            }
            String filename = JOptionPane.showInputDialog(null,"Vui lòng nhập tên file");
            if (filename == null || filename.equals("")) return;
            String location = getLocation();
            if (location == null) return;
            String filePath =location+"/"+ filename +".xlsx";
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            outputStream.close();
//
           JOptionPane.showMessageDialog(null,"Xuất dữ liệu thành công");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static void  exportData1(JTable table){
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Dữ liệu");
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columnCount; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(table.getColumnName(i));
            }
            for (int row = 0; row < rowCount; row++) {
                Row r1 = sheet.createRow(row + 1);
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = r1.createCell(col);
                    cell.setCellValue(table.getValueAt(row, col).toString());
                }
            }
            String filename = JOptionPane.showInputDialog(null,"Vui lòng nhập tên file");
            if (filename == null || filename.equals("")) return;
            String location = getLocation();
            if (location == null) return;
            String filePath =location+"/"+ filename +".xlsx";
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            outputStream.close();
//
            JOptionPane.showMessageDialog(null,"Xuất dữ liệu thành công");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
