package satomi.cache;

/**
 * 1. Реализация кеша на SoftReference [#1592]
 * Категория : 2.4. Garbage Collection
 * Топик : 2.4.4. Типы
 * Часть 3
 * Создать класс Emulator для работы с пользователем. Предоставить пользователю возможности:
 * - указать кэшируемую директорию
 * - загрузить содержимое файла в кэш
 * - получить содержимое файла из кэша
 */
public class Emulator {
    DirFileCache dirFileCache;
    
    public Emulator(String cashStringDir) {
        this.dirFileCache = new DirFileCache(cashStringDir);
    }
    
    /**
     * Метод записывает в Map<K, V>
     * where Key - file name,Value - file data
     *
     * @param key   - file name
     * @param value - file content
     */
    public void put(String key, String value) {
        dirFileCache.put(key, value);
    }
    
    /**
     * Метод проверяет есть ли в Мап данный файл
     * Возвращает содержимое по ключу файла
     *
     * @param key - file name
     * @return - file content
     */
    public String get(String key) {
        return dirFileCache.get(key);
    }


//    public static void main(String[] args) {
//        String opisanie = "Столица автоматически переходит в Васюки. " +
//                "Сюда приезжает правительство. " +
//                "Васюки переименовываются в Нью-Москву, Москва — в Старые Васюки.";
//        String opisanie_1 = "Ленинградцы и харьковчане скрежещут зубами, но ничего не могут поделать." +
//                " Нью-Москва становится элегантнейшим центром Европы, а скоро и всего мира.";
//        //директория где находятся файлы для кеширования
//        //String placeDir = "C:/projects/job4j_design/src/main/java/ru/job4j/cache";
//        //   String string = "C:/projects/Temp";
//        var string = "Names.txt";
//        var name = "cache/Address.txt";
//        Emulator emulator = new Emulator(string); // указать кешируемую директорию
////получение по ключу если нет еще такого элемента
//
//        System.out.println("defore - get : " + emulator.get("cache/EmulatorNewFile.txt"));
//// вставка ключ/значение
//        emulator.put("NewVasyki.txt", opisanie);
//        emulator.put(name, opisanie_1);
//// получение значение из Мап по ключу
//        System.out.println("after get (vasyki) : " + emulator.get("NewVasyki.txt"));
//        System.out.println("after get (names) : " + emulator.get("Names.txt"));
//        System.out.println("after get (newFile) : " + emulator.get("cache/EmulatorNewFile.txt"));
//
//
//// в случае если была передана не директория а фаил
//      /*  String cachingDir = "C:/projects/Temp/Names.txt";
//        Emulator emulator = new Emulator(cachingDir);   */
//    }
}
