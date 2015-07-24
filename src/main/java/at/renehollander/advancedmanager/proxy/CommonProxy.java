package at.renehollander.advancedmanager.proxy;

import at.renehollander.advancedmanager.block.Blocks;
import at.renehollander.advancedmanager.block.TileEntities;
import at.renehollander.advancedmanager.event.EventHandler;
import at.renehollander.advancedmanager.network.DescriptionHandler;
import at.renehollander.advancedmanager.network.NetworkHandler;
import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy {

    public void preInit() {
        Blocks.preInit();
        TileEntities.preInit();
        NetworkHandler.preInit();
        DescriptionHandler.preInit();
        EventHandler.preInit();
    }

    public void init() {
    }

    public void postInit() {
    }

    public abstract EntityPlayer getClientPlayer();

    public abstract boolean isServer();

    public abstract boolean isClient();

}
