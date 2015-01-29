package at.renehollander.advancedmanager.network;

import at.renehollander.advancedmanager.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler {
    private static SimpleNetworkWrapper INSTANCE;

    public static void init() {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

        INSTANCE.registerMessage(MessageRedstoneControllerUpdateScript.class, MessageRedstoneControllerUpdateScript.class, 0, Side.SERVER);
    }

    public static void sendToServer(IMessage message) {
        INSTANCE.sendToServer(message);
    }

}
