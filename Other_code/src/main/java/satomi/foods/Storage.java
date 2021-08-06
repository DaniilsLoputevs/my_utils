package satomi.foods;

import java.util.List;
import java.util.function.Predicate;

/**
 * 1. Хранилище продуктов [#852]
 * Уровень : 2. Джуниор Категория : 2.5. ООД Топик : 2.5.3. LSP
 * Создать классы хранилище продуктов Warehouse, Shop, Trash.
 */
public interface Storage<T> {
    boolean add(T food);
    
    boolean delete(T food);
    
    List<T> findBy(Predicate<T> filter);
    
    List<T> getAll();
    
    void clear();
}
