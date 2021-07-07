package satomi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestUtils {
    public static String getResourcePath(String resourcePath) {
        final var pathURL = Optional.ofNullable(
                Thread.currentThread().getContextClassLoader().getResource(resourcePath)
        );
        return pathURL.map(url -> new File(url.getPath()).getPath()).orElse(null);
    }
//    public static List<String>
    
    /**
     * Преобразовать содержимое файла в List, далее работать текстом в виде List.
     *
     * @param path            - Путь файла.
     * @return List<String> Все строчки из файла.
     */
    public static List<String> readFileToList(String path) {
        try (var bufferedReader = new BufferedReader(new FileReader(path))) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("IOException: IOHelper - read File to List!");
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }
}
