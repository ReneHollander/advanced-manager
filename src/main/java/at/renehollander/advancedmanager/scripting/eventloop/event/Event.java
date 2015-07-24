package at.renehollander.advancedmanager.scripting.eventloop.event;

import at.renehollander.advancedmanager.scripting.eventloop.EventLoop;

public abstract class Event {

    private EventLoop parent;

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
}
