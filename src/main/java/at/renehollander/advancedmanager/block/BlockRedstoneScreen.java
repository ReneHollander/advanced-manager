package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;
import at.renehollander.advancedmanager.client.renderer.tileentity.TileEntityRedstoneScreenRenderer;
import at.renehollander.advancedmanager.tilentity.TileEntityRedstoneScreen;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRedstoneScreen extends BlockAdvancedManagerTileEntity {

    public BlockRedstoneScreen() {
        super(Material.iron, "redstonescreen", TileEntityRedstoneScreen.class, new TileEntityRedstoneScreenRenderer());
    }
}
