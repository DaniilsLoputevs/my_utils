package sounding.info;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static sounding.ReflectionTools.setReflectionAccess;

@Getter
@Setter
public class ObjectInfo implements FieldInfo{
    private String modifiers = "";
    private List<FieldInfo> fields;
    
    /**
     * @param field - (nullable) - show that {@param obj} is field of other Object.
     *             If null it's the highest Object.
     * @param obj -
     */
    public ObjectInfo(Field field, Object obj) {
        if (field != null) this.modifiers = FieldInfo.modifiersToString(field);
        this.fields = Arrays.stream(setReflectionAccess(obj.getClass().getDeclaredFields()))
                .map(it -> FieldInfo.of(it, obj))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<String> collapse() {
        return new ArrayList<>();
    }
}
