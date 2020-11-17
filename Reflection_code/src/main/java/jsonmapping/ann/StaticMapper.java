package jsonmapping.ann;

import jsonmapping.map.JsonCollector;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class StaticMapper {

    // map by fields
    public static String map(Object model, MapCase mapCase) {
        var clazz = model.getClass();
        var clazzFields = clazz.getDeclaredFields();

        List<String> rslJsonStrings = new ArrayList<>();

        for (Field each : clazzFields) {
            if (each.isAnnotationPresent(MyJsonMap.class)) {
                var tempAnn = each.getAnnotation(MyJsonMap.class);
                var cases = Arrays.asList(tempAnn.mapCases());
                if (cases.contains(mapCase)) {
                    each.setAccessible(true);

                    String fieldName = "";             // init stub
                    Object fieldValue = new Object();  // init stub
                    try {
                        fieldName = each.getName();
                        fieldValue = each.get(model);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    String rslFieldValue = isCustomDataClass(each.getType())
                            ? map(fieldValue, mapCase) //  << =================  RECURSION !!!
                            : fieldValue.toString();

                    rslJsonStrings.add(JsonCollector.collect(fieldName, rslFieldValue));
                }
            }
        }
        return JsonCollector.wrapObject(rslJsonStrings);
    }


    /**
     * using Class clazz.setSimpleName()
     * @param val -
     * @return -
     */
    private static boolean isCustomDataClass(Class<?> val) {
        String simpleName = val.getSimpleName();
        Set<String> setOfNativeClasses = Set.of(
                "String", "int", "boolean"
        );
        return !setOfNativeClasses.contains(simpleName);
    }
}
