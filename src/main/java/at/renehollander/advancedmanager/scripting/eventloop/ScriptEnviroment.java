package at.renehollander.advancedmanager.scripting.eventloop;

import at.renehollander.advancedmanager.scripting.eventloop.module.Module;
import at.renehollander.advancedmanager.scripting.eventloop.module.NamedModule;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.util.*;

public class ScriptEnviroment {

    private static final NashornScriptEngineFactory NASHORN_SCRIPT_ENGINE_FACTORY = new NashornScriptEngineFactory();

    private Set<Module> modules;
    private Map<String, NamedModule> moduleNameMapping;

    private EventLoop eventLoop;
    private ScriptEngine engine;
    private Bindings bindings;

    public ScriptEnviroment() {
        this.modules = new HashSet<>();
        this.moduleNameMapping = new HashMap<>();
        this.eventLoop = new EventLoop();
        this.engine = NASHORN_SCRIPT_ENGINE_FACTORY.getScriptEngine();
        this.bindings = new SimpleBindings();
    }

    public void execute(String script) throws ScriptException {
        getEngine().eval(script, getBindings());
       /*
        Map<String, Object> options = new HashMap<>();
        options.put("script", script);
        options.put("name", "src/main/javascript/test.js");

        Invocable invocable = (Invocable) getEngine();
        try {
            getEngine().setBindings(getBindings(), ScriptContext.ENGINE_SCOPE);
            NashornScriptEngine eng = (NashornScriptEngine) getEngine();
            invocable.invokeFunction("load", options);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        */
        getEventLoop().start();
    }

    protected ScriptEngine getEngine() {
        return engine;
    }

    public void addModule(Module module) {
        this.modules.add(module);
        if (module instanceof NamedModule) {
            this.moduleNameMapping.put(((NamedModule) module).getModuleName(), (NamedModule) module);
        }
    }

    /**
     * Getter for property 'modules'.
     *
     * @return Value for property 'modules'.
     */
    public Set<Module> getModules() {
        return Collections.unmodifiableSet(modules);
    }

    /**
     * Getter for property 'moduleNameMapping'.
     *
     * @return Value for property 'moduleNameMapping'.
     */
    public Map<String, NamedModule> getModuleNameMapping() {
        return Collections.unmodifiableMap(moduleNameMapping);
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
