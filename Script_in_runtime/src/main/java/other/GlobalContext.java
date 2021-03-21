package other;

import entity.PrototypeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reflection.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Context of all Entity(PrototypeEntity)
 */
public class GlobalContext implements Context {
    private static final Logger LOG = LoggerFactory.getLogger(ReflectionUtil.class);

    //    private final Map<Integer, PrototypeEntity> context = new HashMap<>();
    /**
     * key - entity name.
     * val - entity.
     */
    private final Map<String, PrototypeEntity> context = new HashMap<>();
    private int lastId = 0;

    /**
     * @param object -
     */
    @Override
    public void add(PrototypeEntity object) {
        if (!context.containsKey(object.getName())) {
            object.setContextId(lastId);
            context.put(object.getName(), object);
            lastId++;
        } else {
            var existsEntity = context.get(object.getName());
            LOG.warn("Entity can't to be added because has unique name: {}", object.getName());
            LOG.warn("New entity detains: ");
            LOG.warn("id: {}", object.getContextId());
            LOG.warn("name: {}", object.getName());
            LOG.warn("Exists entity detains: ");
            LOG.warn("id: {}", existsEntity.getContextId());
            LOG.warn("name: {}", existsEntity.getName());
        }
    }

//    /**
//     * @param id            -
//     * @param expectedClass -
//     * @param <C>           -
//     * @return Entity OR null, if it didn't find.
//     */
//    public <C> C get(int id, Class<C> expectedClass) {
//        if (context.containsKey(id)) {
//            return ReflectionUtil.prettyCast(context.get(id), expectedClass);
//        } else {
//            LOG.warn("Entity didn't fond!");
//            LOG.warn("id: {}", id);
//            return null;
//        }
//    }

    /**
     * @param name          -
     * @param expectedClass -
     * @param <C>           -
     * @return Entity OR null, if it didn't find.
     */
    @Override
    public <C> C getEntityByName(String name, Class<C> expectedClass) {
        var rsl = context.get(name);
        if (rsl != null) {
            return ReflectionUtil.prettyCast(rsl, expectedClass);
        } else {
            LOG.warn("Entity didn't fond!");
            LOG.warn("name: {}", name);
            return null;
        }
    }

    @Override
    public Map<String, PrototypeEntity> getInnerMap() {
        return context;
    }

    @Override
    public int size() {
        return context.size();
    }
}
