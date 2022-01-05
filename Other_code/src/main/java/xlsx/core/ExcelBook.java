package xlsx.core;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.HorizontalAlignment.LEFT;

public class ExcelBook {
    private final XSSFWorkbook workbook = new XSSFWorkbook();
    private final List<ExcelBlock<?>> blocks = new ArrayList<>();
    private final XSSFSheet firstWorksheet = workbook.createSheet("sheet 1");
    private boolean autoSize = true;

    public ExcelBook addBlock(ExcelBlock<?> block) {
        blocks.add(block);
        return this;
    }

    /**
     * подгоняет все колонки во размеру.
     * Default value: true
     */
    public ExcelBook autoSize(boolean autoSize) {
        this.autoSize = autoSize;
        return this;
    }

    public ExcelCellStyle.ExcelCellStyleBuilder buildStyle() {
        return ExcelCellStyle.builder()
                .cellStyleInner(workbook.createCellStyle())
                .horizontalAlignment(LEFT)
                .dataFormatHelper(workbook.getCreationHelper().createDataFormat());
    }

    /**
     * @param format - {@link ExcelCellStyle#format}
     */
    public ExcelCellStyle.ExcelCellStyleBuilder buildStyle(String format) {
        return buildStyle().format(format);
    }

    @SneakyThrows
    public byte[] toBytes() {
        blocks.forEach(block -> block.writeToWorkBookSheet(firstWorksheet));

        if (autoSize) autoSizeAllColumns(firstWorksheet);

        @Cleanup val bos = new ByteArrayOutputStream();
        workbook.write(bos);

        return bos.toByteArray();
    }

    public void autoSizeAllColumns(XSSFSheet sheet) {
        val lastColumnIndex = sheet.getRow(0).getLastCellNum();
        for (int columnIndex = 0; columnIndex < lastColumnIndex; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

}
