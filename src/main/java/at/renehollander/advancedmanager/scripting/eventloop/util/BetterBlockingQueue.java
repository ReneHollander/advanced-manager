package at.renehollander.advancedmanager.scripting.eventloop.util;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BetterBlockingQueue<T> extends ArrayBlockingQueue<T> {
    public BetterBlockingQueue(int capacity) {
        super(capacity);
    }

    public BetterBlockingQueue(int capacity, boolean fair) {
        super(capacity, fair);
    }

    public BetterBlockingQueue(int capacity, boolean fair, Collection<? extends T> c) {
        super(capacity, fair, c);
    }

    public void loopUntil(long timeout, TimeUnit timeUnit, Supplier<Boolean> runCheck, Consumer<T> consumer) {
        T t;
        while (runCheck.get()) {
            try {
                while ((t = this.poll(timeout, timeUnit)) != null && runCheck.get()) {
                    consumer.accept(t);
                }
            } catch (InterruptedException ignored) {
            }
        }
    }
}
