package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;
import at.renehollander.advancedmanager.grid.INetworkBlock;
import at.renehollander.advancedmanager.tilentity.TileEntityNetworkCable;
import net.minecraft.block.material.Material;

public class BlockNetworkCable extends BlockAdvancedManagerTileEntity implements INetworkBlock {

    public BlockNetworkCable() {
        super(Material.wood, "networkcable", TileEntityNetworkCable.class);
    }

}