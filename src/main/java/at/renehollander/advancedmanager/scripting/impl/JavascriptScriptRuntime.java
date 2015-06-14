package at.renehollander.advancedmanager.scripting.impl;

import at.renehollander.advancedmanager.scripting.api.API;
import at.renehollander.advancedmanager.scripting.exception.ScriptError;
import at.renehollander.advancedmanager.scripting.runtime.ScriptRuntime;
import at.renehollander.advancedmanager.scripting.runtime.ScriptRuntimeInfo;
import at.renehollander.advancedmanager.util.Callback;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ScriptRuntimeInfo.Annotation(shortName = "js", name = "JavaScript")
public class JavascriptScriptRuntime extends ScriptRuntime {

    private final ExecutorService pool;
    private final ScriptEngine se;
    private final Invocable invocable;

    public JavascriptScriptRuntime() {
        pool = Executors.newCachedThreadPool();
        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        se = factory.getScriptEngine();
        invocable = (Invocable) se;
    }

    @Override
    public void bindAPIs() {
        for (API api : getApis()) {
            se.put(api.getApiInfo().getShortName(), api);
        }
    }

    @Override
    public void execute(String script, Callback<Boolean> cb) {
        pool.submit(() -> {
            try {
                se.eval(script);
                cb.call(null, true);
            } catch (Exception ex) {
                cb.call(new ScriptError(ex), false);
            }
        });
    }

    public void stopExecution() {
        try {
            invocable.invokeFunction("exit");
        } catch (Exception ex) {
        }
        pool.shutdownNow();
    }

}
