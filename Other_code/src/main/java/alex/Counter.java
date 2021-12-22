package alex;


public class Counter {
    
    public static int sum(int start, int finish) {
        int rsl = 0;
        for (int currentNumber = start; currentNumber <= finish; currentNumber++) {
//            System.out.println("rsl = " + rsl + " & currentNumber = " + currentNumber);
            rsl = rsl + currentNumber;
        }
        return  rsl;
    }
    public static int sumOld(int start, int finish) {
        int rsl = 0;
        for (int sum = start; sum <= finish; sum++) {
//            System.out.println("sum = " + rsl);
            rsl = rsl + rsl;
        }
        return rsl;
    }
    
    public static void main(String[] args) {
        System.out.println(sum(0, 10));
        System.out.println(sum(3, 8));
        System.out.println(sum(1, 1));
        System.out.println(sum(0, 5));
    }
    
}