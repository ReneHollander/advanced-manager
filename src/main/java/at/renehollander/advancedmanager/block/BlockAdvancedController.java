package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;
import at.renehollander.advancedmanager.grid.IGridBlock;
import at.renehollander.advancedmanager.tilentity.TileEntityAdvancedController;
import net.minecraft.block.material.Material;

public class BlockAdvancedController extends BlockAdvancedManagerTileEntity implements IGridBlock {

    public BlockAdvancedController() {
        super(Material.wood, "advancedcontroller", TileEntityAdvancedController.class);
    }

}