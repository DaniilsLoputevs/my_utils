package sounding.info;

import lombok.SneakyThrows;
import lombok.val;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.StringJoiner;

import static sounding.Modifiers.*;

public interface FieldInfo extends Info<Field> {
    
    @SneakyThrows
    static FieldInfo of(Field field, Object obj) {
        val fieldValue = field.get(obj);
        return (isPrimitive(fieldValue)) ? new PrimitiveInfo(field, obj) :
                (isCollection(field)) ? new CollectionInfo(field, obj) :
                        new ObjectInfo(field, fieldValue);
    }
    
    static String modifiersToString(Field f) {
        return new StringJoiner(" ")
                .add(findOwnModifier(f))
                .add(findAccessModifier(f))
                .add(findStaticModifier(f))
                .add(findFinalOrVolatileModifier(f))
                .toString();
    }
    
    
    static boolean isPrimitive(Object fieldValue) {
        if (fieldValue instanceof String) return true;
        if (fieldValue instanceof Boolean) return true;
        if (fieldValue instanceof Integer) return true;
        if (fieldValue instanceof Short) return true;
        if (fieldValue instanceof Double) return true;
        if (fieldValue instanceof Long) return true;
        if (fieldValue instanceof Float) return true;
        if (fieldValue instanceof Character) return true;
        return fieldValue instanceof Byte;
    }
    
    
    static boolean isCollection(Field fieldClass) {
        val clazz = fieldClass.getType();
        if (clazz.isArray()) return true;
        if (Iterable.class.isAssignableFrom(clazz)) return true;
        return Map.class.isAssignableFrom(clazz);
    }
    
    
}
