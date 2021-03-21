package services;

import entity.City;
import entity.Machine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import other.Context;
import other.GlobalContext;
import script.ScriptListener;
import script.ScriptManager;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static Context initContext() {
        var context = new GlobalContext();
        List<Machine> landSideMachines = new ArrayList<>();
        landSideMachines.add(new Machine("ven1", 100, 1));
        landSideMachines.add(new Machine("ven2", 100, 1));
        context.add(new City("LandSide", landSideMachines));

        List<Machine> astroFieldMachines = new ArrayList<>();
        landSideMachines.add(new Machine("kamaz1", 250, 3));
        landSideMachines.add(new Machine("kamaz2", 250, 3));
        context.add(new City("AstroField", astroFieldMachines));
        return context;
    }

    public static void main(String[] args) {
       final Context context = initContext();
       final ScriptManager scriptManager = new ScriptManager(context);
       final ScriptListener scriptListener = new ScriptListener(scriptManager).run();

        try {
            int lifeTime = 1000 * 3;
            LOG.info("Thread Main: live time: {}", (lifeTime / 1000) + " sec");
            Thread.sleep(lifeTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scriptListener.destroy();
        LOG.info("Log all applied scripts");
        scriptManager.outAllScripts(System.out::println);
        LOG.info("Log all applied scripts - finish");
















//        var list = new ArrayList<Object>();
////        list.add("aaa");
////        list.add("bbb");
////        list.add("ccc");
//        list.add(new Machine("mach", 100, 4));
//
//        for (var each : list) {
//            LOG.info("superClazz: {}", each.getClass().getSuperclass().getSimpleName());
//            var superFields = each.getClass().getSuperclass().getDeclaredFields();
//            var fields = each.getClass().getDeclaredFields();
//            for (var field : fields) {
//                LOG.info(field.getName());
//            }
//            for (var field : superFields) {
//                LOG.info(field.getName());
//            }
//
//        }
    }

}
