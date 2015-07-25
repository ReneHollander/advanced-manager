package at.renehollander.advancedmanager.scripting.eventloop.module;

public abstract class NamedModule extends Module {

    protected String moduleName;

    private Object exports;

    public NamedModule(String moduleName) {
        this.moduleName = moduleName;
        this.exports = null;
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
        return "NamedModule{" +
                "moduleName='" + moduleName + '\'' +
                ", exports=" + exports +
                '}';
    }
}
