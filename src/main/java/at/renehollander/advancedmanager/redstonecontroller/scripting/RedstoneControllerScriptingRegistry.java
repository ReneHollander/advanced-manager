package at.renehollander.advancedmanager.redstonecontroller.scripting;

import at.renehollander.advancedmanager.redstonecontroller.scripting.api.Chat;
import at.renehollander.advancedmanager.redstonecontroller.scripting.api.Console;
import at.renehollander.advancedmanager.scripting.impl.JavascriptScriptRuntime;
import at.renehollander.advancedmanager.scripting.runtime.ScriptingRegistry;

public class RedstoneControllerScriptingRegistry extends ScriptingRegistry {

    private static final RedstoneControllerScriptingRegistry INSTANCE = new RedstoneControllerScriptingRegistry();

    private RedstoneControllerScriptingRegistry() {
        super();
        this.registerScriptRuntime(JavascriptScriptRuntime.class);

        this.registerAPI(Chat.class);
        this.registerAPI(Console.class);
    }

    public static RedstoneControllerScriptingRegistry instance() {
        return INSTANCE;
    }
}
