package dto.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Transfer Object From to Target object,
 * use the same fields name or use this annotation.
 *
 * @see TransferDTO
 */
public class Transfer {
    public <R> R toDto(Object from, Class<R> target) {
        R targetInst = createTargetInst(target);
        var ffc = new FieldsController(from.getClass().getDeclaredFields()); // fromFieldsController
        var tfc = new FieldsController(target.getDeclaredFields()); // targetFieldsController
        ffc.makeAllAccess(true);
        tfc.makeAllAccess(true);

        Map<String, Field> targetFieldsMap = getTargetFields(tfc.getInnerFields());
        Field[] fromFieldsList = ffc.getInnerFields();

        for (Field each : fromFieldsList) {
            Field tempField = targetFieldsMap.get(each.getName()); // get target field or null
            if (tempField != null) {

                try {
                    var fromValue = each.get(from);
                    tempField.set(targetInst, fromValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    var msg = "CATCH EXCEPTION: IllegalArgumentException:\r\n"
                            + "It could be wrong Field types, details:\r\n"
                            + "=======================================================\r\n"
                            + "from Class:     " + from.getClass().getName() + "\r\n"
                            + "target Class:   " + target.getName() + "\r\n"
                            + "                     Fields Type    --   Field Name\r\n"
                            + "from Field:      " + each.getType() + " -- " + each.getName() + "\r\n"
                            + "target Field:    " + tempField.getType() + " -- " + tempField.getName() + "\r\n"
                            + "=======================================================\r\n"
                            + "* This target field will have default value of Field Type.\r\n"
                            + "* Example: String == null, int == 0, boolean == false and etc.\r\n"
                            + "=======================================================\r\n"
                            + "ORIGINAL STACKTRACE:";
                    System.err.println(msg);
                    e.printStackTrace();
                }
            }
        }
        return targetInst;
    }

    private Map<String, Field> getTargetFields(Field[] fields) {
        Map<String, Field> map = new HashMap<>();
        for (Field each : fields) {
            if (each.isAnnotationPresent(TransferDTO.class)) {
                var altNames = each.getAnnotation(TransferDTO.class).altNames();
                for (String name : altNames) {
                    map.put(name, each);
                }
            } else {
                map.put(each.getName(), each);
            }
        }
        return map;
    }

    private <R> R createTargetInst(Class<R> target) {
        R rsl = null;
        try {
            rsl = target.getConstructor().newInstance();
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InstantiationException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * clazz.getDeclaredFields() - is coping fields,
     * if you and switch access, it won't work for new copied fields.
     * <p>
     * It the reason to use: getInnerFields();
     */
    private static class FieldsController {
        private final Field[] fields;

        public FieldsController(Field[] fields) {
            this.fields = fields;
        }

        public void makeAllAccess(boolean access) {
            for (Field field : fields) {
                field.setAccessible(access);
            }
        }

        public Field[] getInnerFields() {
            return this.fields;
        }
    }


    private enum ClassPart {
        FIELD, METHOD
        // and etc...
    }


    /**
     * @param tClass    - accept class
     * @param ann       - annotation that it search
     * @param classPart - Field class, Method class,
     *                  any class that is in java.lang.reflect
     * @return -
     */
    private <T, R> List<R> takeOnlyThisAnnotated(Class<T> tClass, Annotation ann, R classPart) {
        // ...
        return null;
    }


}