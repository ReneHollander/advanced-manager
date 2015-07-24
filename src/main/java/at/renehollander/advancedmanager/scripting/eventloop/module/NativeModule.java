package at.renehollander.advancedmanager.scripting.eventloop.module;

import at.renehollander.advancedmanager.scripting.eventloop.ScriptEnviroment;

public abstract class NativeModule extends SimpleModule {
    public NativeModule(ScriptEnviroment enviroment, String moduleName) {
        super(enviroment, moduleName);
    }
}
