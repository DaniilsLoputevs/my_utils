package satomi;

import java.time.LocalDateTime;
import java.util.Map;

public class DataParser2 {
    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "01"),
            Map.entry("фев", "02"),
            Map.entry("мар", "03"),
            Map.entry("апр", "04"),
            Map.entry("май", "05"),
            Map.entry("июн", "06"),
            Map.entry("июл", "07"),
            Map.entry("авг", "08"),
            Map.entry("сен", "09"),
            Map.entry("окт", "10"),
            Map.entry("ноя", "11"),
            Map.entry("дек", "12")
    );
    
    private static String correctMonth(String date) {
        for (String key : MONTHS.keySet()) {
            if (date.contains(key)) return date.replace(key, MONTHS.get(key));
        }
        System.err.println("ДАТУ не нужно корректировать");
        return date;
    }
    
    public static void main(String[] args) {
//        var inputDate = "23 апр 21, 17:16";
//        var yourDate = correctMonth(inputDate);
//        System.out.println("yourDate = " + yourDate);
//
//        var dtf = DateTimeFormatter.ofPattern("dd MM yy, HH:mm");
//
//        var localDateTime = LocalDateTime.parse(yourDate, dtf);
//
//        System.out.println("day = " + localDateTime.getDayOfMonth());
//        System.out.println("month = " + localDateTime.getMonth());
//        System.out.println("year = " + localDateTime.getYear());
//        System.out.println("min = " + localDateTime.getMinute());
//        System.out.println("sec = " + localDateTime.getSecond());
        
        
        var date = "сегодня, 17:16";
        LocalDateTime rsl = parseDate(date);
        
        System.out.println("day = " + rsl.getDayOfMonth());
        System.out.println("month = " + rsl.getMonth());
        System.out.println("year = " + rsl.getYear());
        System.out.println("min = " + rsl.getMinute());
        System.out.println("sec = " + rsl.getSecond());
    }
    
    /** @param date - сегодня, 17:16 */
    private static LocalDateTime parseDate(String date) {
        LocalDateTime rsl;
        
        if (date.startsWith("сегодня")) {
            rsl = parseToday(date);
        } else if (date.startsWith("вчера")) {
            rsl = parseToday(date).minusDays(1);
        } else {
            rsl = parse(date);
        }
        return rsl;
    }
    
    /** @param date - сегодня, 17:16 */
    private static LocalDateTime parse(String date) {
        return null;
    }
    
    /** @param date - сегодня, 17:16 */
    private static LocalDateTime parseToday(String date) {
        var tempTime = date.split(", ")[1]; // "17:16"
        var tempTimeTwo = tempTime.split(":"); // [0] = "17" && [1] = "16"
//        LocalTime time = new LocalTime.of(tempTimeTwo[0], tempTimeTwo[1]);
//        LocalTime time = new LocalTime.now();
//        System.out.println(time);
//        return LocalDateTime.of(LocalDate.now(), time);
        return null;
    }
}
