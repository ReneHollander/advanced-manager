package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;
import at.renehollander.advancedmanager.redstonecontroller.network.INetworkBlock;
import at.renehollander.advancedmanager.tilentity.TileEntityNetworkController;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockNetworkController extends BlockAdvancedManagerTileEntity implements INetworkBlock {

    public BlockNetworkController() {
        super(Material.wood, "networkcontroller", TileEntityNetworkController.class);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getStateFromMeta(meta);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityNetworkController();
    }

}