package at.renehollander.advancedmanager.network;

import at.renehollander.advancedmanager.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    private static SimpleNetworkWrapper INSTANCE;

    public static void init() {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
    }

    public static void sendToServer(IMessage message) {
        INSTANCE.sendToServer(message);
    }

}
