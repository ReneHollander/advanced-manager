package at.renehollander.advancedmanager.scripting.eventloop.util;

public interface Callback<T extends Callback> {

    @FunctionalInterface
    public interface EmptyCallback extends Callback<EmptyCallback> {
        public void invoke();
    }

    @FunctionalInterface
    public interface ErrorCallback<E extends Throwable> extends Callback<ErrorCallback> {
        public void invoke(E err);
    }

    @FunctionalInterface
    public interface Error1DataCallback<E extends Throwable, D> extends Callback<Error1DataCallback> {
        public void invoke(E err, D data);
    }

}
