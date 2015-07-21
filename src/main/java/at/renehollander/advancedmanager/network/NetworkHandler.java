package at.renehollander.advancedmanager.network;

import at.renehollander.advancedmanager.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class NetworkHandler {
    private static SimpleNetworkWrapper INSTANCE;

    public static void preInit() {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
    }

    public static void sendToServer(IMessage message) {
        INSTANCE.sendToServer(message);
    }

}
