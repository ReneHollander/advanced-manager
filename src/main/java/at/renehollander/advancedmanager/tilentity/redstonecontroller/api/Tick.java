package at.renehollander.advancedmanager.tilentity.redstonecontroller.api;

public class Tick {

    private Tickable tickable;

    public void setOnTick(Tickable tickable) {
        this.tickable = tickable;
    }

    public void tick() {
        if (this.tickable != null) {
            this.tickable.tick();
        }
    }

    @FunctionalInterface
    public interface Tickable {
        public void tick();
    }

}
