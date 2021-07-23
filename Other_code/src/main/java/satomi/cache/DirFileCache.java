package satomi.cache;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 1. Реализация кеша на SoftReference [#1592]
 * Категория : 2.4. Garbage Collection
 * Топик : 2.4.4. Типы
 * Часть 2
 * Создать программу, эмулирующее поведение данного кеша.
 * Программа должна считывать текстовые файлы из системы и выдавать текст
 * при запросе имени файла. Если в кеше файла нет. Кеш должен загрузить себе данные.
 * По умолчанию в кеше нет ни одного файла. Текстовые файл должны лежать в одной директории.
 * Пример. Names.txt, Address.txt - файлы в системе.
 * При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.
 */
public class DirFileCache extends AbstractCache<String, String> {
    
    private String cachingDir;
    
    public DirFileCache(String cachingDir) {
        setCachingDir(cachingDir);
    }
    
    public String getCachingDir() {
        return cachingDir;
    }
    
    public void setCachingDir(String cachingDir) {
        this.cachingDir = cachingDir;
    }
    
    /**
     * @see AbstractCache#load(Object)
     */
    @Override
    protected String load(String key) {
        try {
            return Files.readString(Paths.get(cachingDir + "/" + key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Назначение - Указать кешируемую директорию
     * Метод проводить разбор всех файлов в указанной дериктории  на пары ключ/значение,
     * проводит запись этих файлов в Map
     *
     * @param key кешируемая директория
     * @return directory name or file date else null if key is empty
     */
//    @Override
//    protected String load(String key) {
//
//        String cachingDir = key;
//        String value = null;
//        if (cachingDir != null) {
//            Path path = Paths.get(cachingDir);
//
//            Path nameFile = path.getFileName(); //имя файла
//            String nameStr = nameFile.toString();
//// записали в значение то что в файле
//            try {
//                var intoFile = Files.readString(path);
//                value = intoFile;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            put(nameStr, value); // записавли в Мапу из АбстрактКеш
//
//        }
//        return value;
//    }
    
    
    /**
     * @see AbstractCache#put(Object, Object) (Object)
     */
    @Override
    public void put(String key, String value) {
        super.put(key, value);
    }
    
    /**
     * @see AbstractCache#get(Object)
     */
    @Override
    public String get(String key) {
        return super.get(key);
    }
}

