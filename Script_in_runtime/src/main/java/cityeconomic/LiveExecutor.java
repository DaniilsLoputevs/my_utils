package cityeconomic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LiveExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(LiveExecutor.class);
    private List<City> cities;

    public LiveExecutor(List<City> cities) {
        this.cities = cities;
    }

    public void run() {
        int logInfoTicCounter = 1;
        while (true) {
            LOG.info("TIC: {}", logInfoTicCounter);
            // all live
            beautySleep(1000); // 1 sec
        }
    }

    private static void beautySleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
