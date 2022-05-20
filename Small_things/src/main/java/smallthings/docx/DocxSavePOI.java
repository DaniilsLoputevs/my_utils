package smallthings.docx;

import lombok.*;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class DocxSavePOI {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private static final String
            SPLIT = "%s : %s",
            PATH = "C:\\Danik\\DEVELOPMENT\\TM2-dev-excel\\NEW_LIB_DOCX/",
            PATH_SRC = "C:\\Danik\\DEVELOPMENT\\TM2-dev-excel\\NEW_LIB_DOCX/manual_docx_02_src.docx",
            PATH_TRG = "C:\\Danik\\DEVELOPMENT\\TM2-dev-excel\\NEW_LIB_DOCX/manual_docx_02_trg.docx";
    
    public static final List<RD> reportData = Arrays.asList(
            new RD(LocalDateTime.now(), "Desc 1", new BigDecimal("10"), new BigDecimal("10"), new BigDecimal("10")),
            new RD(LocalDateTime.now(), "Desc 2", new BigDecimal("20"), new BigDecimal("20"), new BigDecimal("20"))
    );
    
    private static final String
            INFO_BLOCK_FIRST = "${First info block}",
            INFO_BLOCK_FIRST_VAL
                    = "displayName : User Admin \n"
                    + "email : eee@mail.ru \r\n\r\n"
                    + "phone : +44 5693 12424"
                    + "more info on : na6 url ^_^ \r";
    
    @SneakyThrows
    public static void main(String[] args) {
        new DocxSavePOI().readThenModThenSaveTo(PATH_SRC, PATH_TRG);
    }
    private XWPFParagraph setInfoBlockFirstToP(XWPFParagraph p, Object reportData) {
        p.removeRun(0);  // убираем пустой шаблонный Run
        this.setLine(p.createRun(), "display name", "User Admin");
        this.setLine(p.createRun(), "email", "eee@mail.ru");
        this.setLine(p.createRun(), "phone", "+44 5693 12424");
        this.setLine(p.createRun(), "more info on", "na6 url ^_^");
        return p;
    }
    
    @SneakyThrows
    public void readThenModThenSaveTo(String srcFileName, String trgFileName) {
        @Cleanup val doc = new XWPFDocument(new FileInputStream(srcFileName));
        
        this.setInfoBlockFirstToP(doc.getParagraphArray(0), null);
//        this.replaceText(doc.getParagraphArray(0), INFO_BLOCK_FIRST, INFO_BLOCK_FIRST_VAL);
        
        val table = doc.getTableArray(0); // получить Первую таблицу.
        val templateRow = table.getRows().get(table.getNumberOfRows() - 1);
        
        reportData.stream()
                .map(it -> this.txRowToTableRow(table, templateRow, it))
                .forEach(table::addRow);
        
        table.removeRow(1); // убираем пустой шаблонный Row
        
        @Cleanup val out = new FileOutputStream(trgFileName);
        doc.write(out);
    }
    private void setLine(XWPFRun r, String key, String value) {
        r.setText(format(SPLIT, key, value));
//        r.addBreak();
        r.addCarriageReturn();
    }
    
    private void replaceText(XWPFParagraph p, String search, String newVal) {
        for (val r : p.getRuns()) {
            val text = r.getText(0);
            if (text == null) return;
            r.setText(text.replace(search, newVal), 0);
        }
    }
    
    @SneakyThrows
    private XWPFTableRow txRowToTableRow(XWPFTable table, XWPFTableRow template, RD txRow) {
        val ctRow = CTRow.Factory.parse(template.getCtRow().newInputStream());
        val row = new XWPFTableRow(ctRow, table);
        
        /* row.getTableCells().get(${columnNumber}).getParagraphArray(0).createRun()
        тут работаем с шаблон файлом, точно знаем что это ОК, а не magic number */
        row.getTableCells().get(0).getParagraphArray(0).createRun().setText(txRow.dateTime.format(DATE_TIME_FORMATTER));
        row.getTableCells().get(1).getParagraphArray(0).createRun().setText(txRow.description);
        row.getTableCells().get(2).getParagraphArray(0).createRun().setText(txRow.credit.toString());
        row.getTableCells().get(3).getParagraphArray(0).createRun().setText(txRow.debit.toString());
        row.getTableCells().get(4).getParagraphArray(0).createRun().setText(txRow.balance.toString());
        return row;
    }
    
    @Data
    @AllArgsConstructor
    static
    class RD {
        protected final LocalDateTime dateTime;
        protected final String description;
        protected final BigDecimal credit;
        protected final BigDecimal debit;
        protected BigDecimal balance; // late init
    }
}
