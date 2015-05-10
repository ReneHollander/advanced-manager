package at.renehollander.advancedmanager.init;

import at.renehollander.advancedmanager.block.BlockRedstoneController;
import net.minecraft.block.Block;

public class ModBlocks {

    public static Block redstoneController;

    public static void init() {
        redstoneController = new BlockRedstoneController();
    }
}