package at.renehollander.advancedmanager.scripting.eventloop.defaultmodules;

import at.renehollander.advancedmanager.scripting.eventloop.module.Module;
import at.renehollander.advancedmanager.scripting.eventloop.module.NamedModule;
import at.renehollander.advancedmanager.scripting.eventloop.util.Function;

public class Require extends Module {

    public Require() {
    }

    public void load() {
        super.load();
        getEnviroment().getBindings().put("require", (Function.OneArg.WithReturn<Object, String>) this::require);
    }

    public Object require(String name) {
        NamedModule module = getEnviroment().getModuleNameMapping().get(name);
        if (module == null) {
            throw new RuntimeException("module \"" + name + "\" not found");
        }
        return module.getExports();
    }

}
