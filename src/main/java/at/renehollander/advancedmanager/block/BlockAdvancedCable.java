package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;
import at.renehollander.advancedmanager.grid.IGridBlock;
import at.renehollander.advancedmanager.tilentity.TileEntityAdvancedCable;
import net.minecraft.block.material.Material;

public class BlockAdvancedCable extends BlockAdvancedManagerTileEntity implements IGridBlock {

    public BlockAdvancedCable() {
        super(Material.wood, "advancedcable", TileEntityAdvancedCable.class);
    }

}