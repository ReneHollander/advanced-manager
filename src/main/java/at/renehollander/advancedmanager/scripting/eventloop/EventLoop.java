package at.renehollander.advancedmanager.scripting.eventloop;

import at.renehollander.advancedmanager.scripting.eventloop.event.Event;
import at.renehollander.advancedmanager.scripting.eventloop.event.UniversalEvent;
import at.renehollander.advancedmanager.scripting.eventloop.event.WorkerDoneEvent;
import at.renehollander.advancedmanager.scripting.eventloop.event.WorkerNewEvent;
import at.renehollander.advancedmanager.scripting.eventloop.util.BetterBlockingQueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class EventLoop {

    private boolean running;

    private BetterBlockingQueue<Event> eventQueue;
    private AtomicInteger runningTaskCount;

    public EventLoop() {
        runningTaskCount = new AtomicInteger();
        eventQueue = new BetterBlockingQueue<>(100000);
    }

    public void postWorker(Worker worker) {
        worker.setParent(this);
        this.postEvent(new WorkerNewEvent(worker));
    }

    public void postEvent(Event event) {
        event.setParent(this);
        eventQueue.offer(event);
    }

    public void start() {
        running = true;
        eventQueue.loopUntil(100, TimeUnit.MILLISECONDS, () -> (runningTaskCount.get() != 0 && eventQueue.size() != 0) || isRunning(), this::handleEvent);
        SharedThreads.instance().getExecutorService().shutdownNow();
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    private void handleEvent(Event event) {
        if (event instanceof WorkerNewEvent) {
            WorkerNewEvent newWorkerEvent = (WorkerNewEvent) event;
            Worker realWorker = newWorkerEvent.getWorker();
            runningTaskCount.addAndGet(1);
            SharedThreads.instance().getExecutorService().execute(() -> {
                realWorker.run();
                postEvent(new WorkerDoneEvent(realWorker));
            });
        } else if (event instanceof WorkerDoneEvent) {
            runningTaskCount.decrementAndGet();
            WorkerDoneEvent doneWorkerEvent = (WorkerDoneEvent) event;
            doneWorkerEvent.getWorker().handleDone();
        } else if (event instanceof UniversalEvent) {
            ((UniversalEvent) event).handle();
        }
    }
}
