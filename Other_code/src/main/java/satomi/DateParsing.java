package satomi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DateParsing {
    private static final Map<String, String > MONTHS = Map.ofEntries(
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
    
    private static LocalDateTime parse(String date) {
        return null; // your code here
    }
    
    public static void main(String[] args) {
        var inputDate = "23 апр 21, 17:16";
        var yourDate = correctMonth(inputDate);
        var date = parse(yourDate);


//        //parse()
//        var dtf = DateTimeFormatter.ofPattern("dd MM yy, HH:mm", Locale.ENGLISH);
//        var dtf = DateTimeFormatter.ofPattern("dd MMM yy, HH:mm", new Locale("RU"));
        var dtf = DateTimeFormatter.ofPattern("dd MM yy, HH:mm");
//        var dateInString = "23 апр 21, 17:16";
        var dateInString = "23 03 21, 17:16";
        var localDateTime = LocalDateTime.parse(dateInString, dtf);
        
        
        System.out.println("day = " + localDateTime.getDayOfMonth());
        System.out.println("month = " + localDateTime.getMonth());
        System.out.println("year = " + localDateTime.getYear());
        System.out.println("min = " + localDateTime.getMinute());
        System.out.println("sec = " + localDateTime.getSecond());
//        System.out.println(localDateTime);
        
        //format()
     /*   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yy, HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.of(21, Month.APRIL, 23, 17, 16);
        String dateInString = "23 апр 21, 17:16";
        String localDateTime = localDateTime1.format(dtf);
        System.out.println(localDateTime);*/
    }
}
