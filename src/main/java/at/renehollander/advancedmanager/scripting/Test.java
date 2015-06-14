package at.renehollander.advancedmanager.scripting;

import at.renehollander.advancedmanager.redstonecontroller.scripting.RedstoneControllerScriptingRegistry;
import at.renehollander.advancedmanager.scripting.exception.ScriptError;
import at.renehollander.advancedmanager.scripting.exception.ScriptRuntimeError;
import at.renehollander.advancedmanager.scripting.runtime.ScriptRuntime;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {

    public static void main(String[] args) throws ScriptRuntimeError, ScriptError {
        Map<String, Object> valuesToInject = new HashMap<>();
        valuesToInject.put("test", "faggot");
        Set<String> classWhitelist = new HashSet<String>();
        classWhitelist.add("java.lang.String");
        ScriptRuntime sr = RedstoneControllerScriptingRegistry.instance().newRuntime("js", classWhitelist, valuesToInject);
        sr.execute("var System = Java.type(\"java.lang.System\"); System.out.println(\"okok\"); var list = []; list.push('faggot'); console.print(list); console.log('har HAR'.toLowerCase());", (err, data) -> {
            if (err != null) err.printStackTrace();
            System.out.println(data);
            sr.stopExecution();
        });
    }

}
