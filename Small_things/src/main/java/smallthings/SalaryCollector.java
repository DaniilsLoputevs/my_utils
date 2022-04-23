package smallthings;

import lombok.val;

public class SalaryCollector {
    private static final String SALARY = "" +
            "30 apr 455\r\n" +
            "11 may 500\r\n" +
            "1 jun 500\r\n" +
            "21 jun 500\r\n" +
            "7 jul 500\r\n" +
            "14 jul 500\r\n" +
            "3 aug 500\r\n" +
            "11 sep 500\r\n" +
            "11 sep 500\r\n" +
            "29 oct 500\r\n" +
            "30 nov 1000\r\n" +
            "22 dec 1000\r\n" +
            "2 feb 1000\r\n" +
            "10 mar 650\r\n" +
            "10 mar 650"
//            .replace("  ", "-")
            ;
    
    public static void main(String[] args) {
        
        int total =0;
        /* example: transfer="30 apr 455" */
        for (val transfer : SALARY.split("\r\n")) {
            System.out.println("transfer = " + transfer);
           val amount =transfer.split(" ")[2];
           total+= Integer.parseInt(amount);
        }
        System.out.println("total = " + total);
    }
}
