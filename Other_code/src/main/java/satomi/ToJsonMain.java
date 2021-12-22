package satomi;

import lombok.val;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;

public class ToJsonMain {

    public static void main(String[] args) {
//        val firstDayOfMonthCalendar = new GregorianCalendar();
//        firstDayOfMonthCalendar.set(HOUR_OF_DAY, 0);
//        firstDayOfMonthCalendar.set(MINUTE, 0);
//        firstDayOfMonthCalendar.set(SECOND, 0);
//
//        val prevDayStartCalendar = new GregorianCalendar();
//        /* prevDayStartCalendar.set(DAY_OF_MONTH, 1) - почему-то Добавляет +1 день, не Устанавливает 1-ый день месяца. */
//        prevDayStartCalendar.add(DAY_OF_MONTH, -prevDayStartCalendar.get(DAY_OF_MONTH) + 1);
//        prevDayStartCalendar.set(HOUR_OF_DAY, 0);
//        prevDayStartCalendar.set(MINUTE, 0);
//        prevDayStartCalendar.set(SECOND, 0);
//
//        val firstDayOfMonth = LocalDateTime.ofInstant(firstDayOfMonthCalendar.toInstant(), firstDayOfMonthCalendar.getTimeZone().toZoneId());
//        val prevDayStart = LocalDateTime.ofInstant(prevDayStartCalendar.toInstant(), prevDayStartCalendar.getTimeZone().toZoneId());
//
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        System.out.println("firstDayOfMonthCalendar = " + formatter.format(firstDayOfMonth));
//        System.out.println("prevDayStartCalendar =        " + formatter.format(prevDayStart));
        
        
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, 1);
//        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(cal.getTime());
//// Output "Wed Sep 26 14:23:28 EST 2012"
//
//        String formatted = format1.format(cal.getTime());
//        System.out.println(formatted);
//// Output "2012-09-26"
        
        
        // ######################################3
        val firstDayOfMonthCalendar = new GregorianCalendar();
        firstDayOfMonthCalendar.set(HOUR_OF_DAY, 0);
        firstDayOfMonthCalendar.set(MINUTE, 0);
        firstDayOfMonthCalendar.set(SECOND, 0);
//        firstDayOfMonthCalendar.set(MONTH, 8);

        val prevDayStartCalendar = new GregorianCalendar();
        /* prevDayStartCalendar.set(DAY_OF_MONTH, 1) - почему-то Добавляет +1 день, не Устанавливает 1-ый день месяца. */
        prevDayStartCalendar.add(DAY_OF_MONTH, -prevDayStartCalendar.get(DAY_OF_MONTH) + 1);
        prevDayStartCalendar.set(HOUR_OF_DAY, 0);
        prevDayStartCalendar.set(MINUTE, 0);
        prevDayStartCalendar.set(SECOND, 0);
//        prevDayStartCalendar.set(MONTH, 8);

//        val prevDayEndCalendar = new GregorianCalendar();
        val prevDayEndCalendar = (GregorianCalendar) prevDayStartCalendar.clone();
        /* prevDayEndCalendar.set(DAY_OF_MONTH, 1) - почему-то Добавляет +1 день, не Устанавливает 1-ый день месяца. */
//        prevDayEndCalendar.add(DAY_OF_MONTH, -prevDayStartCalendar.get(DAY_OF_MONTH) + 1);
        prevDayEndCalendar.set(HOUR_OF_DAY, 23);
        prevDayEndCalendar.set(MINUTE, 59);
        prevDayEndCalendar.set(SECOND, 59);
////        prevDayEndCalendar.set(MONTH, 8);
//
        val firstDayOfMonth = LocalDateTime.ofInstant(firstDayOfMonthCalendar.toInstant(), firstDayOfMonthCalendar.getTimeZone().toZoneId());
        val prevDayStart = LocalDateTime.ofInstant(prevDayStartCalendar.toInstant(), prevDayStartCalendar.getTimeZone().toZoneId());
        val prevDayEnd = LocalDateTime.ofInstant(prevDayEndCalendar.toInstant(), prevDayEndCalendar.getTimeZone().toZoneId());
    
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        System.out.println("firstDayOfMonth = " + formatter.format(firstDayOfMonth));
        System.out.println("prevDayStart =    " + formatter.format(prevDayStart));
        System.out.println("prevDayEnd =      " + formatter.format(prevDayEnd));
    
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime firstDayOfMonth = LocalDateTime.parse("2021-09-01 00:00:00", formatter);
//        LocalDateTime prevDayStart = LocalDateTime.parse("2021-09-22 00:00:00", formatter);
//        LocalDateTime prevDayEnd = LocalDateTime.parse("2021-09-22 23:59:59", formatter);
//
//        LocalDateTime regDate = LocalDateTime.parse("2021-09-22 17:00:00", formatter);
//
//        val inMonth = regDate.isAfter(firstDayOfMonth);
//        val inPrevDay = regDate.isAfter(prevDayStart) && regDate.isBefore(prevDayEnd);
//        System.out.println("inMonth = " + inMonth);
//        System.out.println("inPrevDay = " + inPrevDay);
    }
    
}
