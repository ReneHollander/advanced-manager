package at.renehollander.advancedmanager.scripting.eventloop;

public abstract class Worker<T extends Callback> implements Runnable {

    private EventLoop parent;
    private T callback;

    public Worker(T callback) {
        this.callback = callback;
    }

    /**
     * Getter for property 'parent'.
     *
     * @return Value for property 'parent'.
     */
    public EventLoop getParent() {
        return parent;
    }

    /**
     * Setter for property 'parent'.
     *
     * @param parent Value to set for property 'parent'.
     */
    public void setParent(EventLoop parent) {
        this.parent = parent;
    }

    /**
     * Getter for property 'callback'.
     *
     * @return Value for property 'callback'.
     */
    public T getCallback() {
        return callback;
    }

    public void handleDone() {

    }

}
