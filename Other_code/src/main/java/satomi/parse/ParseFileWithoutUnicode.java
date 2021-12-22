package satomi.parse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Predicate;

public class ParseFileWithoutUnicode implements Parse {
    private final File file; //все поля отмечены финал

    public ParseFileWithoutUnicode(File file) {
        this.file = file;
    }

    @Override
    public String content(Predicate<Character> filter) {
        String output = "";
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = bufferedInputStream.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
