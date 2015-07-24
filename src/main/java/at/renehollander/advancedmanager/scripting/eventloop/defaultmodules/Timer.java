package at.renehollander.advancedmanager.scripting.eventloop.defaultmodules;

import at.renehollander.advancedmanager.scripting.eventloop.ScriptEnviroment;
import at.renehollander.advancedmanager.scripting.eventloop.Worker;
import at.renehollander.advancedmanager.scripting.eventloop.event.UniversalEvent;
import at.renehollander.advancedmanager.scripting.eventloop.module.BaseModule;
import at.renehollander.advancedmanager.scripting.eventloop.util.Callback;
import at.renehollander.advancedmanager.scripting.eventloop.util.Function;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class Timer extends BaseModule {

    public Timer(ScriptEnviroment enviroment) {
        super(enviroment);
    }

    public void load() {
        getEnviroment().getBindings().put("setTimeout", (Function.TwoArg.WithoutReturn<ScriptObjectMirror, Integer>) this::setTimeout);
        getEnviroment().getBindings().put("setInterval", (Function.TwoArg.WithoutReturn<ScriptObjectMirror, Integer>) this::setInterval);
    }

    public void setTimeout(ScriptObjectMirror somCb, int duration) {
        Callback.Empty cb = somCb.to(Callback.Empty.class);
        Worker w = new Worker<Callback.Empty>(cb) {
            private String outdata;

            @Override
            public void handleDone() {
                this.getCallback().invoke();
            }

            public void run() {
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        getEnviroment().getEventLoop().postWorker(w);
    }

    public void setInterval(ScriptObjectMirror somCb, int duration) {
        Callback.Empty cb = somCb.to(Callback.Empty.class);
        Worker w = new Worker<Callback.Empty>(cb) {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(duration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.getParent().postEvent(new UniversalEvent() {
                        @Override
                        public void handle() {
                            cb.invoke();
                        }
                    });
                }
            }
        };
        getEnviroment().getEventLoop().postWorker(w);
    }

}
