package hr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class TaskOne {
    public static void main(String[] args) {

//        var t = Stream.of()
        
        
//        var fac = new GoldFactory();
//        var scopeTwo = new ObjectScope<>(fac);
//        scopeTwo.exe(f -> {
//                    System.out.println(f);
//                    f.name = "Azino777";
//                    f.goldTotal++;
//                    System.out.println(f);
//                }
//        );
//
//        var goldFac = ObjectScope.of(new GoldFactory()).exe(
//                (f) -> f.goldTotal = 100
//        );
//        var list = List.of("0", "1", "2");
//        list.add("3");
//        list.add("4");
//        list.forEach(System.out::println);
      
        
        
    }
}
class ObjectScope<T> {
    private final T object;
    private final List<Consumer<T>> codeParts = new ArrayList<>();
    
    public static <T> ObjectScope<T> of(T target) {
        return new ObjectScope<>(target);
    }
    
    public ObjectScope(T object) {
        this.object = object;
    }
    
//    public void add(Consumer<T>[] parts) {
//        Collections.addAll(codeParts, parts);
//    }
//    public void exe() {
//     codeParts.forEach(e -> e.accept(object));
//    }
//    public void exe(List<Consumer<T>> parts) {
//        parts.forEach(e -> e.accept(object));
//    }
    public T exe(Consumer<T> code) {
        code.accept(object);
        return object;
    }
    public void exe(Consumer<T>... parts) {
        for (var part : parts) {
            part.accept(object);
        }
    }
}

class ListScope<T> {
    private final List<T> objects;
    private final List<Consumer<T>> codeParts = new ArrayList<>();
    
    public ListScope(List<T> objects) {
        this.objects = objects;
    }
    
    public void add(Consumer<T>[] parts) {
        Collections.addAll(codeParts, parts);
    }
    public void exe() {
        objects.forEach(obj -> codeParts.forEach(part -> part.accept(obj)));
    }
}
class GoldFactory {
    public String name;
    public int goldTotal;
    
    @Override
    public String toString() {
        return new StringJoiner(", ", GoldFactory.class.getSimpleName() + "[", "]")
                .add("goldTotal=" + goldTotal)
                .toString();
    }
}
//class CodeScope {
//    private final List<Object> objects;
//    private final List<Consumer> codeParts = new ArrayList<>();
//
//    public CodeScope(List<Object> objects) {
//        this.objects = objects;
//    }
//
//    public void add(Consumer part) {
//        codeParts.add(part);
//    }
//    public void exe() {
//        codeParts.forEach(e -> e.accept());
//        codeParts.forEach(Consumer<::accept);
//    }
//}
