package satomi.scv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputWriter {
    
    public void out(String outPath, List<String> content) {
        if (outPath.equals("stdout")) content.forEach(System.out::println);
        else writeContentToFile(outPath, content.toString());
    }
    
    private void writeContentToFile(String path, String content) {
        try (var writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
