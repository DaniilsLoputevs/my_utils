package xlsx.old;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelBook {
    private final XSSFWorkbook workbook = new XSSFWorkbook();
    private final List<ExcelBlock<?>> blocks = new ArrayList<>();

    public ExcelBook addBlock(ExcelBlock<?> block) {
        blocks.add(block);
        return this;
    }

    @SneakyThrows
    public byte[] toBytes() {
        val ws = workbook.createSheet("sheet 1");
        blocks.forEach(block -> block.writeToWorkBookSheet(ws));

        try (val bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            return bos.toByteArray();
        }
    }

}
