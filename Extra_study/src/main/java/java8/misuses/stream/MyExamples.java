package java8.misuses.stream;

import lombok.val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyExamples {
    public static void main(String[] args) {
        val ints = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
//        val intsRev = List.of(0, 9, 8, 7, 6, 5, 4, 3, 2, 1);
//        val intsRev = ints.subList(0, ints.size() - 1);
        val intsRev = new ArrayList<>(ints);
        Collections.reverse(intsRev);
        System.out.println(intsRev);

//        System.out.println(ints.subList(5, ints.size() - 1));
//        val rsl = ints.stream()
//                .sorted(Integer::compareTo)
//                .limit(5)
//                .collect(Collectors.toList());
//        System.out.println(rsl);
        
    }
}
