package explanations;

import java.util.List;

public class Generics {
    public static void main(String[] args) {
//        System.out.println(UsingComplexLambdaInPlace.findEditors());
//        System.out.println(MyClass.findEditors());
//        System.out.println(VALUES.get(1));
        
        addAll(List.of(new Human()));
        addAll(List.of(new Adult()));
        addAll(List.of(new ForumTrol()));
        
    }
    static class Human{}
    static class Adult extends Human {}
    static class ForumTrol extends Adult {}
    
    public static boolean addAll(List<? super Adult> c) {
        return false;
    }
}
