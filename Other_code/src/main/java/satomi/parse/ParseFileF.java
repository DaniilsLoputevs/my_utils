package satomi.parse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Predicate;

public class ParseFileF implements Parse {
    private final File file; //все поля отмечены финал

    public ParseFileF(File file) {
        this.file = file;
    }

    @Override
    public String content(Predicate<Character> filter) {
        var output = new StringBuilder();
        try (var bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = bufferedInputStream.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
                output.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        File file  = new File("/job4j_threads/pom_tmp.xml");
        ParseFileF parseFileF = new ParseFileF(file);
        
        var allContent = parseFileF.content((character) -> true);
        var unicodeContent = parseFileF.content((character) -> character < 0x80);
    }
}
