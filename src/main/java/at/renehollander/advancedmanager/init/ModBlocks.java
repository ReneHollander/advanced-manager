package at.renehollander.advancedmanager.init;

import at.renehollander.advancedmanager.block.BlockNetworkCable;
import at.renehollander.advancedmanager.block.BlockNetworkController;
import at.renehollander.advancedmanager.block.BlockRedstoneController;
import at.renehollander.advancedmanager.block.BlockRedstoneScreen;
import net.minecraft.block.Block;

public class ModBlocks {

    public static Block redstonecontroller;
    public static Block redstonescreen;
    public static Block networkcable;
    public static Block networkcontroller;

    public static void init() {
        redstonecontroller = new BlockRedstoneController();
        redstonescreen = new BlockRedstoneScreen();
        networkcable = new BlockNetworkCable();
        networkcontroller = new BlockNetworkController();
    }
}