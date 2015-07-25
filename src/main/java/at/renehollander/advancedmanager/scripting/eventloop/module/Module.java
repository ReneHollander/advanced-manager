package at.renehollander.advancedmanager.scripting.eventloop.module;

import at.renehollander.advancedmanager.scripting.eventloop.ScriptEnviroment;

public abstract class Module {

    private ScriptEnviroment enviroment;

    public Module() {
    }

    public void load() {
        if (enviroment == null) {
            throw new RuntimeException("enviroment not injected!");
        }
    }

    public void unload() {
    }

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
