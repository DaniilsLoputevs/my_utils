package sounding.info;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CollectionInfo implements FieldInfo{
    CollectionInfo(Field field, Object obj) {
    
    }
    @Override
    public List<String> collapse() {
        return new ArrayList<>();
    }
}
