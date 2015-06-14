package at.renehollander.advancedmanager.tilentity;

import at.renehollander.advancedmanager.grid.IMasterNode;
import at.renehollander.advancedmanager.grid.impl.TileEntityNode;

import java.util.Set;

public class TileEntityAdvancedController extends TileEntityNode implements IMasterNode<TileEntityNode> {

    public String toString() {
        return "TileEntityAdvancedController[pos=" + this.pos.toString() + "]";
    }

}
