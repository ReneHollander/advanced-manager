package at.renehollander.advancedmanager.scripting.eventloop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class SharedThreads {

    private static final SharedThreads INSTANCE = new SharedThreads();

    private ExecutorService executorService;

    private SharedThreads() {
        this.executorService = Executors.newCachedThreadPool(new CountingThreadFactory("Worker %d"));
    }

    /**
     * Getter for property 'executorService'.
     *
     * @return Value for property 'executorService'.
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    public static SharedThreads instance() {
        return INSTANCE;
    }

    private class CountingThreadFactory implements ThreadFactory {

        private String text;
        private int number;

        public CountingThreadFactory(String text) {
            this.text = text;
            this.number = 0;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(String.format(this.text, ++number));
            return thread;
        }
    }

}
