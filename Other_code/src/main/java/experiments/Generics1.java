package experiments;

import lombok.SneakyThrows;
import lombok.val;

import java.util.concurrent.CompletableFuture;

public class Generics1 {
    static class GrandParent {}
    static class Parent extends GrandParent {}
    static class Children extends Parent {}
    
    @SneakyThrows
    public static void main(String[] args) {
//        val array = new Parent[3];
////        array[0] = new GrandParent();
//        array[1] = new Parent();
//        array[2] = new Children();
//
//        val list = new ArrayList<Parent>();
////        list.add(new GrandParent());
//        list.add(new Parent());
//        list.add(new Children());
//
//        val f = CompletableFuture.completedFuture("hh");
//        System.out.println("f = " + f.get());
//        System.out.println();
//        System.out.println("f = " + f.get());
        
        val one = CompletableFuture.supplyAsync(() -> {
            while (true);
        });
//        val two = CompletableFuture.runAsync(() ->{
//            one.complete("one completed");
//        });
//
//        two.get();
        System.out.println(one.get());
    }
}
