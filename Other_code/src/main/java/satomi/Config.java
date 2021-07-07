package satomi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * 1. Читаем файл конфигурации [#471726]
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    //TODO Напишите тест ConfigTest.
    // Реализуйте метод load по аналогии с методом toString.

    /**
     * Метод load - должен считать все ключи в карту values.
     */
    public void load() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(this.path))) {
            buffer.lines().filter(x -> x.contains("=") && !x.startsWith("#"))
                    .forEach(split -> {
                        String[] spliter = split.split("=");
                        if (spliter.length <= 1) {
                            //values.put(spliter[0], null);
                            throw new IllegalArgumentException();
                        } else {
                        values.put(spliter[0], spliter[1]);
                        }
                        //System.out.println("array :" + spliter[0] + " " + spliter[1]);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает value соотвествующее заданному в параметре ключу.
     * @param key ключ для поиска
     * @return значение value соотвествующее данному ключу в Map<String, String> values;
     */
    public String value(String key) {
        for (var string : values.entrySet()) {
            if (string.getKey().contains(key)) {
                return string.getValue();
            }
        }
        throw  new NoSuchElementException("there is no such element");
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        // System.out.println(new Config("app.properties"));
        Config config = new Config("app.properties");
        config.load();

        System.out.println(config.values);
        System.out.println(config.value("hibernate.dialect"));
    }

}
