package other;

import entity.PrototypeEntity;

import java.util.Map;

public interface Context {
    void add(PrototypeEntity object);

    /**
     * @param name          -
     * @param expectedClass -
     * @param <C>           -
     * @return Entity OR null, if it didn't find.
     */
    <C> C getEntityByName(String name, Class<C> expectedClass);

    Map<String, PrototypeEntity> getInnerMap();

    int size();
}
