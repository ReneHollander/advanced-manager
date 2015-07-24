package at.renehollander.advancedmanager.scripting.eventloop.module;

import at.renehollander.advancedmanager.scripting.eventloop.ScriptEnviroment;

public abstract class Module {

    private ScriptEnviroment enviroment;

    public Module(ScriptEnviroment enviroment) {
        this.enviroment = enviroment;
    }

    protected abstract void load();

    /**
     * Getter for property 'enviroment'.
     *
     * @return Value for property 'enviroment'.
     */
    public ScriptEnviroment getEnviroment() {
        return enviroment;
    }

    @Override
    public String toString() {
        return "Module{" +
                "enviroment=" + enviroment +
                '}';
    }
}
