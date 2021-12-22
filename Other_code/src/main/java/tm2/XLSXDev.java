package tm2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static tm2.XLSXUtil.*;

@Slf4j
public class XLSXDev {
    
//    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm");

    private static final String FILE_NAME = "C:/Users/Admin/Desktop/dev_excel/excel_example.xlsx";
//    private static final String FILE_NAME = "C:/Users/Admin/Desktop/tx/tx_2021-10-19__18-22.xlsx";
    
    public static void main(String[] args) {
//        updFile();
//        checkFile();
        System.out.println(Optional.empty().isEmpty());
        System.out.println(Optional.empty().isPresent());
    }
    
    /**
     * BigDecimalDev
     * String
     * Integer
     * LocalDateTime
     * Date
     * Calendar
     */
    @SneakyThrows
    private static void updFile() {
        val workbook = new XSSFWorkbook();
        val workSheet = workbook.createSheet("sheet 1");
        val firstRow = workSheet.createRow(0);
        
        val stringCellStyle = creatStringCellStyle(workSheet.getWorkbook());
        stringCellStyle.setBorderTop(BorderStyle.THIN);
        stringCellStyle.setBorderBottom(BorderStyle.THIN);
        stringCellStyle.setBorderRight(BorderStyle.THIN);
        stringCellStyle.setBorderLeft(BorderStyle.THIN);
        
        val dateCellStyle = createDataCellStyle(workSheet.getWorkbook(), "dd.MM.yy HH:mm");
        val numberStyle = createNumberCellStyle(workSheet.getWorkbook(), "0.0000000000000");
        
        
        val date = new Date();
        val calendar = Calendar.getInstance();
        System.out.println("date = " + date);
        System.out.println("calendar = " + calendar);
        
        val sdf = new SimpleDateFormat("dd.MM.yy HH:mm");
        System.out.println("date = " + sdf.format(date));
        System.out.println("calendar = " + sdf.format(calendar.getTime()));
        
        createCell(firstRow, 0, 123456, numberStyle);
        createCell(firstRow, 1, new BigDecimal("10000.275"), numberStyle);
        createCell(firstRow, 2, "String value", stringCellStyle);
//        createCell(firstRow, 3, sdf.parse("28.10.21 13:36"), dateCellStyle);
        
//        firstRow.createCell(3).setCellValue(date);
//        firstRow.createCell(4).setCellValue(calendar);
        createCell(firstRow, 3, date, dateCellStyle);
        createCell(firstRow, 4, calendar, dateCellStyle);
        
        try (val outputStream = new FileOutputStream(FILE_NAME)) {
            outputStream.write(workbookToBytes(workbook));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void checkFile() {
        try (val excelFile = new FileInputStream(FILE_NAME)) {
            val workbook = new XSSFWorkbook(excelFile);
            val datatypeSheet = workbook.getSheetAt(0);
            
            for (val eachRow : datatypeSheet) {
                for (val eachCell : eachRow) {
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    
                    System.out.println(" : type=" + eachCell.getCellTypeEnum() + " : format =" + eachCell.getCellStyle().getDataFormatString());
//                    System.out.println(" : type=" + eachCell.getCellTypeEnum() + " : format =" + eachCell.getCellStyle().getDataFormat());
//                    System.out.println("value = " + eachCell.getValue() + " : type = " + eachCell.getCellTypeEnum());
                
                }
            }
//
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Done");
    }
    
    
}
