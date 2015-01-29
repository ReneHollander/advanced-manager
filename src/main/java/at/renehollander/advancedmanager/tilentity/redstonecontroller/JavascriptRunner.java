package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavascriptRunner implements Runnable {

    private RedstoneControllerProperties props;
    private ScriptEngine engine;

    private Thread evalThread;

    private Tick tick;

    public JavascriptRunner(RedstoneControllerProperties props) {
        this.props = props;

        this.tick = new Tick();

        ScriptEngineManager manager = new ScriptEngineManager();
        this.engine = manager.getEngineByName("nashorn");
        this.engine.put("props", this.props);
        this.engine.put("chat", new Chat());
        this.engine.put("tick", this.tick);
    }

    public void tick() {
        this.tick.tick();
    }

    public void start() {
        this.evalThread = new Thread(this);
        this.evalThread.setDaemon(true);
        this.evalThread.start();
    }

    public void stop() {
        if (this.evalThread != null) {
            this.evalThread.stop();
            this.evalThread = null;
        }
    }

    @Override
    public void run() {
        try {
            engine.eval(props.getScript());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public RedstoneControllerProperties getProps() {
        return props;
    }

    public void reload() {
        this.stop();
        this.getProps().reset();
        this.start();
    }
}
