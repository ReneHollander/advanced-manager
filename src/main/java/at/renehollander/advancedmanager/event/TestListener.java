package at.renehollander.advancedmanager.event;

import at.renehollander.advancedmanager.grid.event.GridNodeAddEvent;
import at.renehollander.advancedmanager.grid.impl.TileEntityNode;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TestListener {

    @SubscribeEvent
    public void onNodeAdd(GridNodeAddEvent<TileEntityNode> event) {
        System.out.println(event);
    }

}
