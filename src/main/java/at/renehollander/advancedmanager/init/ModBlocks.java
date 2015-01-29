package at.renehollander.advancedmanager.init;

import at.renehollander.advancedmanager.Names;
import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.block.BlockRedstoneController;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

@GameRegistry.ObjectHolder(Reference.MODID)
public class ModBlocks {

    public static final Block redstoneController = new BlockRedstoneController();

    public static void init() {
        GameRegistry.registerBlock(redstoneController, Names.Blocks.REDSTONE_CONTROLLER);
    }
}