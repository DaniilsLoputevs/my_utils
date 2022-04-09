package tm2;

import lombok.val;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_MONTH;

public class BigDecimalDev {
    public static void main(String[] args) {
//        val rsl = new BigDecimal("10.50").add(new BigDecimal("500"));
//        System.out.println("rsl = " + rsl);

//        val one = new BigDecimal("490.7999878").divide(BigDecimal.ONE, 2, RoundingMode.HALF_DOWN);
//        val one = new BigDecimal("490.7999878").setScale(2, DOWN);
//        val one = new BigDecimal("490.555").setScale(0, DOWN);
//        System.out.println("one = " + one);

//        System.out.println(one.equals(two));
//        val one = new BigDecimal("200.00");
//        val two = new BigDecimal("100.00");
//        val rsl = two.divide(one, 2, RoundingMode.UP).multiply(new BigDecimal("100"));
//        System.out.println("rsl = " + Integer.valueOf(rsl.toString().split("\\.")[0]));
    
    
        val sendDatePlusThreeDays = GregorianCalendar.getInstance();
        sendDatePlusThreeDays.set(DAY_OF_MONTH, sendDatePlusThreeDays.get(DAY_OF_MONTH) +5);
        val t = EXCHANGE_IS_NOT_AVAILABLE_SDF.format(sendDatePlusThreeDays.getTime());
        System.out.println(t);
        
//        val all = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
//        val all = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
//        val halfSize = all.size() / 2;
//        val two = all.subList(halfSize, all.size());
//        val one = all.subList(0, halfSize);
//        System.out.println(one);
//        System.out.println(two);
    
//        System.out.println("all = " + all);
//        System.out.println("page 0 = " + slicePage(0, all));
//        System.out.println("page 1 = " + slicePage(1, all));
//        System.out.println("page 2 = " + slicePage(2, all));
//        System.out.println("page 3 = " + slicePage(3, all));
//        System.out.println("page 4 = " + slicePage(4, all));
//        System.out.println("page 5 = " + slicePage(5, all));
    }
    private static final SimpleDateFormat EXCHANGE_IS_NOT_AVAILABLE_SDF = new SimpleDateFormat("dd.MM.yyyy");

//    private static List<?> slicePage(int page, List<?> list) {
//        if (list == null || list.isEmpty()) return new ArrayList<>();
//        if (page == 0) throw new IllegalArgumentException("parameter \"page\" must have value 1 or more");
//
//        var startIndex = (page == 1) ? 0 : (page-1) * PAGE_SIZE;
//        if (startIndex >= list.size()) startIndex = list.size();
//        var endIndex = startIndex + PAGE_SIZE;
//        if (endIndex >= list.size()) endIndex = list.size();
//        System.out.println(startIndex);
//        System.out.println(endIndex);
//
//        return list.subList(startIndex, endIndex);
//    }
    
    private static final int PAGE_SIZE = 3;
}
