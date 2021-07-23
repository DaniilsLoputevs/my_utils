package satomi.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Реализация кеша на SoftReference [#1592]
 * Категория : 2.4. Garbage Collection
 * Топик : 2.4.4. Типы
 * Часть 1
 * Создать структуру данных типа кеш. Кеш должен быть абстрактный.
 * То есть необходимо, что бы можно было задать ключ получения объекта кеша и в случае
 * если его нет в памяти, задать поведение загрузки этого объекта в кеш. Для это выделим класс:
 *
 * @param <K> - file name
 * @param <V> - file content
 */
public abstract class AbstractCache<K, V> {
    //where will be placed file name(K), file date(V)
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();
    
    /**
     * метод записывает в Map<K, V>
     * where Key -file name,Value - file data
     *
     * @param key   - file name
     * @param value - file content
     */
    public void put(K key, V value) {
        SoftReference<V> softReference = new SoftReference<>(value);
        cache.put(key, softReference);
    }
    
    /**
     * Метод проверяет есть ли в Мап данный файл, если нет то будет загружен с диска.
     * Возвращает содержимое по ключу файла
     *
     * @param key - file name
     * @return - file content
     */
    public V get(K key) {
// getOrDefault()        Параметры: этот метод принимает два параметра:
//ключ: это ключ элемента, значение которого должно быть получено.
//defaultValue: значение по умолчанию , которое должно быть возвращено,
// если никакое значение не сопоставлено с указанным ключом.
//в конкретном случе, если такого ключа нет, выйдет null значение для записи в value
        
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }
    
    /**
     * В случае если его нет в Мап, задать поведение загрузки этого объекта в Мап
     *
     * @param key - file name
     * @return - file content
     */
    protected abstract V load(K key);
}
