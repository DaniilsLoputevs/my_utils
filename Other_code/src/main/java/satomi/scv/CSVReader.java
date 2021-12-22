package satomi.scv;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 7. Scanner [#504791]
 * Уровень : 2. Джуниор Категория : 2.2. Ввод-вывод Топик : 2.2.1. Ввод-вывод
 * 1. Ознакомиться с форматом CSV.
 * 2. Создать класс CSVReader. Задача класса прочитать данные из CSV файла и вывести их.
 * В качестве входных данных задается путь к файлу path, разделитель delimiter,
 * приемник данных out и фильтр по столбцам filter. Ключ -out имеет только два допустимых
 * значения stdout или путь к файлу, куда нужно вывести. Например, если есть файл CSV со
 * столбцами name, age, birthDate, education, children и программа запускается таким образом:
 * java -jar target/csvReader.jar -path=file.csv -delimiter=;  -out=stdout -filter=name,age
 * то программа должна прочитать файл по пути file.txt и вывести только столбцы name, age в консоль.
 * В качестве разделителя данных выступает ;
 * 3. Для чтения аргументов использовать класс Args и задания "5.1. Именованные аргументы".
 * 4. Программа должна запускаться с консоли в виде jar файла как показано в примере. *
 * 5. Для чтения файла использовать класс Scanner.
 * 6. Добавить валидацию аргументов, как в классе Search.(проверка маасива аргументов на наличие согласно заданию)
 */
public class CSVReader {
    private static final String FATAL = "External identifier for import";
    private final ArgsName argsName;
    private final List<Integer> integerList = new ArrayList<>();

    // при создании экземпляра класса принимает данные из массива аргументов
    //и проводит их парсинг в Мар<String, String>
    public CSVReader(String[] args) {
        this.argsName = ArgsName.of(args); //статик методом передали аргументы из массива
    }

    public ArgsName getArgsName() {
        return argsName;
    }

    /**
     * Метод проводит запись номера нахождения элемента для фильтрации в строке - key-filter
     * колличество аргументов для фильтрации м.б. любое
     *
     * @param header строка считанная Scanner и разбитая разделителем на массив
     * @param filter key-filter Map(ArgsName)
     * @return List<Integer> number position words
     */
    private List<Integer> parseIndices(String[] header, String filter) {
        var filters = argsName.get("filter").split(",");
        for (int i = 0; i < header.length; i++) {
            for (String f : filters) {
                if (header[i].contains(f)) {
                    integerList.add(i);
                }
            }
        }
        return integerList;
    }

    /**
     * метд проводит чтение денных их файла и выводит то что в запросе(массив аргументов) в консоль
     * (в текущей версии массив аргуементов содержит параметры:
     * -path=file.csv -delimiter=";"  -out=stdout -filter=name,age)
     */
    public void readF() {
        String file = argsName.get("path");
        String sc;
        List<String> fin = new ArrayList<>();
        try (FileReader reader = new FileReader(file)) {
            Scanner scanner1 = new Scanner(reader);
            while (scanner1.hasNext()) {
                sc = scanner1.nextLine();
                var splt = sc.split(argsName.get("delimiter"));
                parseIndices(splt, argsName.get("filter"));
                for (Integer integer : integerList) {
                    fin.add(splt[integer]);
                }
                System.out.println(fin);
                fin.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    
    
//    public static void main(String[] args) {
//        // должна быть валидация параметров массива аргументов
//        if (args.length < 4) {
//            throw new IllegalArgumentException("HSQueryResult folder is null."
//                    + " or one of the following parameters is not specified"
//                    + " Usage java -jar dir.jar ROOT_FOLDER.");
//        }
//        CSVReader csvReader = new CSVReader(args);
//        csvReader.readF();
//    }
}
