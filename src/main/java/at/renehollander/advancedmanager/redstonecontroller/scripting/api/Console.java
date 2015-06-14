package at.renehollander.advancedmanager.redstonecontroller.scripting.api;

import at.renehollander.advancedmanager.scripting.api.APIInfo;

import java.util.Arrays;
import java.util.List;

@APIInfo.Annotation(shortName = "console", name = "Console", description = "Log messages to console")
public class Console extends RedstoneControllerAPI {

    public void log(String string) {
        System.out.println(string);
    }

    public void print(List<Object> list) {
        System.out.println(list.toString());
    }

}
