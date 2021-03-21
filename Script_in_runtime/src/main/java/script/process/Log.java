package script.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import other.Context;
import reflection.ContextReflectionWarp;
import reflection.ReflectionClassUtil;
import reflection.ReflectionObject;
import services.Main;

public class Log implements ScriptProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    ContextReflectionWarp refContext;

    /**
     * log LandSide.machines
     *
     * @param context -
     * @param script -
     */
    @Override
    public void process(Context context, String script) {
        this.refContext = new ContextReflectionWarp(context);
        var entityArr = script.split("\\.");

        // logger
        LOG.info("entityArr: ");
        LOG.info("length: {}", entityArr.length);
        for (var each : entityArr) {
            System.out.println("|" + each + "|");
        }

        var tempEntity = refContext.getByEntityName(entityArr[0]);
        LOG.info("tempEntity: {}", tempEntity);
        var obj = new ReflectionObject(tempEntity);
        if (entityArr.length > 2) {
//            var coreEntity = refContext.getByEntityName(entityArr[0]);
//            Field tempField = obj.getFieldByName(entityArr[1]);
//            var temp = obj.getFieldValueByName(tempField.getName());
            Object temp = obj.getFieldValueByName(entityArr[1]);
//            var rsl = ReflectionUtil.prettyCast(temp, tempField.getDeclaringClass());
            if (temp != null) {
                System.out.println("LOG - marker");
                System.out.println(temp);
            }
            System.out.println();
        } else {
//            var obj = new ReflectionObject(refContext.getByEntityName(entityArr[0]));
//            log(obj);
            System.out.println("LOG - marker");
            System.out.println(obj.toString());
        }
    }

    private void log(ReflectionObject obj) {

    }


    public static void main(String[] args) {
        var test = "aaa";
        var tsl = ReflectionClassUtil.getAllFields(test.getClass());
        tsl.forEach(System.out::println);
    }
}
