package tm2;

public class OtherClass {
    public final static String one = initOne();
    static String two = "two";
    static String three;
    
    static {
        System.out.println("one = " + one);
        System.out.println("two = " + two);
        System.out.println("three 1 = " + three);
        three = "three";
        System.out.println("three 2 = " + three);
    }
    
    public static String initOne() { return "one"; }
}
