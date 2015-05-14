package at.renehollander.advancedmanager.event;

import at.renehollander.advancedmanager.redstonecontroller.network.INetworkBlock;
import at.renehollander.advancedmanager.redstonecontroller.network.Network;
import at.renehollander.advancedmanager.redstonecontroller.network.Node;
import at.renehollander.advancedmanager.tilentity.TileEntityNetworkController;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockEventHook {

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = false)
    public void onEvent(BlockEvent.PlaceEvent event) {
        if (event.placedBlock.getBlock() instanceof INetworkBlock) {
            Node node = (Node) event.blockSnapshot.getWorld().getTileEntity(event.blockSnapshot.pos);
            System.out.println("placed network node " + node);
            if (node instanceof TileEntityNetworkController) {
                node.setNetwork(new Network(node));
            }
            node.discover();
        }
    }

}
