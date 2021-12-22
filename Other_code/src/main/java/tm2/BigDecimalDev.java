package tm2;

import lombok.val;

import java.math.BigDecimal;

public class BigDecimalDev {
    public static void main(String[] args) {
        val rsl = new BigDecimal("10.50").add(new BigDecimal("500"));
        System.out.println("rsl = " + rsl);
    }
}
