package xlsx;

import lombok.SneakyThrows;
import lombok.val;
import xlsx.data.User;
import xlsx.old.ExcelBlock;
import xlsx.old.ExcelBook;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    private static final String FILE_NAME = "C:/Users/aleks/Desktop/daniils_dev/xlsx_reports/report_{TIME}.xlsx"
            .replace("{TIME}", new SimpleDateFormat("HH_mm_ss").format(new Date()));
    
    private static final List<User> DEV_DATA = Arrays.asList(
            new User(1L, "ttt@mailru", true),
            new User(2L, "aaa@mailru", true),
            new User(3L, "ttt2bbb@mailru", false)
    );
    
    public static void main(String[] args) {
        new Main().doReport();
    }
    
    // TODO : merged regions
    // TODO : how to get cellStyle
    // TODO : all data types cast
    // TODO :
    public void doReport() {
//        val headerStyle = new XSSFCellStyle();
        val report = new ExcelBook()
                .addBlock(new ExcelBlock<>(DEV_DATA)
                        .addColumn("ID", null, User::getId)
                        .addColumn("email", null, User::getEmail)
                        .addColumn("status", null, User::getSomeStatus)
                )
                .toBytes();
        writeToFile(report);
    }
    
    @SneakyThrows
    private static void writeToFile(byte[] bytes) {
//        Path path = Paths.get(FILE_NAME);
//        wb.close(); // is it need?

//        wb.write(new FileOutputStream(FILE_NAME));
//        wb.close();

//        Files.write(Path.of(FILE_NAME), bytes, WRITE);
        
        try (val outputStream = new FileOutputStream(FILE_NAME)) {
            outputStream.write(bytes);
        }

//        FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
//        workbook.write(outputStream);
//        workbook.close();
    }
}
