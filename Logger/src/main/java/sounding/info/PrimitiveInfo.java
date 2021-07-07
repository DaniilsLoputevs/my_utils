package sounding.info;

import lombok.val;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class PrimitiveInfo implements FieldInfo{
    private String modifiers = "";
    private final Object value;
    
    
    PrimitiveInfo(Field field, Object obj) {
        if (field != null) this.modifiers = FieldInfo.modifiersToString(field);
        this.value = obj;
    }
    
    @Override
    public List<String> collapse() {
        val toString = modifiers + " " + ((value instanceof String) ? "\"" + value + '\"' : value.toString());
        return Collections.singletonList(toString);
    }
}
