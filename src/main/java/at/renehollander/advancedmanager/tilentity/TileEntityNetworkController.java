package at.renehollander.advancedmanager.tilentity;

import at.renehollander.advancedmanager.grid.IMasterNode;
import at.renehollander.advancedmanager.grid.impl.TileEntityNode;

public class TileEntityNetworkController extends TileEntityNode implements IMasterNode<TileEntityNode> {

    @Override
    public void destroy() {
        if (this.getConnectedGrid() != null) {
            this.getConnectedGrid().removeNode(this);
        }
    }

    public String toString() {
        return "TileEntityNetworkController[pos=" + this.pos.toString() + "]";
    }

}
