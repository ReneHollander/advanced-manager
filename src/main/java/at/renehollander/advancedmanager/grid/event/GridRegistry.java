package at.renehollander.advancedmanager.grid.event;

public class GridRegistry {

    public void registerListener(Listener listener) {
        // TODO implement listener logic
    }

    /* ---------- STATIC STUFF ---------- */
    private static GridRegistry INSTANCE;

    public static void init() {
        INSTANCE = new GridRegistry();
    }

    public GridRegistry instance() {
        return INSTANCE;
    }
}
