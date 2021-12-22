package satomi.scv;

import java.io.*;
import java.util.*;

/**
 * 5.1. Именованные аргументы [#471724]
 * Уровень : 2. Джуниор Категория : 2.2. Ввод-вывод Топик : 2.2.1. Ввод-вывод
 * В этом задании вам нужно будет написать программу,
 * которая принимает массив параметров и разбивает их на пары: ключ, значение.
 * Exception - массив аргументов пуст, value - пусто
 */
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    /**
     * Метод возвращает занчение из внутренней Мар
     *
     * @param key ключ для поиска в карте
     * @return возвращаемое значние соотвествующее указанному ключу
     */
    public String get(String key) {
        return values.get(key);
    }

    /**
     * Метод принимает массив параметров и разбивает их на пары: ключ, значение.
     *
     * @param args массив параметров
     */
    private void parse(String[] args) {
        if (args.length == 0) throw new IllegalArgumentException();
        
        Arrays.stream(args).forEach(p -> {
            String[] argAndValue = p.substring(1).split("=");
            
            if (argAndValue.length < 2) throw new IllegalArgumentException();
            
            values.put(argAndValue[0], argAndValue[1]);
            //System.out.println("key map: " + argAndValue[0] + " value map: " + argAndValue[1]);
        });
        // System.out.println("to cho zashlo" + values);
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        //System.out.println("args.length " + args.length);
        names.parse(args);
        return names;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ArgsName.class.getSimpleName() + "[", "]")
                .add("values=" + values)
                .toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
      /* ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));*/

        //ArgsName zorg =
        // ArgsName.of(new String[]{"-path=file.csv", "-delimiter=\";\"", "-out=stdout", "-filter=name,age"});
        // с двойными скобками - делиметр
        ArgsName zorg = ArgsName.of(
                new String[] {"-path=file.csv", "-delimiter=;", "-out=stdout", "-filter=name,age"});

        System.out.println(zorg.get("out"));
        String[] args1 = {"-path=file.csv", "-delimiter=;", "-out=stdout", "-filter=name,age"};
        Arrays.stream(args1).forEach(p -> {
            System.out.println("bez podstroki : " + p);
            String[] str = p.substring(1).split("=");
            String str1 = p.substring(1);
            System.out.println("То что в подстроке : " + str1);
        });
        zorg.parse(args1);
        System.out.println("zorg : " + zorg.values.toString());
        var delimiter = zorg.get("delimiter");
        System.out.println("delimiter : " + delimiter);
        String filter = zorg.get("filter");

        System.out.println("filter : " + filter);

        if (filter.contains(",")) {
            var g = filter.split(",");
            System.out.println("filter.split " + g[0] + " __ " + g[1]);
        }

        // прочитать данные из файла
        File file = new File("file.csv");
        // String file = "file.csv";
       /* try (Scanner scanner = new Scanner(new File(String.valueOf(file)))) {
            while (scanner.hasNext()) {
                var s = scanner.next().split(";");
                String[] arrt = new String[](scanner.next());
                zorg.parse();
                System.out.print(scanner.next());
                System.out.print(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try (var bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            Scanner scanner;
            int i = 0;
            String[] words = new String[2];
            while ((line = bufferedReader.readLine()) != null) {
                scanner = new Scanner(line);
                scanner.useDelimiter(zorg.get("delimiter")); //                scanner.useDelimiter(";");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    //System.out.println(" String data : " + data);
                    if (data.contains("name")) {
                        words[0] = data;

                    }
                    if (data.contains("age")) {
                        words[1] = data;
                        System.out.println("that's need to print : " + words[0] + " " + words[1]);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



