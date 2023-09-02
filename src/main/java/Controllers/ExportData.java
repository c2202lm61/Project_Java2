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
    public static void  exportData(JTable table){
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Dữ liệu");
            for (int row = 0; row < rowCount; row++) {
                Row r1 = sheet.createRow(row + 1);
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = r1.createCell(col);
                    cell.setCellValue(table.getValueAt(row, col).toString());
                }
            }
            String filename = JOptionPane.showInputDialog(null,"Vui lòng nhập tên file");
            if (filename == null || filename.equals("")) return;
            String filePath =getLocation()+"/"+ filename +".xlsx";
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            outputStream.close();
//
           JOptionPane.showMessageDialog(null,"Xuất dữ liệu thành công");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        try {
//            Workbook workbook = new XSSFWorkbook();
//            Sheet sheet = workbook.createSheet("Dữ liệu");
//            Row headerRow = sheet.createRow(0);
//            for (int i = 0; i < headers.length; i++) {
//                Cell cell = headerRow.createCell(i);
//                cell.setCellValue(headers[i]);
//            }
//            for (int i = 0; i < data.length; i++) {
//                Row row = sheet.createRow(i + 1);
//                for (int j = 0; j < data[i].length; j++) {
//                    Cell cell = row.createCell(j);
//                    cell.setCellValue(data[i][j]);
//                }
//            }
//            String filePath =getLocation()+"/"+ "data.xlsx";
//            FileOutputStream outputStream = new FileOutputStream(filePath);
//            workbook.write(outputStream);
//            outputStream.close();
//
//            JOptionPane.showMessageDialog(null,"Xuất dữ liệu thành công");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
