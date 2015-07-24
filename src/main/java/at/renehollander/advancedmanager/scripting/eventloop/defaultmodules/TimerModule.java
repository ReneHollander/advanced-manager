package at.renehollander.advancedmanager.scripting.eventloop.defaultmodules;

import at.renehollander.advancedmanager.scripting.eventloop.ScriptEnviroment;
import at.renehollander.advancedmanager.scripting.eventloop.event.Event;
import at.renehollander.advancedmanager.scripting.eventloop.module.BaseModule;
import at.renehollander.advancedmanager.scripting.eventloop.util.Callback;
import at.renehollander.advancedmanager.scripting.eventloop.util.Function;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.Timer;
import java.util.TimerTask;

public class TimerModule extends BaseModule {

    private Timer timer;

    public TimerModule(ScriptEnviroment enviroment) {
        super(enviroment);

        this.timer = new Timer(true);
    }

    public void load() {
        getEnviroment().getBindings().put("setTimeout", (Function.TwoArg.WithReturn<TimerTask, ScriptObjectMirror, Integer>) this::setTimeout);
        getEnviroment().getBindings().put("clearTimeout", (Function.OneArg.WithoutReturn<TimerTask>) this::clearTimeout);
        getEnviroment().getBindings().put("setInterval", (Function.TwoArg.WithReturn<TimerTask, ScriptObjectMirror, Integer>) this::setInterval);
        getEnviroment().getBindings().put("clearInterval", (Function.OneArg.WithoutReturn<TimerTask>) this::clearInterval);
    }

    public Timer getTimer() {
        return timer;
    }

    public TimerTask setTimeout(ScriptObjectMirror somCb, int duration) {
        Callback.Empty cb = somCb.to(Callback.Empty.class);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                TimerModule.this.getEnviroment().getEventLoop().postEvent((Event) cb::invoke);
            }
        };
        getTimer().schedule(timerTask, duration);
        return timerTask;
    }

    public void clearTimeout(TimerTask timerTask) {
        timerTask.cancel();
    }

    public TimerTask setInterval(ScriptObjectMirror somCb, int duration) {
        Callback.Empty cb = somCb.to(Callback.Empty.class);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                TimerModule.this.getEnviroment().getEventLoop().postEvent((Event) cb::invoke);
            }
        };
        getTimer().scheduleAtFixedRate(timerTask, 0, duration);
        return timerTask;
    }

    public void clearInterval(TimerTask timerTask) {
        timerTask.cancel();
    }

}
