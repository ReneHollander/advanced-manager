package at.renehollander.advancedmanager.scripting.eventloop;

import at.renehollander.advancedmanager.scripting.eventloop.defaultmodules.DefaultModule;
import at.renehollander.advancedmanager.scripting.eventloop.defaultmodules.Timer;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Main {

    private NashornScriptEngine se;
    private EventLoop eventLoop;

    public Main() throws ScriptException, FileNotFoundException {
        this.eventLoop = new EventLoop();
        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        se = (NashornScriptEngine) factory.getScriptEngine();
        se.put("main", this);
        Main main = this;
        Thread t = new Thread() {
            public void run() {
                try {

                    SimpleBindings globalBindings = new SimpleBindings();
                    DefaultModule timer = new Timer(se, eventLoop, globalBindings);

                    se.eval(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.js")), globalBindings);

                    eventLoop.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.setName("ScriptEngineThread");
        t.start();
    }

    public Object require(String moduleName) {
        return moduleName;
    }


    public static void main(String[] args) throws ScriptException, FileNotFoundException {
        new Main();
    }

}
