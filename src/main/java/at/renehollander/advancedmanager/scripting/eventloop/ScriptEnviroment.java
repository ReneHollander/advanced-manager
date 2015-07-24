package at.renehollander.advancedmanager.scripting.eventloop;

import at.renehollander.advancedmanager.scripting.eventloop.module.Module;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.SimpleBindings;
import java.util.List;

public class ScriptEnviroment {

    private static final NashornScriptEngineFactory NASHORN_SCRIPT_ENGINE_FACTORY = new NashornScriptEngineFactory();

    private EventLoop eventLoop;
    private ScriptEngine engine;
    private Bindings bindings;

    private List<Module> modules;

    public ScriptEnviroment() {
        this.eventLoop = new EventLoop();
        this.engine = NASHORN_SCRIPT_ENGINE_FACTORY.getScriptEngine();
        this.bindings = new SimpleBindings();
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
