package at.renehollander.advancedmanager.scripting.api;

import org.apache.commons.lang3.Validate;

import java.lang.annotation.*;

public class APIInfo {

    private final String shortName;
    private final String name;
    private final String description;
    private final String documentation;

    public APIInfo(String shortName, String name, String description, String documentation) {
        Validate.notNull(shortName);
        Validate.notNull(name);

        this.shortName = shortName;
        this.name = name;
        this.description = description;
        this.documentation = documentation;
    }

    /**
     * Getter for property 'shortName'.
     *
     * @return Value for property 'shortName'.
     */
    public String getShortName() {
        return shortName;
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

    @Override
    public String toString() {
        return "ScriptRuntimeInfo{" +
                "shortName='" + shortName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", documentation='" + documentation + '\'' +
                '}';
    }

    public static APIInfo fromAnnotation(APIInfo.Annotation annotation) {
        return new APIInfo(annotation.shortName(), annotation.name(), annotation.description(), annotation.description());
    }

    @Documented
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Annotation {

        String shortName();

        String name();

        String description() default "";

        String documentation() default "";

    }

}

