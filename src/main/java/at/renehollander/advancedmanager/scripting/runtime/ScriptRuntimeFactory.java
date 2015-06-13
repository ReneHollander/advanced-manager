package at.renehollander.advancedmanager.scripting.runtime;

import at.renehollander.advancedmanager.scripting.api.API;
import at.renehollander.advancedmanager.scripting.api.APIInfo;
import at.renehollander.advancedmanager.scripting.exception.ScriptRuntimeError;
import at.renehollander.advancedmanager.util.Pair;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScriptRuntimeFactory {

    private static final ScriptRuntimeFactory INSTANCE = new ScriptRuntimeFactory();

    private Map<String, Pair<APIInfo, Class<? extends API>>> apis;
    private Map<String, Pair<ScriptRuntimeInfo, Class<? extends ScriptRuntime>>> runtimes;

    private ScriptRuntimeFactory() {
        this.apis = new HashMap<>();
        this.runtimes = new HashMap<>();
    }

    public void registerAPI(Class<? extends API> apiClass) {
        APIInfo.Annotation annotation = apiClass.getAnnotation(APIInfo.Annotation.class);
        if (annotation == null)
            throw new IllegalArgumentException("API " + apiClass + " needs a APIInfo annotation!");
        APIInfo info = APIInfo.fromAnnotation(annotation);
        this.apis.put(info.getShortName(), new Pair<>(info, apiClass));
    }

    public void registerScriptRuntime(Class<? extends ScriptRuntime> scriptRuntimeClass) {
        ScriptRuntimeInfo.Annotation annotation = scriptRuntimeClass.getAnnotation(ScriptRuntimeInfo.Annotation.class);
        if (annotation == null)
            throw new IllegalArgumentException("ScriptRuntime " + scriptRuntimeClass + " needs a ScriptRuntimeInfo annotation!");
        ScriptRuntimeInfo info = ScriptRuntimeInfo.fromAnnotation(annotation);
        this.runtimes.put(info.getShortName(), new Pair<>(info, scriptRuntimeClass));
    }

    public API newAPI(String shortName) throws ScriptRuntimeError {
        Pair<APIInfo, Class<? extends API>> api = apis.get(shortName);
        if (api == null) return null;
        try {
            return api.getRight().newInstance();
        } catch (Exception e) {
            throw new ScriptRuntimeError(e);
        }
    }

    public ScriptRuntime newRuntime(String shortName) throws ScriptRuntimeError {
        Pair<ScriptRuntimeInfo, Class<? extends ScriptRuntime>> runtime = runtimes.get(shortName);
        if (runtime == null) return null;
        try {
            return runtime.getRight().newInstance();
        } catch (Exception e) {
            throw new ScriptRuntimeError(e);
        }
    }

    public Collection<Pair<APIInfo, Class<? extends API>>> getAPIs() {
        return Collections.unmodifiableCollection(apis.values());
    }

    public Collection<Pair<ScriptRuntimeInfo, Class<? extends ScriptRuntime>>> getRuntimes() {
        return Collections.unmodifiableCollection(runtimes.values());
    }

    public APIInfo getAPIInfo(String shortName) {
        Pair<APIInfo, Class<? extends API>> runtime = apis.get(shortName);
        if (runtime == null) return null;
        return runtime.getLeft();
    }

    public ScriptRuntimeInfo getRuntimeInfo(String shortName) {
        Pair<ScriptRuntimeInfo, Class<? extends ScriptRuntime>> runtime = runtimes.get(shortName);
        if (runtime == null) return null;
        return runtime.getLeft();
    }

    public static ScriptRuntimeFactory instance() {
        return INSTANCE;
    }

}
