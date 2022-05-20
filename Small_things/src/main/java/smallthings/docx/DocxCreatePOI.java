package smallthings.docx;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTPImpl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;

public class DocxCreatePOI {
    private static final String SPLIT = "%s : %s";
    public static final String PATH = "C:\\Danik\\DEVELOPMENT\\TM2-dev-excel\\NEW_LIB_DOCX/";
    
    @SneakyThrows
    public static void main(String[] args) {
        val obj = new DocxCreatePOI();
        val bytes = obj.makeFile();
        obj.toDisk(PATH, bytes, "docx");
        
    }
    
    @SneakyThrows
    public byte[] makeFile() {
        @Cleanup val doc = new XWPFDocument();
    
        // document header and footer
//        val header = doc.createHeader(HeaderFooterType.DEFAULT).createParagraph().createRun();
//        header.setBold(true);
//        header.setFontSize(40);
//        header.setText("ACCOUNT STATEMENT");
    
        val p1 = doc.createParagraph();
        p1.setAlignment(ParagraphAlignment.LEFT);
        val r1 = p1.createRun();
    
        r1.setText(format(SPLIT, "First Name & Last name OR Company Name", "DISPLAY_NAME"));
        r1.setText(format(SPLIT, "First Name & Last name OR Company Name", "DISPLAY_NAME"));
        r1.setText(format(SPLIT, "First Name & Last name OR Company Name", "DISPLAY_NAME"));
        r1.setText(format(SPLIT, "First Name & Last name OR Company Name", "DISPLAY_NAME"));
    
    
    
    
//        doc.createParagraph().createRun().setText("");
        val table = doc.createTable();
//        doc.createParagraph().createRun().setText("");

//        val cursor = header.getCTR().newCursor();
//        val getCur = cursor.toEndToken(); //now we are at end of the CTTbl
        //there always must be a next start token. Either a p or at least sectPr.
//        while(cursor.toNextToken() != XmlCursor.TokenType.START);
//        val table = doc.insertNewTbl(cursor);
    
        //Creating first Row
        XWPFTableRow row1 = table.createRow();
//        row1.addNewTableCell().addParagraph().createRun().setText("");
//        row1.addNewTableCell().addParagraph().createRun().setText("First Row, First Column");
        row1.addNewTableCell().setText("First Row, First Column");
        row1.addNewTableCell().setText("First Row, Second Column");
        row1.addNewTableCell().setText("First Row, Third Column");
//        row1.addNewTableCell().addParagraph().createRun().setText("First Row, First Column");
//        row1.addNewTableCell().addParagraph().createRun().setText("First Row, Second Column");
//        row1.addNewTableCell().addParagraph().createRun().setText("First Row, Third Column");
//        row1.addNewTableCell().addParagraph().createRun().setText("");
//        table.createRow();
    
//        doc.createParagraph().createRun();
        val p2 = doc.createParagraph();
        val p2CTPara = (CTPImpl) p2.getCTP();
        
        // DEB : ((XWPFParagraph)document.getBodyElements().get(2)).paragraph.toString()
//        p2CTPara.addNewPPr().addNewRPr().addNewLang().setVal("en-US");
        // 698
        
        
//        doc.insertTable(1, table);

//
//        //Creating second Row
//        XWPFTableRow row2 = table.createRow();
//        row2.addNewTableCell().addParagraph().createRun().setText("Second Row, First Column");
//        row2.addNewTableCell().addParagraph().createRun().setText("Second Row, Second Column");
//        row2.addNewTableCell().addParagraph().createRun().setText("Second Row, Third Column");
//
//        //create third row
//        XWPFTableRow row3 = table.createRow();
//        row3.addNewTableCell().addParagraph().createRun().setText("Third Row, First Column");
//        row3.addNewTableCell().addParagraph().createRun().setText("Third Row, Second Column");
//        row3.addNewTableCell().addParagraph().createRun().setText("Third Row, Third Column");

//        makeTxTable(doc.createTable(), reportData);
        
        
        // =================
        val xwpfWordExtractor = new XWPFWordExtractor(doc);
        val doc2 = xwpfWordExtractor.getDocument();
        String docText = xwpfWordExtractor.getText();
        System.out.println(docText);
        // =================
        
    
        @Cleanup val out = new ByteArrayOutputStream();
        doc.write(out);
        return out.toByteArray();
        
    }
    
    
    
    private static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm");
    
    @SneakyThrows
    private void toDisk(String dirPath, byte[] fileBytes, String fileExt) {
        val fileName = dirPath + LocalDateTime.now().format(LOCAL_DATE_TIME_FORMATTER) + "." + fileExt;
        @Cleanup val outputStream = new FileOutputStream(fileName);
        outputStream.write(fileBytes);
    }
}
