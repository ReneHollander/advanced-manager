package at.renehollander.advancedmanager.grid.impl;

import at.renehollander.advancedmanager.grid.IGrid;
import at.renehollander.advancedmanager.grid.INetworkBlock;
import at.renehollander.advancedmanager.grid.INode;
import at.renehollander.advancedmanager.grid.old.Node;
import at.renehollander.advancedmanager.tilentity.base.TileEntityAdvancedManager;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import java.util.HashSet;
import java.util.Set;

public abstract class TileEntityNode extends TileEntityAdvancedManager implements INode<TileEntityNode> {

    private IGrid<TileEntityNode> grid;

    public TileEntityNode() {
    }

    public void setConnectedGrid(IGrid<TileEntityNode> grid) {
        this.grid = grid;
    }

    @Override
    public IGrid<TileEntityNode> getConnectedGrid() {
        return this.grid;
    }

    public boolean inGrid() {
        return this.grid != null;
    }

    @Override
    public void discoverNearbyNetwork() {
        Set<TileEntityNode> foundNodes = new HashSet<>();
        for (EnumFacing side : EnumFacing.values()) {
            BlockPos neighbourPos = this.getPos().add(side.getDirectionVec());
            IBlockState neighbourState = this.getWorld().getBlockState(neighbourPos);
            if (neighbourState.getBlock() instanceof INetworkBlock) {
                TileEntityNode nextNode = (TileEntityNode) this.getWorld().getTileEntity(neighbourPos);
                foundNodes.add(nextNode);
                if (!visited.contains(nextNode)) {
                    visited.add(nextNode);
                    nextNode.setNetwork(this);
                    check(nextNode, visited);
                }
            }
        }

    }
}
