package at.renehollander.advancedmanager.scripting.eventloop.defaultmodules;

import at.renehollander.advancedmanager.scripting.eventloop.Callback;
import at.renehollander.advancedmanager.scripting.eventloop.EventLoop;
import at.renehollander.advancedmanager.scripting.eventloop.Functions;
import at.renehollander.advancedmanager.scripting.eventloop.Worker;
import at.renehollander.advancedmanager.scripting.eventloop.event.UniversalEvent;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Bindings;

public class Timer extends DefaultModule {

    public Timer(NashornScriptEngine engine, EventLoop eventLoop, Bindings bindings) {
        super(engine, eventLoop, bindings);

        getBindings().put("setTimeout", (Functions.Function2ArgNoReturn<ScriptObjectMirror, Integer>) this::setTimeout);
        getBindings().put("setInterval", (Functions.Function2ArgNoReturn<ScriptObjectMirror, Integer>) this::setInterval);
    }

    public void setTimeout(ScriptObjectMirror somCb, int duration) {
        Callback.EmptyCallback cb = somCb.to(Callback.EmptyCallback.class);
        Worker w = new Worker<Callback.EmptyCallback>(cb) {
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
        getEventLoop().postWorker(w);
    }

    public void setInterval(ScriptObjectMirror somCb, int duration) {
        Callback.EmptyCallback cb = somCb.to(Callback.EmptyCallback.class);
        Worker w = new Worker<Callback.EmptyCallback>(cb) {
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
        getEventLoop().postWorker(w);
    }

}
