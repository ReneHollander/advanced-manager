package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;
import at.renehollander.advancedmanager.tilentity.TileEntityRedstoneScreen;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRedstoneScreen extends BlockAdvancedManagerTileEntity {

    public BlockRedstoneScreen() {
        super(Material.iron, "redstonescreen", TileEntityRedstoneScreen.class);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityRedstoneScreen();
    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.SOLID;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return true;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

}
