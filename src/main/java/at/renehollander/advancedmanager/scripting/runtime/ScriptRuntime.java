package at.renehollander.advancedmanager.scripting.runtime;

import at.renehollander.advancedmanager.scripting.annotation.Inject;
import at.renehollander.advancedmanager.scripting.api.API;
import at.renehollander.advancedmanager.scripting.exception.ScriptError;
import at.renehollander.advancedmanager.util.Callback;

import java.util.List;
import java.util.Set;

public abstract class ScriptRuntime {

    @Inject
    private ScriptRuntimeInfo runtimeInfo;

    @Inject
    private Set<String> classWhitelist;

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
     * Getter for property 'classWhitelist'.
     *
     * @return Value for property 'classWhitelist'.
     */
    public Set<String> getClassWhitelist() {
        return classWhitelist;
    }

    /**
     * Getter for property 'apis'.
     *
     * @return Value for property 'apis'.
     */
    public List<API> getApis() {
        return apis;
    }

    public boolean allowedClass(String name) {
        System.out.println(classWhitelist);
        return this.getClassWhitelist() == null || this.getClassWhitelist().contains(name);

    }

    public abstract void initialize();

    public abstract void execute(String script, Callback<Boolean> cb) throws ScriptError;

    public abstract void stopExecution();

}
