package at.renehollander.advancedmanager.init;

import at.renehollander.advancedmanager.block.BlockRedstoneController;
import at.renehollander.advancedmanager.block.BlockRedstoneScreen;
import net.minecraft.block.Block;

public class ModBlocks {

    public static Block redstonecontroller;
    public static Block redstonescreen;

    public static void init() {
        redstonecontroller = new BlockRedstoneController();
        redstonescreen = new BlockRedstoneScreen();
    }
}