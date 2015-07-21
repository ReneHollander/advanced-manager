package at.renehollander.advancedmanager.event;

import net.minecraftforge.common.MinecraftForge;

public class EventHandler {

    public static void preInit() {
        register(new BlockEventHook());
        register(new TestListener());
    }

    public static void register(Object o) {
        MinecraftForge.EVENT_BUS.register(o);
    }

}
