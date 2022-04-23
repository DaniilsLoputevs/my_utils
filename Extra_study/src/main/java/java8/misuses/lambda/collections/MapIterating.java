package java8.misuses.lambda.collections;

import java8.structures.Annotations;
import java8.structures.User;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class MapIterating {
    @Annotations.Ugly
    class UsingOldGoodEntrySet {
        /**
         * @param users map of <User UUID, User>
         * @return map of <User UUID, User name>
         */
        public Map<String, String> getUserNames(Map<String, User> users) {
            Map<String, String> userNames = new HashMap<>();
            users.entrySet().forEach(user -> userNames.put(user.getKey(), user.getValue().getName()));
            return userNames;
        }
    }
    
    @Annotations.My
    static class MyClass {
        public Map<String, String> getUserNames(Map<String, User> users) {
            return users.entrySet().stream().collect(Collectors.toMap(
                    Map.Entry::getKey, entry -> entry.getValue().getName()
            ));
        }
    }
    
    @Annotations.Good
    class UsingMapForEach {
        public Map<String, String> getUserNames(Map<String, User> users) {
            Map<String, String> userNames = new HashMap<>();
            users.forEach((key, value) -> userNames.put(key, value.getName()));
            return userNames;
        }
    }
    
    @Annotations.Good
    class UsingMapTransform {
        public Map<String, String> getUserNames(Map<String, User> users) {
            return users.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey,
                            entry -> entry.getValue().getName()));
        }
    }
    
    /** Why Map not implements Iterable<Entry<K,V>>??? */
    @Data
    class Pair<K, V> {
        private K key;
        private V val;
    }
    
    static class MyMap<K, V> implements Iterable<Pair<K, V>> {
        @NotNull
        @Override
        public Iterator<Pair<K, V>> iterator() {
            return null;
        }
    }
    
    public static void example() {
        for (var entry : new MyMap<String, String>()) {
            System.out.println(entry.getKey() + entry.getVal());
        }
    }
}
