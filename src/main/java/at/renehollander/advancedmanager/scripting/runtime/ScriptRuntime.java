package at.renehollander.advancedmanager.scripting.runtime;

import at.renehollander.advancedmanager.scripting.annotation.Inject;
import at.renehollander.advancedmanager.scripting.api.API;
import at.renehollander.advancedmanager.scripting.exception.ScriptError;
import at.renehollander.advancedmanager.util.Callback;

import java.util.List;

public abstract class ScriptRuntime {

    @Inject
    private ScriptRuntimeInfo runtimeInfo;

    @Inject
    private List<API> apis;

    /**
     * Getter for property 'runtimeInfo'.
     *
     * @return Value for property 'runtimeInfo'.
     */
    public ScriptRuntimeInfo getRuntimeInfo() {
        return runtimeInfo;
    }

    /**
     * Getter for property 'apis'.
     *
     * @return Value for property 'apis'.
     */
    public List<API> getApis() {
        return apis;
    }

    public abstract void bindAPIs();

    public abstract void execute(String script, Callback<Boolean> cb) throws ScriptError;

    public abstract void stopExecution();

}
