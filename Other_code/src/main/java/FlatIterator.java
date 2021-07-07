import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Stream API - map()     :: объект Преобразовать -> объект
 * Stream API - flatMap() :: Множество(List) Преобразовать -> объект
 *
 * Iterator     - пойти по деталям объекта.    object.forEach(...)
 * FlatIterator - пойти по деталям множества.  list<Object>.forEach(...)
 *
 * Iterator - это по кадровая кино-плёнка.
 * hasNext() - показывает что, мы ещё не дошли до конца
 * next() - даёт след. кадр
 * * Когда hasNext() нам вернёт false -> значит плёнка закончилась
 * * next() - не должен давать нам что-то, левое, только нужные именно нам элементы.
 *
 * FlatIterator - пройтись по каждому кадру плёнки которая состоит
 * N разрезанных частей, но всё же, это одна плёнка(цельный фильм).
 */
public class FlatIterator<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> globalIterator;
    private Iterator<T> insideIterator;
    
    
    public FlatIterator(Iterator<Iterator<T>> globalIterator) {
        this.globalIterator = globalIterator;
        this.insideIterator = globalIterator.next();
    }
    
    
    @Override
    public boolean hasNext() {
        /**
         * while - мотает Пустые кадры, ->
         * берём след кадр если кадры на Отрезке кончилась, то берём след Отрезок.
         * Если кончились отрезки, значит мы закончили фильм. hasNext() =>> false
         * пока фильм не кончился, мы ДОЛЖНЫ делать hasNext() =>> true
         */
        while (!insideIterator.hasNext() && globalIterator.hasNext()) {
            //  globalIterator.hasNext() - здесь нужен т.к. в методе происходит вызов next(), перед ним
            // Обязательно! должен быть вызов hasNext()
            insideIterator = globalIterator.next();
        }
        return insideIterator.hasNext();
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return insideIterator.next();
    }
    
    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatIterator<Integer> flat = new FlatIterator<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
    
}
