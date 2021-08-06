import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for:
 * Date, TimeStamp, Calendar -> String
 * String -> Date, TimeStamp, Calendar
 */
public class DateStrFormat {
    public static final String PATTERN_TABLE_TIMESTAMP = "yyyy-MM-dd";
    private static final SimpleDateFormat SDF = new SimpleDateFormat(PATTERN_TABLE_TIMESTAMP);
    
    
    public static String toStr(Date date) {
        return (date != null) ? SDF.format(date) : "date is null";
    }
    
    public static Date toDate(String date) {
        Date rsl = null;
        try {
            rsl = SDF.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rsl;
    }
    
    /* Work with Timestamp */
    
    public static String toStr(Timestamp timestamp) {
        String rsl = timestamp.toString();
        return rsl.substring(0, rsl.lastIndexOf('.'));
    }
    
    public static Timestamp toTimeStamp(String timestamp) {
        Timestamp rsl = null;
        try {
            Date date = new SimpleDateFormat(PATTERN_TABLE_TIMESTAMP).parse(timestamp);
            rsl = new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rsl;
    }
    
    /* For future projects, explain how it work. */

//    public static void main(String[] args) {
//        Date one = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_TABLE_TIMESTAMP);
//        String two = dateFormat.format(one);
//        try {
//            Date three = dateFormat.parse(two);
//
//            System.out.println("Original: " + one);
//            System.out.println("toFront: " + two);
//            System.out.println("toBack: " + three);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }


}
