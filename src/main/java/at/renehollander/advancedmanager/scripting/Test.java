package at.renehollander.advancedmanager.scripting;

import at.renehollander.advancedmanager.redstonecontroller.scripting.RedstoneControllerScriptingRegistry;
import at.renehollander.advancedmanager.scripting.exception.ScriptError;
import at.renehollander.advancedmanager.scripting.exception.ScriptRuntimeError;
import at.renehollander.advancedmanager.scripting.runtime.ScriptRuntime;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws ScriptRuntimeError, ScriptError {
        Map<String, Object> valuesToInject = new HashMap<>();
        valuesToInject.put("test", "faggot");
        ScriptRuntime sr = RedstoneControllerScriptingRegistry.instance().newRuntime("js", valuesToInject);
        sr.execute("console.log('har har');", (err, data) -> {
            if (err != null) err.printStackTrace();
            System.out.println(data);
            sr.stopExecution();
        });
    }

}
