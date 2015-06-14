package at.renehollander.advancedmanager.redstonecontroller.scripting.api;

import at.renehollander.advancedmanager.scripting.api.APIInfo;

@APIInfo.Annotation(shortName = "console", name = "Console", description = "Log messages to console")
public class Console extends RedstoneControllerAPI {

    public void log(String string) {
        System.out.println(string);
    }

}
