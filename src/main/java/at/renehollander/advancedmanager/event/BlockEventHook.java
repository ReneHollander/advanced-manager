package at.renehollander.advancedmanager.event;

import at.renehollander.advancedmanager.grid.INetworkBlock;
import at.renehollander.advancedmanager.grid.impl.TileEntityNode;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockEventHook {

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = false)
    public void onEvent(BlockEvent.PlaceEvent event) {
        if (event.placedBlock.getBlock() instanceof INetworkBlock) {
            TileEntityNode node = (TileEntityNode) event.blockSnapshot.getWorld().getTileEntity(event.blockSnapshot.pos);
            node.discover();
            System.out.println("Grid Size: " + node.getConnectedGrid().getGraph().vertexSet().size());
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = false)
    public void onEvent(BlockEvent.BreakEvent event) {
        if (event.state.getBlock() instanceof INetworkBlock) {
            TileEntityNode node = (TileEntityNode) event.world.getTileEntity(event.pos);
            node.destroy();
            System.out.println("Grid Size: " + node.getConnectedGrid().getGraph().vertexSet().size());
        }
    }

}
