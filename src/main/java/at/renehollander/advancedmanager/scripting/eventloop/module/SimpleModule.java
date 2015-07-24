package at.renehollander.advancedmanager.scripting.eventloop.module;

import at.renehollander.advancedmanager.scripting.eventloop.ScriptEnviroment;

public abstract class SimpleModule extends Module {

    protected String moduleName;

    private Object exports;

    public SimpleModule(ScriptEnviroment enviroment, String moduleName) {
        super(enviroment);
        this.moduleName = moduleName;
        this.exports = null;
        load();
    }

    /**
     * Getter for property 'moduleName'.
     *
     * @return Value for property 'moduleName'.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Getter for property 'exports'.
     *
     * @return Value for property 'exports'.
     */
    public Object getExports() {
        return exports;
    }

    @Override
    public String toString() {
        return "SimpleModule{" +
                "moduleName='" + moduleName + '\'' +
                ", exports=" + exports +
                '}';
    }
}
