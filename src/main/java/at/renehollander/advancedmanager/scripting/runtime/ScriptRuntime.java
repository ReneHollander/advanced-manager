package at.renehollander.advancedmanager.scripting.runtime;

import at.renehollander.advancedmanager.scripting.exception.ScriptError;
import at.renehollander.advancedmanager.util.Callback;

public abstract class ScriptRuntime {

    private final String name;
    private final String longName;
    private final String description;
    private final String documentation;

    public ScriptRuntime(String name, String longName, String description, String documentation) {
        this.name = name;
        this.longName = longName;
        this.description = description;
        this.documentation = documentation;
    }

    public ScriptRuntime(String name, String longName) {
        this(name, longName, "", "");
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for property 'longName'.
     *
     * @return Value for property 'longName'.
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Getter for property 'description'.
     *
     * @return Value for property 'description'.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for property 'documentation'.
     *
     * @return Value for property 'documentation'.
     */
    public String getDocumentation() {
        return documentation;
    }

    public abstract void execute(String script, Callback<Boolean> cb) throws ScriptError;

}
