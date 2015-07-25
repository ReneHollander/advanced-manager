package at.renehollander.advancedmanager.scripting.eventloop;

import org.apache.commons.io.IOUtils;

import javax.script.ScriptException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {

    public Main() throws ScriptException, IOException, InstantiationException, IllegalAccessException {
        ScriptEnviroment se = ModuleRegistry.instance().createScriptEnviroment();

        se.execute(IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.js"), StandardCharsets.UTF_8));
        /*
        this.eventLoop = new EventLoop();
        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        se = (NashornScriptEngine) factory.getScriptEngine();
        se.put("main", this);
        Main main = this;
        Thread t = new Thread() {
            public void run() {
                try {

                    SimpleBindings globalBindings = new SimpleBindings();
                    //BaseModule timer = new TimerModule(se, eventLoop, globalBindings);

                    se.eval(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.js")), globalBindings);

                    eventLoop.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.setName("ScriptEngineThread");
        t.start();
        */
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }

}
