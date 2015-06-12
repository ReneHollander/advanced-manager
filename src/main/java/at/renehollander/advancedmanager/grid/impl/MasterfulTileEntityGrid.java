package at.renehollander.advancedmanager.grid.impl;

public class MasterfulTileEntityGrid extends TileEntityGrid {

    private TileEntityNode master;

    public MasterfulTileEntityGrid(TileEntityNode master) {
        this.master = master;
    }

    /**
     * Gets the masternode of the grid
     *
     * @return master node of the grid
     */
    public TileEntityNode getMaster() {
        return master;
    }

    @Override
    public void checkIntegrity() {
        // TODO if network gets split, create new newworks for the resulting subnetworks
        /*
        ConnectivityInspector<TileEntityNode, SidedEdge<TileEntityNode>> connectivityInspector = new ConnectivityInspector<TileEntityNode, SidedEdge<TileEntityNode>>(this.getGraph());
        if (!connectivityInspector.isGraphConnected()) {
            Set<TileEntityNode> connectedToMaster = connectivityInspector.connectedSetOf(this.getMaster());
            List<Set<TileEntityNode>> allSubnetworks = connectivityInspector.connectedSets();
            for (Set<TileEntityNode> subnet : allSubnetworks) {
                if (subnet != connectedToMaster) {
                    for (INode node : subnet) {
                        node.onDisconnect();
                    }
                    this.getGraph().removeAllVertices(subnet);
                }
            }
        }
        */
    }

}
