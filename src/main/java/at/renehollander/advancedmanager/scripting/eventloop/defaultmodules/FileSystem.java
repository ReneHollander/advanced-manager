package at.renehollander.advancedmanager.scripting.eventloop.defaultmodules;

import at.renehollander.advancedmanager.scripting.eventloop.module.NamedModule;
import at.renehollander.advancedmanager.scripting.eventloop.util.Function;

import java.util.HashMap;
import java.util.Map;

public class FileSystem extends NamedModule {

    public FileSystem() {
        super("fs");
        System.out.println("created fs module");
    }

    public void load() {
        super.load();
        Map<String, Object> exports = new HashMap<>();
        exports.put("writeToFile", (Function.OneArg.WithoutReturn<String>) this::writeToFile);
        this.setExports(exports);
    }

    public void writeToFile(String string) {
        System.out.println("writeToFile: " + string);
    }

}
