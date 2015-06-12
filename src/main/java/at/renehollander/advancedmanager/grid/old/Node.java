package at.renehollander.advancedmanager.grid.old;

import at.renehollander.advancedmanager.grid.INetworkBlock;
import at.renehollander.advancedmanager.tilentity.base.TileEntityAdvancedManager;
import com.google.common.collect.Lists;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import java.util.List;

public abstract class Node extends TileEntityAdvancedManager {

    private Network network;

    public Node() {
    }

    public void discover() {
        if (!getWorld().isRemote) {
            System.out.println("discovering " + this);
            if (this.network == null) {
                List<Node> foundNeighbours = Lists.newArrayList();

                for (EnumFacing side : EnumFacing.values()) {
                    BlockPos neighbourPos = pos.add(side.getDirectionVec());
                    IBlockState neighbourState = getWorld().getBlockState(neighbourPos);
                    if (neighbourState.getBlock() instanceof INetworkBlock) {
                        Node node = (Node) getWorld().getTileEntity(neighbourPos);
                        foundNeighbours.add(node);
                    }
                }

                if (foundNeighbours.size() != 0) {
                    Network nw = getSameNetwork(foundNeighbours);
                    if (nw != null) {
                        nw.rediscover();
                    }
                }
            } else {
                this.network.rediscover();
            }
        }
    }

    private static Network getSameNetwork(List<Node> nodes) {
        Network nw = null;
        for (Node node : nodes) {
            if (nw == null) {
                nw = node.getNetwork();
            } else {
                if (node.getNetwork() != nw) {
                    throw new RuntimeException("you cant connect two networks!");
                }
            }
        }
        return nw;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
}
