package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;
import at.renehollander.advancedmanager.grid.INetworkBlock;
import at.renehollander.advancedmanager.tilentity.TileEntityNetworkController;
import net.minecraft.block.material.Material;

public class BlockNetworkController extends BlockAdvancedManagerTileEntity implements INetworkBlock {

    public BlockNetworkController() {
        super(Material.wood, "networkcontroller", TileEntityNetworkController.class);
    }

}