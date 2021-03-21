package script;

import other.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ScriptManager {
    private final Map<String, String> readyScripts = new HashMap<>();
    private final List<String> appliedScripts = new ArrayList<>();
    private final Context context;
    private final ScriptExecutor executor = new ScriptExecutor();


    public ScriptManager(Context context) {
        this.context = context;
        readyScripts.put("stub", "log LandSide.machines");
    }

    public void apply(String script) {
        appliedScripts.add(script);
        executor.tryExeScript(context, script);
    }

    public void outAllScripts(Consumer<String> out) {
        appliedScripts.forEach(out);
    }

}
