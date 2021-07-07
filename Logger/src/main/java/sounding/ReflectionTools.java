package sounding;

import lombok.var;

import java.lang.reflect.Field;

public class ReflectionTools {
    public static Field[] setReflectionAccess(Field[] fields) {
        for (var f : fields) {
            f.setAccessible(true);
        }
        return fields;
    }
}
