package util;

import lombok.val;
import org.apache.commons.collections4.IterableUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppUtil {
    /**
     * Пример:
     * Afghanistan : AF
     * Russia : RU
     */
    private static final Map<String, String> countriesNameAndISOCode = Arrays.stream(Locale.getISOCountries())
            .map(countryISOCode -> new Locale("", countryISOCode))
            .collect(Collectors.toMap(
                    locale -> locale.getDisplayCountry(Locale.ENGLISH), // Locale.ENGLISH - это важно!
                    Locale::getCountry
            ));
    
    /**
     * https://countrycode.org/
     */
    public static String getCountryCodeISO2(String countryName) {
        return Optional.ofNullable(countriesNameAndISOCode.get(countryName))
                .orElseThrow(() -> new RuntimeException("Can't get ISO code 2 for country=" + countryName));
    }
    
    public static <T> Function<Throwable, T> nullExceptionally(Consumer<Throwable> consumer) {
        return throwable -> {
            consumer.accept(throwable);
            return null;
        };
    }
    
    public static <T> Optional<T> find(Iterable<T> source, Predicate<T> search) {
        for (val elem : source) {
            if (search.test(elem)) return Optional.ofNullable(elem);
        }
        return Optional.empty();
    }
    
    public static <T> Optional<T> findFromEnd(List<T> source, Predicate<T> search) {
        val iterator = source.listIterator(source.size());
        while (iterator.hasPrevious()) {
            val elem = iterator.previous();
            if (search.test(elem)) return Optional.ofNullable(elem);
        }
        return Optional.empty();
    }
    
    public static <T> boolean contains(Iterable<T> source, Predicate<T> search) {
        for (val elem : source) {
            if (search.test(elem)) return Optional.ofNullable(elem).isPresent();
        }
        return false;
    }
    
    public static <T> List<T> filter(Iterable<T> source, Predicate<T> saveIfTrue) {
        val rsl = new ArrayList<T>();
        for (val elem : source) {
            if (saveIfTrue.test(elem)) rsl.add(elem);
        }
        return rsl;
    }
    
    /* "Разовые" операции вместо методов Stream. Если используется 1 операция, а 2 и более(пример filter + map), то выгоднее юзать эти методы. */
    
    public static <T, R> List<R> map(Iterable<T> source, Function<T, R> mapFunc) {
        val rsl = new ArrayList<R>(IterableUtils.size(source));
        for (val elem : source) {
            rsl.add(mapFunc.apply(elem));
        }
        return rsl;
    }
    
    /**
     * Пример:
     * users -> Map<id, user>
     */
    public static <K, V> Map<K, V> toMapIt(Iterable<V> source, Function<V, K> getKeyFunc) {
        val rsl = new HashMap<K, V>(IterableUtils.size(source));
        for (val elem : source) {
            rsl.put(getKeyFunc.apply(elem), elem);
        }
        return rsl;
    }
    
    public static <S, K, V> Map<K, V> toMap(Iterable<S> source, Function<S, K> getKeyFunc, Function<S, V> getValFunc) {
        val rsl = new HashMap<K, V>(IterableUtils.size(source));
        for (val elem : source) {
            rsl.put(getKeyFunc.apply(elem), getValFunc.apply(elem));
        }
        return rsl;
    }
    
    public static <K, V> Map<K, V> mapOf(K key, V val) {
        val rsl = new HashMap<K, V>();
        rsl.put(key, val);
        return rsl;
    }

//    public static <K, V> Map<K, V> mapOfPairs(Pair<K, V>... pairs) {
//        Objects.requireNonNull(pairs);
//        val rsl = new HashMap<K, V>(pairs.length);
//        for (val p : pairs) {
//            rsl.put(p.getFirst(), p.getSecond());
//        }
//        return rsl;
//    }
    
    public static <T> void ifIsNotEmpty(List<T> source, Consumer<List<T>> doFunc) {
        if (!source.isEmpty()) doFunc.accept(source);
    }
    
    /** @return - если Лист пустой, то вернётся null. */
    public static <T, R> R ifIsNotEmpty(List<T> source, Function<List<T>, R> doFunc) {
        return (!source.isEmpty()) ? doFunc.apply(source) : null;
    }
    
    @SafeVarargs
    public static <T> List<T> concat(List<T>... lists) {
        val rsl = new ArrayList<T>();
        for (List<T> list : lists) {
            rsl.addAll(list);
        }
        return rsl;
    }
}
