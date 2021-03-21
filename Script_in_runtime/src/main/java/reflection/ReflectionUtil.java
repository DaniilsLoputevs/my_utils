package reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ReflectionUtil.class);

    public static <C> C prettyCast(Object object, Class<C> expectedClass) {
        C rsl = null;
        try {
            rsl = expectedClass.cast(object);
        } catch (ClassCastException e) {
            LOG.error("Wrong class cast in reflection: ");
            LOG.error("Entity class:   {}", object.getClass());
            LOG.error("Expected class: {}", expectedClass.getSimpleName());
            LOG.error("Entity details: {}", object);
        }
        return rsl;
    }
}
