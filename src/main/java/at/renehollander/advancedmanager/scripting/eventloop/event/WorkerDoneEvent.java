package at.renehollander.advancedmanager.scripting.eventloop.event;

import at.renehollander.advancedmanager.scripting.eventloop.Worker;

public class WorkerDoneEvent implements Event {

    private Worker worker;

    public WorkerDoneEvent(Worker worker) {
        this.worker = worker;
    }

    /**
     * Getter for property 'worker'.
     *
     * @return Value for property 'worker'.
     */
    public Worker getWorker() {
        return worker;
    }

    @Override
    public void handle() {
        getWorker().getParent().getRunningTaskCounter().decrementAndGet();
        getWorker().handleDone();
    }
}
