package script;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import other.Context;
import script.process.Log;
import script.process.ScriptProcessor;

import java.util.Map;

public class ScriptExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(ScriptExecutor.class);
    private final Map<String, ScriptProcessor> processors;

    public ScriptExecutor() {
        this.processors = Map.of(
                "log", new Log()
        );
    }

    public void tryExeScript(Context context, String script) {
        var command = script.split(" ")[0];
        var processor = processors.get(command);
        if (processor == null) {
            LOG.error("Unknown command: {}", command);
        } else {
            var rslScript = script.substring(script.indexOf(" ") + 1);
            processor.process(context, rslScript);
        }
    }
}
