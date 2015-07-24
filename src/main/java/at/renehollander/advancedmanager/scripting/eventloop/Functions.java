package at.renehollander.advancedmanager.scripting.eventloop;

public class Functions {

    @FunctionalInterface
    public interface Function1ArgWithReturn<R, A1> {
        public R invoke(A1 arg1);
    }

    @FunctionalInterface
    public interface Function1ArgNoReturn<A1> {
        public void invoke(A1 arg1);
    }

    @FunctionalInterface
    public interface Function2ArgWithReturn<R, A1, A2> {
        public R invoke(A1 arg1, A2 arg2);
    }

    @FunctionalInterface
    public interface Function2ArgNoReturn<A1, A2> {
        public void invoke(A1 arg1, A2 arg2);
    }

}
