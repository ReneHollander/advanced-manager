package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.Names;
import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.tilentity.redstonecontroller.GUI;
import at.renehollander.advancedmanager.tilentity.redstonecontroller.TileEntityRedstoneController;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneController extends BlockAwesomeModTileEntity {

    public BlockRedstoneController() {
        super(Material.wood);
        setBlockName(Names.Blocks.REDSTONE_CONTROLLER);
        setBlockTextureName(Reference.MODID_LOWER + ":" + Names.Blocks.REDSTONE_CONTROLLER);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityRedstoneController();
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        return Blocks.iron_block.getIcon(side, metadata);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        return Blocks.iron_block.getIcon(world, x, y, z, side);
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityRedstoneController te = (TileEntityRedstoneController) world.getTileEntity(x, y, z);
        return te.getProps().getPowerLevel(side);
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
        TileEntityRedstoneController te = (TileEntityRedstoneController) world.getTileEntity(x, y, z);
        te.getRunner().stop();
        super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileEntityRedstoneController te = (TileEntityRedstoneController) world.getTileEntity(x, y, z);
        if (world.isRemote) {
            te.setGui(new GUI(te));
            te.getGui().display();
        }
        return true;
    }
}
