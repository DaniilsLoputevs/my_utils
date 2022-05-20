package smallthings.docx;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DocxCreateDOCX4J {
    public static final String PATH = "C:\\Danik\\DEVELOPMENT\\TM2-dev-excel\\NEW_LIB_DOCX/";
    
    @SneakyThrows
    public static void main(String[] args) {
//        Context.jc = new JAXBContextImpl.JAXBContextBuilder().build();
        val obj = new DocxCreateDOCX4J();
        val bytes = obj.makeFile();
        obj.toDisk(PATH, bytes, "docx");
//        File exportFile = new File("welcome.docx");
    }
    
    @SneakyThrows
    public byte[] makeFile() {
        val wordPackage = WordprocessingMLPackage.createPackage();
        val mainDocumentPart = wordPackage.getMainDocumentPart();
//        mainDocumentPart.addStyledParagraphOfText("Title", "Hello World!");
        mainDocumentPart.addParagraphOfText("Welcome To Baeldung x1");
        mainDocumentPart.addParagraphOfText("Welcome To Baeldung x2");
        
        
        int writableWidthTwips = wordPackage.getDocumentModel()
                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
        int columnNumber = 3;
        val tbl = TblFactory.createTable(3, 3, writableWidthTwips/columnNumber);
        
//        val rows = tbl.getContent().stream().map(Tr.class::cast).collect(Collectors.toList());
        val rows = tbl.getContent();
        for (Object row : rows) {
            val tr = (Tr) row;
            
            List<Object> cells = tr.getContent();
            for(Object cell : cells) {
                Tc td = (Tc) cell;
                td.getContent().add("My content here");
            }
        }
        
        
        @Cleanup val out = new ByteArrayOutputStream();
        wordPackage.save(out);
        return out.toByteArray();
    }
//    private static P addImageToParagraph(Inline inline) {
//        ObjectFactory factory = new ObjectFactory();
//        P p = factory.createP();
//        R r = factory.createR();
//        p.getContent().add(r);
//        Drawing drawing = factory.createDrawing();
//        r.getContent().add(drawing);
//        drawing.getAnchorOrInline().add(inline);
//        return p;
//    }
    
    private static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm");
    
    @SneakyThrows
    private void toDisk(String dirPath, byte[] fileBytes, String fileExt) {
        val fileName = dirPath + LocalDateTime.now().format(LOCAL_DATE_TIME_FORMATTER) + "." + fileExt;
        @Cleanup val outputStream = new FileOutputStream(fileName);
        outputStream.write(fileBytes);
    }
}
