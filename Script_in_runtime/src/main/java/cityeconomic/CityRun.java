package cityeconomic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CityRun {
    private static final Logger LOG = LoggerFactory.getLogger(City.class);

    public static void main(String[] args) {
//        var lindle = new City(
//                "Lindle",
//                GeoPosition.of(100, 100),
//                contactsAndRoutes, 1000*40);
//
//        var kraiser = new City(
//                "Kraiser",
//                GeoPosition.of(200, 200),
//                contactsAndRoutes, 1000*30
//                );
//        var cities = List.of(lindle, kraiser);

//        System.out.println(lindle);
//        System.out.println(kraiser);
//        System.out.println(GeoPosition.distance(lindle, kraiser));

        LOG.info("### MAIN: START ###");
        LOG.info("### liveExecutor: INIT start ###");
//        var liveExecutor = new LiveExecutor(cities);
        LOG.info("### liveExecutor: INIT complete ###");
        LOG.info("### liveExecutor: RUN ###");
//        liveExecutor.run();
        LOG.info("### MAIN: FINISH ###");
    }


//    private void beautyWait(Object obj) {
//        try {
//            obj.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
