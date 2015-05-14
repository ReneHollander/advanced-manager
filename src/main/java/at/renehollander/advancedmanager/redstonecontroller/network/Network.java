package at.renehollander.advancedmanager.redstonecontroller.network;

import com.google.common.collect.Lists;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import java.util.HashSet;
import java.util.Set;

public class Network {

    private Node master;
    private Set<Node> nodes;

    public Network(Node master) {
        this.master = master;
        this.nodes = new HashSet<Node>();
    }

    public void rediscover() {
        System.out.println("rediscovering!");
        if (!master.getWorld().isRemote) {
            long start;
            long stop;
            start = System.nanoTime();
            this.nodes.clear();
            this.nodes.add(master);
            this.check(master, nodes);
            stop = System.nanoTime();
            System.out.println("Time to discover network: " + ((stop - start) / 1000000D) + "ms. New Network Size: " + this.nodes.size());
        }
    }

    private void check(Node node, Set<Node> visited) {
        for (EnumFacing side : EnumFacing.values()) {
            BlockPos neighbourPos = node.getPos().add(side.getDirectionVec());
            IBlockState neighbourState = node.getWorld().getBlockState(neighbourPos);
            if (neighbourState.getBlock() instanceof INetworkBlock) {
                Node nextNode = (Node) node.getWorld().getTileEntity(neighbourPos);
                if (!visited.contains(nextNode)) {
                    visited.add(nextNode);
                    nextNode.setNetwork(this);
                    check(nextNode, visited);
                }
            }
        }
    }

    public Set<Node> getNodes() {
        return nodes;
    }
}
