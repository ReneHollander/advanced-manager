package at.renehollander.advancedmanager.tilentity;

import at.renehollander.advancedmanager.grid.impl.TileEntityNode;

public class TileEntityNetworkCable extends TileEntityNode {

    public String toString() {
        return this.pos.toString();
    }

    @Override
    public void destroy() {
        if (this.getConnectedGrid() != null) {
            this.getConnectedGrid().removeNode(this);
        }
    }
}
