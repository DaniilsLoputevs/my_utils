package experiments;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTry {
    public static void main(String[] args) {
        //        var stream = Stream.of("111", "222", "333", "444", "555", "666");
        Supplier<Stream<String>> supplier = () -> Stream.of("111", "222", "333", "444", "555", "666");
        
        var u = String.join(", ", supplier.get().collect(Collectors.toList()));
        System.out.println("first   = " + u);
        
        var v = supplier.get().collect(Collectors.joining(", "));
        System.out.println("second  = " + v);

//        var s =  supplier.get().reduce(new Yup(),  (one, two) -> one + two);
//        System.out.println("third   = " + s);

//        String h = () -> {
//                return "";
//        }.get();
//        val temp = if() {
//            return "ttt";
//            // ....
//        } else "";
//        var temp = initTemp();

//        var j = () -> {return Stream.of("111", "222", "333", "444", "555", "666");};
        var k = supplier.get();
        
        
        var obj = new Yup();
        supplier.get().collect(Collectors.toList()).forEach(each -> {
            if (each.startsWith("1")) {
                obj.one = each;
            } else if (each.startsWith("1")) {
                obj.two = each;
            } else if (each.startsWith("2")) {
                obj.thr = each;
            }
        });
        
        var list = List.of(1, 2, 3);
        
        for (var number : list) {
            System.out.println(number);
            System.out.println(number);
            System.out.println(number);
            System.out.println(number);
            System.out.println(number);
            System.out.println(number);
        }
    }
    
    static class Yup {
        private String one;
        private String two;
        private String thr;
    }
}
