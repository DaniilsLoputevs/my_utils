package java8.misuses.stream;

import lombok.val;

import java.util.List;

public class MyExamples {
    public static void main(String[] args) {
        val ints = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

//        val rsl1 = ints.stream().map(Object::toString).reduce("", String::concat);
//        val rsl2 = ints.stream().map(Object::toString).collect(Collectors.joining(""));
//        System.out.println("rls1 = " + rsl1);
//        System.out.println("rls2 = " + rsl2);
        
        val regExp = "[a-zA-Z0-9]+";
        System.out.println("121312785673aashtasdasd2421241".matches(regExp));
        System.out.println("aaaa".matches(regExp));
        System.out.println("13&%$&#$23aasdasd243%$&$%@#241".matches(regExp));

//        val intsRev = List.of(0, 9, 8, 7, 6, 5, 4, 3, 2, 1);
//        val intsRev = ints.subList(0, ints.size() - 1);
//        val intsRev = new ArrayList<>(ints);
//        Collections.reverse(intsRev);
//        System.out.println(intsRev);

//        System.out.println(ints.subList(5, ints.size() - 1));
//        val rsl = ints.stream()
//                .sorted(Integer::compareTo)
//                .limit(5)
//                .collect(Collectors.toList());
//        System.out.println(rsl);

//        val amount = new BigDecimal("-100.11");
//        System.out.println("amount = " + amount);
//        val rsl = amount.setScale(2, RoundingMode.UNNECESSARY).abs();
//        System.out.println("rsl = " + rsl);
    
    }
    
}