package smallthings.docx;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DocxReadPOI {
    private static final String
            SPLIT = "%s : %s",
            PATH = "C:\\Danik\\DEVELOPMENT\\TM2-dev-excel\\NEW_LIB_DOCX/",
            PATH_READ = "C:\\Danik\\DEVELOPMENT\\TM2-dev-excel\\NEW_LIB_DOCX/manual_docx_01.docx";
    
    @SneakyThrows
    public static void main(String[] args) {
        val obj = new DocxReadPOI();
        obj.readFile(PATH_READ);
    }
    
    @SneakyThrows
    public void readFile(String fileName) {
        @Cleanup val doc = new XWPFDocument(Files.newInputStream(Paths.get(fileName)));
        
        val xwpfWordExtractor = new XWPFWordExtractor(doc);
        val doc2 = xwpfWordExtractor.getDocument();
        String docText = xwpfWordExtractor.getText();
        System.out.println(docText);

//            // find number of words in the document
//            long count = Arrays.stream(docText.split("\\s+")).count();
//            System.out.println("Total words: " + count);
        
        
    }
    
}
