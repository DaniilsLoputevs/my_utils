package reflection;

import entity.PrototypeEntity;
import other.Context;

import java.util.Map;

/**
 * Provide external Context API, like get something without using declare code.
 */
public class ContextReflectionWarp {
    private final Context context;
    private final Map<String, PrototypeEntity> innerMap;

    public ContextReflectionWarp(Context context) {
        this.context = context;
        this.innerMap = context.getInnerMap();
    }

    public PrototypeEntity getByEntityName(String name) {
        return innerMap.get(name);
    }
}
