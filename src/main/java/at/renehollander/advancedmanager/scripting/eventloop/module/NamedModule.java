package at.renehollander.advancedmanager.scripting.eventloop.module;

public abstract class NamedModule extends Module {

    private String moduleName;
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

    /**
     * Setter for property 'exports'.
     *
     * @param exports Value to set for property 'exports'.
     */
    public void setExports(Object exports) {
        this.exports = exports;
    }

    @Override
    public String toString() {
        return "NamedModule{" +
                "moduleName='" + moduleName + '\'' +
                ", exports=" + exports +
                '}';
    }
}
