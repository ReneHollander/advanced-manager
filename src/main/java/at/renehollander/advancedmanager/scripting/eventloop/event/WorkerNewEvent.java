package at.renehollander.advancedmanager.scripting.eventloop.event;

import at.renehollander.advancedmanager.scripting.eventloop.Worker;

public class WorkerNewEvent extends Event {

    private Worker worker;

    public WorkerNewEvent(Worker worker) {
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
}
