package at.renehollander.advancedmanager.scripting.eventloop;

import at.renehollander.advancedmanager.scripting.eventloop.module.Module;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.util.HashMap;
import java.util.Map;

public class ScriptEnviroment {

    private static final NashornScriptEngineFactory NASHORN_SCRIPT_ENGINE_FACTORY = new NashornScriptEngineFactory();

    private Map<String, Module> modules;
    private EventLoop eventLoop;
    private ScriptEngine engine;
    private Bindings bindings;

    public ScriptEnviroment() {
        this.modules = new HashMap<>();
        this.eventLoop = new EventLoop();
        this.engine = NASHORN_SCRIPT_ENGINE_FACTORY.getScriptEngine();
        this.bindings = new SimpleBindings();
    }

    public void execute(String script) throws ScriptException {
        getEngine().eval(script, getBindings());
        getEventLoop().start();
    }

    protected ScriptEngine getEngine() {
        return engine;
    }

    /**
     * Getter for property 'modules'.
     *
     * @return Value for property 'modules'.
     */
    public Map<String, Module> getModules() {
        return modules;
    }

    /**
     * Getter for property 'eventLoop'.
     *
     * @return Value for property 'eventLoop'.
     */
    public EventLoop getEventLoop() {
        return eventLoop;
    }

    /**
     * Getter for property 'bindings'.
     *
     * @return Value for property 'bindings'.
     */
    public Bindings getBindings() {
        return bindings;
    }
}
