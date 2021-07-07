package satomi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

//TODO 1. Реализуйте метод unavailable.
//source - имя файла лога
//target - имя файла после анализа.
//Метод anavailable должен находить диапазоны, когда сервер не работал.
//Сервер не работал. если status = 400 или 500.

/**
 * 2. Анализ доступности сервера. [#471727]
 * Метод main - записывает текст в файл "unavailable.csv" *
 */
public class Analize {
    
    /**
     * Метод anavailable должен находить диапазоны, когда сервер не работал
     * Сервер не работал. если status = 400 или 500.
     *
     * @param source - имя файла лога
     * @param target - имя файла после анализа.
     */
    public static void unavailable(String source, String target) {
        try (var buffer = new BufferedReader(new FileReader(source))) {
            String[] strings = new String[2];
            List<String> list = new ArrayList<>();
            AtomicInteger index = new AtomicInteger();
            // отсортировать на у которых есть 400 500  скидать в файл таргет
            buffer.lines().forEach(split -> {
                if (strings[0] == null && (split.contains("400") || split.contains("500"))) {
                    if (split.contains("400")) {
                        String words = split.replaceAll("400", "");
                        strings[0] = words;
                    } else {
                        String words1 = split.replaceAll("500", "");
                        strings[0] = words1;
                    }
                  /* Pattern pattern = Pattern.compile("400.+?500");
                    Matcher matcher = pattern.matcher(split);
                    String text = matcher.replaceAll("");*/
                    //  String[] stroka = split.split(" ");
                    //  strings[0] = stroka[1];
                    // strings[0] = words;
                    
                }
                if ((strings[0] != null && strings[1] == null) && (split.contains("200") || split.contains("300"))) {
                    String[] stroka1 = split.split(" ");
                    strings[1] = stroka1[1];
                }
                if (strings[0] != null && strings[1] != null) {
                    
                    list.add(index.getAndIncrement(), strings[0] + ";" + strings[1]);
                    System.out.println(list);
                    strings[0] = null;
                    strings[1] = null;
                }
            });
            try (var rsl = new BufferedWriter(new FileWriter(target, true))) {
                for (String string : list) {
                    rsl.write(string);
                    rsl.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }
    
    /** Новый вариант */
    public static void unavailableNew(String source, String target) {
        var lines = readFileToList(source);
//        var lines = List.of(
//                "200 10:55:01",
//                "500 10:56:01",  // start
//                "400 10:58:01",
//                "200 10:59:01",  // end
//                "500 11:01:01",  // start
//                "200 11:03:01"   // end
//        );
        var diapasons = new ArrayList<Diapason>();
        var currentDiapason = new Diapason();
        var inDiapason = false;
        for (var line : lines) {
            if (!inDiapason && line.startsWith("400") || line.startsWith("500")) {
                currentDiapason.start = line.substring(4);
                inDiapason = true;
            }
            if (inDiapason && line.startsWith("200") || line.startsWith("300")) {
                currentDiapason.end = line.substring(4);
                inDiapason = false;
                diapasons.add(currentDiapason);
                currentDiapason = new Diapason();
            }
        }
        writeListToFile(target, diapasons.stream().map(Diapason::toString).collect(Collectors.toList()));
//        diapasons.stream().map(Diapason::toString).collect(Collectors.toList())
//                .forEach(System.out::println);
    }
    
    private static List<String> readFileToList(String path) {
        try (var bufferedReader = new BufferedReader(new FileReader(path))) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("IOException: read File to List!");
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }
    
    private static void writeListToFile(String path, List<String> content) {
        try (var writer = new BufferedWriter(new FileWriter(path))) {
            for (String contentLine : content) {
                writer.write(contentLine + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("IOException: write List to File!");
            e.printStackTrace();
        }
    }
    
    /**
     * Это local private class, тут можно и без getters&setters.
     */
    private static class Diapason {
        private String start;
        private String end;
        
        @Override
        public String toString() {
            return start + ';' + end;
        }
    }
    
    
    /**
     * Метод main - записывает текст в файл "unavailable.csv"
     *
     * @param args
     */
    public static void main(String[] args) {
       /* try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.txt"))) {
            out.println("200 10:55:01");
            out.println("500 10:56:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:01");
            out.println("200 11:03:01");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Analize analize = new Analize();
        analize.unavailable("unavailable.txt", "targetFile.txt");
    }
    
}
