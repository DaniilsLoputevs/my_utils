package satomi;

import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

import static java.util.Locale.ENGLISH;

public class Show {
    public static void main(String[] args) {
        
        var t = new GregorianCalendar();
        var sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm", ENGLISH);
        
        var expected = new StringBuilder();
//        expected.append("   <hired>" + sdf.format(t) + "</hired>");
        
        // ...
    }
}
