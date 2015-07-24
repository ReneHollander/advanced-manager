package at.renehollander.advancedmanager.scripting.eventloop.util;

public interface Callback {

    @FunctionalInterface
    interface Empty extends Callback {
        void invoke();
    }

    @FunctionalInterface
    interface Error<E extends Throwable> extends Callback {
        void invoke(E err);

        @FunctionalInterface
        interface OneArg<E extends Throwable, A1> extends Callback {
            void invoke(E err, A1 arg1);
        }

        @FunctionalInterface
        interface TwoArg<E extends Throwable, A1, A2> extends Callback {
            void invoke(E err, A1 arg1, A2 arg2);
        }

    }

    @FunctionalInterface
    interface OneArg<A1> extends Callback {
        void invoke(A1 arg1);
    }

    @FunctionalInterface
    interface TwoArg<A1, A2> extends Callback {
        void invoke(A1 arg1, A2 arg2);
    }

}
