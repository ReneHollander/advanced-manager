package at.renehollander.advancedmanager.tilentity;

import at.renehollander.advancedmanager.grid.impl.TileEntityNode;

public class TileEntityNetworkCable extends TileEntityNode {

    @Override
    public void destroy() {
        if (this.getConnectedGrid() != null) {
            this.getConnectedGrid().removeNode(this);
        }
    }

    public String toString() {
        return "TileEntityNetworkCable[pos=" + this.pos.toString() + "]";
    }
}
