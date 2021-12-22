package satomi.scv;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
public class CSVReaderNew {
    private final String path;
    private final String delimiter;
    private final List<Column> contentTable = new ArrayList<>();
    private int rowsCount;
    
    public CSVReaderNew(String path, String delimiter) {
        this.path = path;
        this.delimiter = delimiter;
    }
    
    public void read() {
        try (var reader = new FileReader(path)) {
            var sc = new Scanner(reader);
    
            if (!sc.hasNext()) return;
    
            /* читаем header(1-ая итерация вынесена до начала цикла) */
            var fileLine = sc.nextLine();
//            System.out.println("fileLine = " + fileLine);
            
            for (var columnHeader : fileLine.split(delimiter)) {
                contentTable.add(new Column(columnHeader));
            }
//            System.out.println("contentTable.size = " + contentTable.size());
            
            while (sc.hasNext()) {
                fileLine = sc.nextLine();
                int columnIndex = 0;
                rowsCount++;
                
                for (var cellValue : fileLine.split(delimiter)) {
                    contentTable.get(columnIndex).addCell(cellValue);
                    columnIndex++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Column getColumn(String columnName) {
        for (var column : contentTable) {
//            System.out.println("columnName = |"+ columnName + "|");
//            System.out.println("column.getHeader() = |"+ column.getHeader() + "|");
            if (column.getHeader().equals(columnName)) return column;
        }
        return null;
    }
    
    public List<String> getContentOfColumns(String[] columnNames) {
        var rowsAsString = new ArrayList<String>();
        var columns = Arrays.stream(columnNames).map(this::getColumn).collect(Collectors.toList());
        
        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
            var row = new StringBuilder();
            for (var column : columns) {
                row.append(column.getCell(rowIndex)).append(", ");
            }
            /* удаляем ", " в конце каждого row */
            row.delete(row.length() -2, row.length());
            rowsAsString.add(row.toString());
        }
        return rowsAsString;
    }
    
    public static void main(String[] args) {
        /* для запуска из IDEA */
        if (args.length == 0) {
            args = new String[]{"-path=file.csv", "-delimiter=;", "-out=stdout", "-filter=name,age"};
        }
        
        var config = ArgsName.of(args);
        System.err.println("config.path = " + config.get("path"));
        System.err.println("config.delimiter = " + config.get("delimiter"));
        System.err.println("config.out = " + config.get("out"));
        System.err.println("config.filter = " + config.get("filter"));
        
        var reader = new CSVReaderNew(config.get("path"), config.get("delimiter"));
        reader.read();
        var content = reader.getContentOfColumns(config.get("filter").split(","));
        
        new OutputWriter().out(config.get("out"), content);
    }
}
