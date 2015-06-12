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
            System.out.println("placed network node " + node);
            node.discover();
        }
    }

}
