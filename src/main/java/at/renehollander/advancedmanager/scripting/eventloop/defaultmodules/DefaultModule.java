package at.renehollander.advancedmanager.scripting.eventloop.defaultmodules;

import at.renehollander.advancedmanager.scripting.eventloop.EventLoop;
import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.Bindings;

public abstract class DefaultModule {

    private NashornScriptEngine engine;
    private EventLoop eventLoop;
    private Bindings bindings;

    public DefaultModule(NashornScriptEngine engine, EventLoop eventLoop, Bindings bindings) {
        this.engine = engine;
        this.eventLoop = eventLoop;
        this.bindings = bindings;
    }

    /**
     * Getter for property 'engine'.
     *
     * @return Value for property 'engine'.
     */
    public NashornScriptEngine getEngine() {
        return engine;
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

    @Override
    public String toString() {
        return "DefaultModule{" +
                "engine=" + engine +
                ", eventLoop=" + eventLoop +
                ", bindings=" + bindings +
                '}';
    }
}
