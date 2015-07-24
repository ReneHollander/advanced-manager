package at.renehollander.advancedmanager.scripting.eventloop;

import at.renehollander.advancedmanager.scripting.eventloop.event.Event;
import at.renehollander.advancedmanager.scripting.eventloop.event.WorkerDoneEvent;
import at.renehollander.advancedmanager.scripting.eventloop.util.BetterBlockingQueue;
import at.renehollander.advancedmanager.scripting.eventloop.util.SharedThreads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class EventLoop {

    private boolean running;

    private BetterBlockingQueue<Event> eventQueue;
    private AtomicInteger runningTaskCounter;

    public EventLoop() {
        runningTaskCounter = new AtomicInteger();
        eventQueue = new BetterBlockingQueue<>(100000);
    }

    public void postWorker(Worker worker) {
        runningTaskCounter.addAndGet(1);
        SharedThreads.instance().getExecutorService().execute(() -> {
            worker.run();
            postEvent(new WorkerDoneEvent(worker));
        });
    }

    public void postEvent(Event event) {
        eventQueue.offer(event);
    }

    public void start() {
        running = true;
        eventQueue.loopUntil(100, TimeUnit.MILLISECONDS, () -> (runningTaskCounter.get() != 0 && eventQueue.size() != 0) || isRunning(), Event::handle);
        SharedThreads.instance().getExecutorService().shutdownNow();
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public AtomicInteger getRunningTaskCounter() {
        return runningTaskCounter;
    }

    private void handleEvent(Event event) {
        event.handle();
    }
}
