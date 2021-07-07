package sounding;

import java.util.List;

@Deprecated
public class StringTools {
    public static String collapse(List<String> list) {
        return String.join("", list);
//
//
//        var result = new StringBuilder();
//        for (var string : list) {
//            result.append(string);
//        }
//        return result.toString();
    }
}
