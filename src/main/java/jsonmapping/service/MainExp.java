package jsonmapping.service;

import jsonmapping.models.OneMoreModel;

import java.util.HashSet;
import java.util.Set;

public class MainExp {
//    public static void main(String[] args) {
//        var model = new AnnotatedModel(
//                1234,
//                "abc",
//                new OneMoreModel(777, "here", "other")
//        );
//        var rslOne = StaticMapper.map(model, MapCase.JSON_FULL);
//        var rslTwo = StaticMapper.map(model, MapCase.JSON_NAME);
//        System.out.println("rslOne: " + rslOne);
//        System.out.println("rslTwo: " + rslTwo);
//        System.err.println("something");
//    }

    public static void main(String[] args) {
        var temp = new OneMoreModel[]{
                new OneMoreModel(111, " name", "target"),
                new OneMoreModel(111, " name", "target")
        };
        int[] test = new int[]{1, 2, 3, 4, 5};
        var test1 = new String[]{"abc", " asd", " asdf"};

        var set = new HashSet<OneMoreModel>();
        set.add(temp[0]);
        set.add(temp[1]);

        System.out.println(Set.of(test1));
        System.out.println(set);
//        System.out.println(Set.of(temp));
        System.out.println(Set.of(1, 2, 3, 4, 5));
    }

    static abstract class AbsBase {

        protected void printSomething() {
            System.out.println(this.getClass().getSimpleName());
        }
    }

    static class Model extends AbsBase {

    }
}
