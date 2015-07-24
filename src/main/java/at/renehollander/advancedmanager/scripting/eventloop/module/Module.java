package at.renehollander.advancedmanager.scripting.eventloop.module;

import at.renehollander.advancedmanager.scripting.eventloop.EventLoop;
import jdk.nashorn.api.scripting.NashornScriptEngine;

public abstract class Module {

    protected String moduleName;
    protected NashornScriptEngine engine;
    protected EventLoop eventLoop;

    private Object exports;
    protected boolean loaded;

    public Module(String moduleName, NashornScriptEngine engine, EventLoop eventLoop) {
        this.moduleName = moduleName;
        this.engine = engine;
        this.eventLoop = eventLoop;

        this.exports = null;

        load();
    }

    protected abstract void load();

    /**
     * Getter for property 'moduleName'.
     *
     * @return Value for property 'moduleName'.
     */
    public String getModuleName() {
        return moduleName;
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
     * Getter for property 'exports'.
     *
     * @return Value for property 'exports'.
     */
    public Object getExports() {
        return exports;
    }

    /**
     * Getter for property 'loaded'.
     *
     * @return Value for property 'loaded'.
     */
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleName='" + moduleName + '\'' +
                ", engine=" + engine +
                ", eventLoop=" + eventLoop +
                ", exports=" + exports +
                ", loaded=" + loaded +
                '}';
    }
}
