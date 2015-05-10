package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.tilentity.redstonecontroller.GUI;
import at.renehollander.advancedmanager.tilentity.redstonecontroller.TileEntityRedstoneController;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneController extends BlockAwesomeModTileEntity {

    public BlockRedstoneController() {
        super(Material.wood, "redstonecontroller", TileEntityRedstoneController.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityRedstoneController();
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
        TileEntityRedstoneController te = (TileEntityRedstoneController) worldIn.getTileEntity(pos);
        return te.getProps().getPowerLevel(side);
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityRedstoneController te = (TileEntityRedstoneController) worldIn.getTileEntity(pos);
        te.getRunner().stop();
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntityRedstoneController te = (TileEntityRedstoneController) worldIn.getTileEntity(pos);
        if (worldIn.isRemote) {
            te.setGui(new GUI(te));
            te.getGui().display();
        }
        return true;
    }
}