package xlsx.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;


@Slf4j
@RequiredArgsConstructor
public class ExcelBlock<D> {
    private final Iterable<D> data;
    @Getter
    private final List<ExcelColumn> columns = new ArrayList<>();

    public ExcelBlock<D> addColumn(String headerValue, ExcelCellStyle headerStyle, Function<D, Object> dataGetter) {
        columns.add(new ExcelColumn(headerValue, headerStyle.terminate(), dataGetter, (__) -> ExcelCellStyle.EMPTY));
        return this;
    }

    public ExcelBlock<D> addColumn(String headerValue, ExcelCellStyle headerStyle, Function<D, Object> dataGetter, ExcelCellStyle dataStyle) {
        columns.add(new ExcelColumn(headerValue, headerStyle.terminate(), dataGetter, (__) -> dataStyle));
        return this;
    }

    public ExcelBlock<D> addColumn(String headerValue, ExcelCellStyle headerStyle, Function<D, Object> dataGetter, Function<D, ExcelCellStyle> dataStyleFunc) {
        columns.add(new ExcelColumn(headerValue, headerStyle.terminate(), dataGetter, dataStyleFunc));
        return this;
    }

    // TODO : сделать как-то, паралелное исполнение.
    void writeToWorkBookSheet(XSSFSheet sheet) {
        int rowIndex = (sheet.getLastRowNum() == 0) ? 0 : sheet.getLastRowNum() + 1;
        var isHeaderRow = true;
        for (val currentRowData : data) {
            val currentRow = sheet.createRow(rowIndex++);

            var cellIndex = 0;
            for (val column : columns) {
                if (isHeaderRow) {
                    createCellAndSetValue(currentRow, cellIndex++, column.headerValue, column.headerCS);
                } else {
                    createCellAndSetValue(currentRow, cellIndex++, column.dataGetter.apply(currentRowData), column.dataStyle.apply(currentRowData).terminate());
                }
            }
            if (isHeaderRow) isHeaderRow = false;
        }
    }

    private void createCellAndSetValue(XSSFRow row, int cellIndex, Object cellValue, CellStyle cellStyle) {
        val cell = row.createCell(cellIndex);

        if (cellValue == null) cell.setCellValue("");
        else if (cellValue instanceof String) cell.setCellValue((String) cellValue);
        else if (cellValue instanceof Number) cell.setCellValue(((Number) cellValue).doubleValue());
        else if (cellValue instanceof Boolean) cell.setCellValue((Boolean) cellValue);
        else if (cellValue instanceof Calendar) cell.setCellValue((Calendar) cellValue);
        else if (cellValue instanceof Date) cell.setCellValue(toCalendar((Date) cellValue));
        else if (cellValue instanceof LocalDate) cell.setCellValue(toCalendar((LocalDate) cellValue));
        else if (cellValue instanceof LocalDateTime) cell.setCellValue(toCalendar((LocalDateTime) cellValue));
        else {
            log.warn("cell value : try to set unsupported type: " + cellValue.getClass().getSimpleName());
            cell.setCellValue(cellValue.toString());
        }

        if (cellStyle != null) cell.setCellStyle(cellStyle);
    }

    @RequiredArgsConstructor
    private class ExcelColumn {
        private final String headerValue;
        private final CellStyle headerCS;
        private final Function<D, Object> dataGetter;
        private final Function<D, ExcelCellStyle> dataStyle;
    }
    
    
    /* Utils */
    
    // для большего перевода одних блин дат в другие блин даты: https://www.logicbig.com/how-to/java-8-date-time-api/to-date-conversion.html
    
    public static Calendar toCalendar(LocalDateTime localDateTime) {
        return GregorianCalendar.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()));
    }
    
    public static Calendar toCalendar(LocalDate localDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        //assuming start of day
        calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
        return calendar;
    }
    
    public static Calendar toCalendar(Date date) {
        val cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    public static LocalDateTime toLocalDateTime(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
    }
}
