package reflection;

import entity.PrototypeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static reflection.ReflectionClassUtil.getAllFields;

public class ReflectionObject {
    private static final Logger LOG = LoggerFactory.getLogger(ReflectionObject.class);
    private final PrototypeEntity original;
    private Map<String, Field> fields = new HashMap<>();

    public ReflectionObject(PrototypeEntity original) {
        if (original == null) {
            LOG.error("object is: null");
        }
        this.original = original;
        for (var each : getAllFields(original)) {
            fields.put(each.getName(), each);
        }
    }

    public Field getFieldByName(String name) {
        Field rsl = fields.get(name);
        if (rsl == null) {
            LOG.error("field doesn't find.");
            LOG.error("Entity details:");
            LOG.error("id: {}", original.getContextId());
            LOG.error("name: {}", original.getName());
        }
        return rsl;
    }

    public Object getFieldValueByName(String name) {
        Object rsl = null;
        var temp = fields.get(name);
        if (temp != null) {
            try {
                rsl = temp.get(original);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return rsl;
    }

    @Override
    public String toString() {
        return original.toString();
    }
}
