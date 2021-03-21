package script;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ScriptListener {
    private static final Logger LOG = LoggerFactory.getLogger(ScriptListener.class);
    ScriptManager scriptManager;
    Thread listenThread;

    public ScriptListener(ScriptManager scriptManager) {
        this.scriptManager = scriptManager;

        this.listenThread = new Thread(() -> {
            var scanner = new Scanner(System.in);
            boolean run = true;
            while (run) {
                var script = scanner.nextLine();
                scriptManager.apply(script);

                if (listenThread.isInterrupted()) {
                    run = false;
                }
            }
            LOG.info("ScriptListener: finished");
        });
        LOG.info("ScriptListener: created");
    }

    public ScriptListener run() {
        this.listenThread.start();
        LOG.info("ScriptListener: started");
        return this;
    }

    /**
     * shutdown listenThread.
     */
    public void destroy() {
        LOG.info("ScriptListener: destroy started");
        listenThread.interrupt();
        LOG.info("ScriptListener: destroy finished");
    }

}
