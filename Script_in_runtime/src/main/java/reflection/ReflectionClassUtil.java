package reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionClassUtil {
    /**
     * @return private + public + all superClass fields(private + public)
     */
    public static List<Field> getAllFields(Object object) {
        return getAllFields(object.getClass());
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        var origFields = Arrays.asList(clazz.getDeclaredFields());
        var superFields = Arrays.asList(clazz.getSuperclass().getDeclaredFields());
        List<Field> rslFields = new ArrayList<>(origFields.size() + superFields.size());
        rslFields.addAll(origFields);
        rslFields.addAll(superFields);
        return rslFields;
    }




//    public static List<Field> getAllFieldsMap(Object object) {
//        var origFields = Arrays.asList(object.getClass().getDeclaredFields());
//        var superFields = Arrays.asList(object.getClass().getSuperclass().getDeclaredFields());
//        List<Field> rslFields = new ArrayList<>(origFields.size() + superFields.size());
//        rslFields.addAll(origFields);
//        rslFields.addAll(superFields);
//        return rslFields;
//    }
}
