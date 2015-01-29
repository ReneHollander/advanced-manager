package at.renehollander.advancedmanager.init;

import at.renehollander.advancedmanager.Names;
import at.renehollander.advancedmanager.tilentity.redstonecontroller.TileEntityRedstoneController;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityRedstoneController.class, Names.TileEntities.REDSTONE_CONTROLLER);
    }
}
