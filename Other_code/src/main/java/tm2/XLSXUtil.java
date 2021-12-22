package tm2;

import lombok.val;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;

public class XLSXUtil {
    /**
     * XSSFWorkbook.bytes() - не даёт нужный результат.
     */
    public static byte[] workbookToBytes(XSSFWorkbook workbook) {
        try (val bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
    
    public static XSSFCell createCell(XSSFRow row, int cellIndex, String value) {
        val rsl = row.createCell(cellIndex);
        rsl.setCellValue(value);
        rsl.setCellType(CellType.STRING);
        return rsl;
    }
    
    public static XSSFCell createCell(XSSFRow row, int cellIndex, String value, CellStyle cellStyle) {
        val rsl = row.createCell(cellIndex);
        rsl.setCellValue(value);
//        rsl.setCellType(CellType.NUMERIC);
        rsl.setCellStyle(cellStyle);
        return rsl;
    }
    
    public static XSSFCell createCell(XSSFRow row, int cellIndex, Number value, CellStyle cellStyle) {
        val rsl = row.createCell(cellIndex);
        rsl.setCellValue(value.doubleValue());
        rsl.setCellType(CellType.NUMERIC);
        rsl.setCellStyle(cellStyle);
        return rsl;
    }
    
    public static XSSFCell createCell(XSSFRow row, int cellIndex, Date value, CellStyle cellStyle) {
        val rsl = row.createCell(cellIndex);
        rsl.setCellType(CellType.NUMERIC);
        rsl.setCellStyle(cellStyle);
        rsl.setCellValue(value);
        return rsl;
    }
    
    public static XSSFCell createCell(XSSFRow row, int cellIndex, Calendar value, CellStyle cellStyle) {
        val rsl = row.createCell(cellIndex);
        rsl.setCellType(CellType.NUMERIC);
        rsl.setCellStyle(cellStyle);
        rsl.setCellValue(value);
        return rsl;
    }
    
    /**
     * @param pattern -
     */
    public static CellStyle createNumberCellStyle(XSSFWorkbook workbook, String pattern) {
        val cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(pattern));
//        cellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("0.00"));
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        return cellStyle;
    }
    
    /**
     * @param pattern -
     *                0 - будет Числовой формат (без знаков после запятой)
     *                0.00 - будет Числовой формат (2 знака после запятой)
     *                dd.MM.yy - будет Дата формат
     *                HH:ss - будет Время формат
     *                dd.MM.yy HH:ss - (другие форматы, работает так же, как Время или Дата)
     */
    public static CellStyle createDataCellStyle(XSSFWorkbook workbook, String pattern) {
        val cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(pattern));
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        return cellStyle;
    }
    
    public static CellStyle creatStringCellStyle(XSSFWorkbook workbook) {
        val cellStyle = workbook.createCellStyle();
        
        val font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("Calibri");
        font.setColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFont(font);
        
        return cellStyle;
    }
    
    
    public static LocalDateTime calendarToLocalDateTime(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
    }
    
    public static Calendar localDateTimeToCalendar(LocalDateTime localDateTime) {
        return GregorianCalendar.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()));
    }
    
    public static void main(String[] args) {
        val prevDayStartCalendar = new GregorianCalendar();
        prevDayStartCalendar.set(DAY_OF_MONTH, prevDayStartCalendar.get(DAY_OF_MONTH) - 1);
        prevDayStartCalendar.set(HOUR_OF_DAY, 0);
        prevDayStartCalendar.set(MINUTE, 0);
        prevDayStartCalendar.set(SECOND, 0);
        val prevDay = calendarToLocalDateTime(prevDayStartCalendar);
        
        val prevDayEndCalendar = (GregorianCalendar) prevDayStartCalendar.clone();
        prevDayEndCalendar.set(HOUR_OF_DAY, 23);
        prevDayEndCalendar.set(MINUTE, 59);
        prevDayEndCalendar.set(SECOND, 59);
        val prevDayEnd = calendarToLocalDateTime(prevDayEndCalendar);
        
        val firstDayOfMonthCalendar = (GregorianCalendar) prevDayStartCalendar.clone();
        /* firstDayOfMonthCalendar.set(DAY_OF_MONTH, 1) - почему-то Добавляет +1 день, не Устанавливает 1-ый день месяца. */
        firstDayOfMonthCalendar.add(DAY_OF_MONTH, -firstDayOfMonthCalendar.get(DAY_OF_MONTH) + 1);
        firstDayOfMonthCalendar.set(HOUR_OF_DAY, 0);
        firstDayOfMonthCalendar.set(MINUTE, 0);
        firstDayOfMonthCalendar.set(SECOND, 0);
        val firstDayOfMonth = calendarToLocalDateTime(firstDayOfMonthCalendar);
        
        
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("firstDayOfMonth = " + formatter.format(firstDayOfMonth));
        System.out.println("prevDay =         " + formatter.format(prevDay));
        System.out.println("prevDayEnd =      " + formatter.format(prevDayEnd));
    }
//    public static void main(String[] args) {
//        val firstDayOfMonthCalendar = new GregorianCalendar();
//        /* firstDayOfMonthCalendar.set(DAY_OF_MONTH, 1) - почему-то Добавляет +1 день, не Устанавливает 1-ый день месяца. */
//        firstDayOfMonthCalendar.add(DAY_OF_MONTH, -firstDayOfMonthCalendar.get(DAY_OF_MONTH) + 1);
//        firstDayOfMonthCalendar.set(HOUR_OF_DAY, 0);
//        firstDayOfMonthCalendar.set(MINUTE, 0);
//        firstDayOfMonthCalendar.set(SECOND, 0);
//        val firstDayOfMonth = calendarToLocalDateTime(firstDayOfMonthCalendar);
//
//        val prevDayStartCalendar = new GregorianCalendar();
//        prevDayStartCalendar.set(DAY_OF_MONTH, prevDayStartCalendar.get(DAY_OF_MONTH) - 1);
//        prevDayStartCalendar.set(HOUR_OF_DAY, 0);
//        prevDayStartCalendar.set(MINUTE, 0);
//        prevDayStartCalendar.set(SECOND, 0);
//        val prevDay = calendarToLocalDateTime(prevDayStartCalendar);
//
//        val prevDayEndCalendar = (GregorianCalendar) prevDayStartCalendar.clone();
//        prevDayEndCalendar.set(HOUR_OF_DAY, 23);
//        prevDayEndCalendar.set(MINUTE, 59);
//        prevDayEndCalendar.set(SECOND, 59);
//        val prevDayEnd = calendarToLocalDateTime(prevDayEndCalendar);
//
//
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        System.out.println("firstDayOfMonth = " + formatter.format(firstDayOfMonth));
//        System.out.println("prevDay =         " + formatter.format(prevDay));
//        System.out.println("prevDayEnd =      " + formatter.format(prevDayEnd));
//    }
}
