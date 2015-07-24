package at.renehollander.advancedmanager.scripting.eventloop.module;

import at.renehollander.advancedmanager.scripting.eventloop.EventLoop;
import jdk.nashorn.api.scripting.NashornScriptEngine;

public abstract class NativeModule extends Module {

    public NativeModule(String moduleName, NashornScriptEngine engine, EventLoop eventLoop) {
        super(moduleName, engine, eventLoop);
    }
}
