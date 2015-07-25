package at.renehollander.advancedmanager.scripting.eventloop;

import at.renehollander.advancedmanager.scripting.eventloop.module.Module;
import at.renehollander.advancedmanager.util.ReflectionUtil;
import com.google.common.reflect.ClassPath;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ModuleRegistry {

    private Set<ModuleHolder> modules;

    private ModuleRegistry() {
        this.modules = new HashSet<>();

        scanForModules("at.renehollander.advancedmanager.scripting.eventloop.defaultmodules");
    }

    private void scanForModules(String packageName) {
        try {
            ClassPath.from(Thread.currentThread().getContextClassLoader()).getTopLevelClassesRecursive(packageName).stream().map(ClassPath.ClassInfo::load).filter(ModuleRegistry::isModule).map((clazz) -> clazz.asSubclass(Module.class)).forEach(this::register);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ScriptEnviroment createScriptEnviroment() throws IllegalAccessException, InstantiationException {
        ScriptEnviroment scriptEnviroment = new ScriptEnviroment();
        for (ModuleHolder module : this.modules) {
            Module instance = module.moduleClass.newInstance();
            module.scriptEnviromentField.setAccessible(true);
            module.scriptEnviromentField.set(instance, scriptEnviroment);
            module.scriptEnviromentField.setAccessible(false);
            scriptEnviroment.addModule(instance);
            instance.load();
        }
        return scriptEnviroment;
    }

    public void register(Class<? extends Module> module) {
        this.modules.add(new ModuleHolder(module, ReflectionUtil.getField(module, "enviroment")));
    }

    private static final ModuleRegistry INSTANCE = new ModuleRegistry();

    public static ModuleRegistry instance() {
        return INSTANCE;
    }

    private static class ModuleHolder {
        public Class<? extends Module> moduleClass;
        public Field scriptEnviromentField;

        public ModuleHolder(Class<? extends Module> moduleClass, Field scriptEnviromentField) {
            this.moduleClass = moduleClass;
            this.scriptEnviromentField = scriptEnviromentField;
        }
    }

    private static boolean isModule(Class<?> clazz) {
        return clazz.getSuperclass() == Module.class || clazz.getSuperclass() != null && isModule(clazz.getSuperclass());
    }

}
