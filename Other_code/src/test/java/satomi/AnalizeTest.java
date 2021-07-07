package satomi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * 3.0. Тестирование IO [#471718]
 * Вернитесь к задаче "2. Анализ доступности сервера".
 * Создайте на код тесты с классом TemporaryFolder.
 */
public class AnalizeTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException { // указали эксепшен для IO
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) { //заполнии исходный файл
            out.println("200 10:55:01");
            out.println("500 10:56:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:01");
            out.println("200 11:03:01");
        }
        //getAbsolutePath() является частью класса File .
        // Эта функция возвращает абсолютный путь к указанному
        // файловому объекту. Если путь к файловому объекту является абсолютным,
        // то она просто возвращает путь к текущему файловому объекту.
        //Analize.unavailable - вызом метода через объект где этот метод прописан.
        //метод в таком случе д.б. static для участия в тесте
        Analize.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        //чтобы узнать каков итог работы метода, код ниже
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
            //https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
            //    Метод append() — обновляет значение объекта, который вызвал метод.
            //    Этот метод в Java принимает boolean, char, int, long, Strings и т.д.
        }
        assertEquals("10:56:01;10:59:01" + "11:01:01;11:03:01", rsl.toString());
    }
    
    /**
     * Тест полностью рабочий.
     * source - должен лежать в папке с ресами под тесты.
     * target - создаётся в temp dir
     */
    @Test
    public void test() throws IOException {
        var source = getResourcePath("source.txt");
        var target = folder.newFile("target.txt").getPath();

        Analize.unavailableNew(source, target);

        var rsl = readFileToList(target);

        assertEquals("10:56:01;10:59:01", rsl.get(0));
        assertEquals("11:01:01;11:03:01", rsl.get(1));
    }
    @Test
    public void testt() throws IOException {
        System.out.println("".equals(null));
    }
    
    /* вынес это в отдельный класс, типа Test Utils ↓↓↓ */
    
    public static String getResourcePath(String resourcePath) {
        final var pathURL = Optional.ofNullable(
                Thread.currentThread().getContextClassLoader().getResource(resourcePath)
        );
        return pathURL.map(url -> new File(url.getPath()).getPath()).orElse(null);
    }
    
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