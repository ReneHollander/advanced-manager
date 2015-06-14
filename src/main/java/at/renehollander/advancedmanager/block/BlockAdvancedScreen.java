package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;
import at.renehollander.advancedmanager.client.renderer.tileentity.TileEntityAdvancedScreenRenderer;
import at.renehollander.advancedmanager.grid.IGridBlock;
import at.renehollander.advancedmanager.tilentity.TileEntityAdvancedScreen;
import net.minecraft.block.material.Material;

public class BlockAdvancedScreen extends BlockAdvancedManagerTileEntity implements IGridBlock {

    public BlockAdvancedScreen() {
        super(Material.iron, "advancedscreen", TileEntityAdvancedScreen.class, new TileEntityAdvancedScreenRenderer());
    }
}
