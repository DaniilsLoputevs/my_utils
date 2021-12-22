package satomi.parse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 1. Visibility. Общий ресурс вне критической секции [#1102]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.3. Синхронизация
 * Поправьте код с ошибками в коде.
 * - Избавиться от get set за счет передачи File в конструктор.
 * - Ошибки в многопоточности. Сделать класс Immutable. Все поля final.
 * - Ошибки в IO. Не закрытые ресурсы. Чтение и запись файла без буфера.
 * - Нарушен принцип единой ответственности. Тут нужно сделать два класса.
 * - Методы getContent написаны в стиле копипаста.
 * Нужно применить шаблон стратегия. content(Predicate<Character> filter)
 */
public class ParseFile {
    private final File file; //все поля отмечены финал

    public ParseFile(File file) {
        this.file = file;
    }

    //TODO predicate Нужно применить шаблон стратегия. content(Predicate<Character> filter)
    public String getContent() {
        //  try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
        String output = "";
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = bufferedInputStream.read()) > 0) {
                output += (char) data;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getContentWithoutUnicode() {
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
