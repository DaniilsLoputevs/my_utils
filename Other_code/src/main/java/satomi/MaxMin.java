package satomi;


import java.util.Comparator;
import java.util.List;

/**
 * 1. Принципы Kiss, Dry и Yagni [#238813]
 * Категория : 2.5. ООДТопик : 2.5.0. TDD
 * Разработайте класс для поиска максимального и минимального элемента по критерию
 * java.util.Comparator.
 * Убедительная просьба при решении этой задачи не использовать
 * Stream API и методы Collections. Лямбды использовать можно.
 */
public class MaxMin {
    /**
     * Метод returning min or max value
     *
     * @param value      List data
     * @param comparator user Comparator
     * @param <T>
     * @return value min or max
     */
    private static <T> T findBy(List<T> value, Comparator<T> comparator) {
        T rsl = null;
        for (T curr : value) {
            if (rsl == null) {
                rsl = curr;
                continue;
            }
            if (comparator.compare(curr, rsl) > 0) {
                rsl = curr;
            }
        }
        return rsl;
    }
    
    public static void main(String[] args) {
        var minecraft = new MaxMin();
        
        var ints = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Comparator<Integer> comparator = Integer::compareTo;
        
        var min = minecraft.min(ints, comparator);
        var max = minecraft.max(ints, comparator);
        
        System.out.println("min = " + min);
        System.out.println("max = " + max);
    }
    
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findBy(value, comparator);
    }

//    public static void main(String[] args) {
//        MaxMin maxMin = new MaxMin();
//        List<Integer> integers = new ArrayList<>();
//        Comparator<Integer> comparator = Integer::compareTo;
//// запись компаратора в лямбда
//        Comparator<Integer> ompMin = (o1, o2) -> o2.compareTo(o1);  // min
//        Comparator<Integer> integerComparator = (o1, o2) -> o1.compareTo(o2); //max
//        integers.add(0, 1);
//        integers.add(1, 2);
//        integers.add(2, 3);
//        integers.add(3, 4);
//        integers.add(4, 5);
//        integers.add(5, 6);
//        System.out.println("то что по итогу : " + maxMin.max(integers, comparator));
//        System.out.println(maxMin.min(integers, comparator));
//        var finish = findBy(integers, ompMin);
//        System.out.println(finish);
//        var fns = findBy(integers, integerComparator);
//        System.out.println(fns);
//    }
    
    /**
     * Метод находит минимальное значение
     *
     * @param value      List значений
     * @param comparator
     * @param <T>        значение
     * @return min value
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findBy(value, comparator.reversed());
    }
}