package tm2;

import lombok.val;

import java.math.BigDecimal;

public class ManualRun {
    public static void main(String[] args) {
//        val vbAmount = new BigDecimalDev(90);
//        val balanceAfterTransfer = new BigDecimalDev(90);
////        val balanceAfterTransfer = new BigDecimalDev(390); // true????
//        System.out.println("step 1 = " + eq(balanceAfterTransfer, vbAmount));
//        System.out.println("step 2 = " + lw(balanceAfterTransfer, vbAmount));
//        val rsl = (eq(balanceAfterTransfer, vbAmount) || lw(balanceAfterTransfer, vbAmount));
//
//        System.out.println("rsl expected true - 90 && 90");
//        System.out.println("actual = " + rsl);
    
//        check(80, 100, true);
//        check(80, 70, false);
//        check(80, 80, true);
        Long rsl = null;
        Double not = null;
        accept(rsl);
        accept(not);
    }
    
    private static void accept(Long id) {
        System.out.println("long in");
    }
    
    private static void accept(Double id) {
        System.out.println("long in");
    }
    
    /**
     * vb = 80 && rb = 100
     * TEST : false
     * vb = 80 && rb = 70
     * TEST : true
     * vb = 80 && rb = 80
     * TEST : true
     */
    public static void check(int vb, int rb, boolean excepted) {
        System.out.println("vb = " + vb + " && rb = " + rb);
        val rsl = (eq(new BigDecimal(rb), new BigDecimal(vb)) || gt(new BigDecimal(rb), new BigDecimal(vb)));
        System.out.println("TEST : " + (excepted == rsl));
    }
//    public static void check(int vb, int rb, boolean excepted) {
//        System.out.println("vb = " + vb + " && rb = " + rb);
//        val rsl = ( gt(new BigDecimalDev(rb), new BigDecimalDev(vb)) || eq(new BigDecimalDev(rb), new BigDecimalDev(vb)));
//        System.out.println("TEST : " + (excepted == rsl));
//    }
    
    /**
     * Is {@param dig} lower {@param compareWith}
     */
    public static boolean lw(BigDecimal dig, BigDecimal compareWith) {
        return dig.compareTo(compareWith) < 0;
    }
    
    /**
     * Is {@param dig} greater {@param compareWith}
     */
    public static boolean gt(BigDecimal dig, BigDecimal compareWith) {
        return dig.compareTo(compareWith) > 0;
    }
    
    /**
     * Is {@param dig} equals {@param compareWith}
     */
    public static boolean eq(BigDecimal dig, BigDecimal compareWith) {
        return dig.compareTo(compareWith) == 0;
    }
    
}
