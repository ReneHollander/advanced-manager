package at.renehollander.advancedmanager.scripting.runtime;

import at.renehollander.advancedmanager.scripting.api.API;
import at.renehollander.advancedmanager.scripting.api.APIInfo;
import at.renehollander.advancedmanager.scripting.exception.ScriptRuntimeError;
import at.renehollander.advancedmanager.util.Pair;
import at.renehollander.advancedmanager.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.*;

public abstract class ScriptingRegistry {

    private static final String SCRIPTRUNTIME_RUNTIMEINFOFIELD = "runtimeInfo";
    private static final String SCRIPTRUNTIME_APILISTFIELD = "apis";
    private static final String SCRIPTRUNTIME_CLASSWHITELISTFIELD = "classWhitelist";

    private static final String API_APIINFOFIELD = "apiInfo";

    private Map<String, Pair<APIInfo, Class<? extends API>>> apis;
    private Map<String, Pair<ScriptRuntimeInfo, Class<? extends ScriptRuntime>>> runtimes;

    public ScriptingRegistry() {
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

    public API newAPI(Pair<APIInfo, Class<? extends API>> api, Map<String, Object> toInject) throws ScriptRuntimeError {
        if (api == null) return null;
        try {
            API apiObj = api.getRight().newInstance();
            Map<String, Field> fields = ReflectionUtil.getAllFields(new HashMap<>(), api.getRight());
            inject(api.getRight(), fields, API_APIINFOFIELD, apiObj, api.getLeft(), false);
            if (toInject != null) {
                for (Map.Entry<String, Object> entry : toInject.entrySet()) {
                    inject(api.getRight(), fields, entry.getKey(), apiObj, entry.getValue(), true);
                }
            }
            return apiObj;
        } catch (Exception e) {
            throw new ScriptRuntimeError(e);
        }
    }

    public List<API> createAllAPIs(Map<String, Object> toInject) throws ScriptRuntimeError {
        List<API> apis = new ArrayList<>();
        for (Pair<APIInfo, Class<? extends API>> api : getAPIs()) {
            API apiObj = newAPI(api, toInject);
            apis.add(apiObj);
        }
        return apis;
    }

    public ScriptRuntime newRuntime(String shortName, Set<String> classWhitelist, Map<String, Object> toInject) throws ScriptRuntimeError {
        Pair<ScriptRuntimeInfo, Class<? extends ScriptRuntime>> runtime = runtimes.get(shortName);
        if (runtime == null) return null;
        try {
            ScriptRuntime runtimeObj = runtime.getRight().newInstance();
            Map<String, Field> fields = ReflectionUtil.getAllFields(new HashMap<>(), runtime.getRight());
            inject(runtime.getRight(), fields, SCRIPTRUNTIME_RUNTIMEINFOFIELD, runtimeObj, runtime.getLeft(), false);
            inject(runtime.getRight(), fields, SCRIPTRUNTIME_APILISTFIELD, runtimeObj, createAllAPIs(toInject), false);
            inject(runtime.getRight(), fields, SCRIPTRUNTIME_CLASSWHITELISTFIELD, runtimeObj, classWhitelist, false);
            runtimeObj.initialize();
            return runtimeObj;
        } catch (Exception e) {
            throw new ScriptRuntimeError(e);
        }
    }

    private void inject(Class<?> clazz, Map<String, Field> fields, String fieldName, Object obj, Object val, boolean dontCareIfFieldNonExistent) throws ScriptRuntimeError {
        try {
            Field f = fields.get(fieldName);
            if (!dontCareIfFieldNonExistent && f == null)
                throw new NoSuchElementException("field " + fieldName + " non existent");
            if (f != null) {
                boolean accessible = f.isAccessible();
                f.setAccessible(true);
                f.set(obj, val);
                f.setAccessible(accessible);
            }
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

}
