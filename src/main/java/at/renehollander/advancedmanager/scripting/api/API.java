package at.renehollander.advancedmanager.scripting.api;

import at.renehollander.advancedmanager.scripting.annotation.Inject;

public abstract class API {

    @Inject
    private APIInfo apiInfo;

    /**
     * Getter for property 'apiInfo'.
     *
     * @return Value for property 'apiInfo'.
     */
    public APIInfo getApiInfo() {
        return apiInfo;
    }
}
