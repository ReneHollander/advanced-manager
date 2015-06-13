package at.renehollander.advancedmanager.event;

import at.renehollander.advancedmanager.grid.INetworkBlock;
import at.renehollander.advancedmanager.grid.exception.MultipleMasterNodesException;
import at.renehollander.advancedmanager.grid.impl.TileEntityNode;
import at.renehollander.advancedmanager.util.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockEventHook {

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = false)
    public void onEvent(BlockEvent.PlaceEvent event) {
        if (event.placedBlock.getBlock() instanceof INetworkBlock) {
            TileEntityNode node = (TileEntityNode) event.blockSnapshot.getWorld().getTileEntity(event.blockSnapshot.pos);
            try {
                node.discover();
                Log.debug(node.getConnectedGrid());
            } catch (MultipleMasterNodesException mmne) {
                event.player.addChatMessage(new ChatComponentText("You can't have multiple master nodes on 1 network grid!"));
                event.setCanceled(true);
            } catch (Exception e) {
                Minecraft.getMinecraft().crashed(new CrashReport("Error placing grid node", e));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = false)
    public void onEvent(BlockEvent.BreakEvent event) {
        if (event.state.getBlock() instanceof INetworkBlock) {
            TileEntityNode node = (TileEntityNode) event.world.getTileEntity(event.pos);
            try {
                node.destroy();
                if (node.getConnectedGrid() != null)
                    Log.debug(node.getConnectedGrid());
            } catch (Exception e) {
                Minecraft.getMinecraft().crashed(new CrashReport("Error removing grid node", e));
            }
        }
    }

}
