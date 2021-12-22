package xlsx.old;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class ExcelBlock<D> {
    private final Iterable<D> data;
    private final List<ExcelColumn> columns = new ArrayList<>();


    public ExcelBlock<D> addColumn(String headerValue, CellStyle headerCS, Function<D, Object> dataGetter) {
        columns.add(new ExcelColumn(headerValue, headerCS, dataGetter, (__) -> null));
        return this;
    }

    public ExcelBlock<D> addColumn(String headerValue, CellStyle headerCS, Function<D, Object> dataGetter, CellStyle dataCS) {
        columns.add(new ExcelColumn(headerValue, headerCS, dataGetter, (__) -> dataCS));
        return this;
    }

    public ExcelBlock<D> addColumn(String headerValue, CellStyle headerCS, Function<D, Object> dataGetter, Function<D, CellStyle> dataCSFunc) {
        columns.add(new ExcelColumn(headerValue, headerCS, dataGetter, dataCSFunc));
        return this;
    }

    void writeToWorkBookSheet(XSSFSheet sheet) {
        int rowIndex = (sheet.getLastRowNum() == 0) ? 1 : sheet.getLastRowNum() + 1;
        var isHeaderRow = true;
        for (val currentRowData : data) {
            val currentRow = sheet.createRow(rowIndex++);

            var cellIndex = 0;
            for (val column : columns) {
                if (isHeaderRow) {
                    createCellAndSetValue(currentRow, cellIndex++, column.headerValue, column.headerCS);
                } else {
                    createCellAndSetValue(currentRow, cellIndex++, column.dataGetter.apply(currentRowData), column.dataCS.apply(currentRowData));
                }
            }
            if (isHeaderRow) isHeaderRow = false;
        }
    }

    private void createCellAndSetValue(XSSFRow row, int cellIndex, Object cellValue, CellStyle cellStyle) {
        val cell = row.createCell(cellIndex);

        // TODO : для всех типов сделать каст и default формат
        if (cellValue instanceof String) cell.setCellValue((String) cellValue);
        if (cellValue instanceof Boolean) cell.setCellValue((Boolean) cellValue);
        if (cellValue instanceof Number) cell.setCellValue(((Number) cellValue).doubleValue());

        if (cellStyle != null) cell.setCellStyle(cellStyle);
    }

    @RequiredArgsConstructor
    private class ExcelColumn {
        private final String headerValue;
        private final CellStyle headerCS;
        private final Function<D, Object> dataGetter;
        private final Function<D, CellStyle> dataCS;
    }
}
