package at.renehollander.advancedmanager.grid.impl;

import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Set;

/**
 * A {@link TileEntityGrid} network that has a master node
 *
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public class MasterfulTileEntityGrid extends TileEntityGrid {

    private TileEntityNode master;

    public MasterfulTileEntityGrid(TileEntityNode master) {
        this.master = master;
    }

    /**
     * Gets the master node of the grid
     *
     * @return master node of the grid
     */
    public TileEntityNode getMaster() {
        return master;
    }

    @Override
    public void removeNode(TileEntityNode node) {
        this.getGraph().removeVertex(node);
        node.setConnectedGrid(null);
        if (this.getGraph().vertexSet().size() <= 0) {
            vis.close();
        } else {
            ConnectivityInspector<TileEntityNode, DefaultEdge> connectivityInspector = new ConnectivityInspector<>(this.getGraph());
            // get all subnets of the graph
            List<Set<TileEntityNode>> subgrids = connectivityInspector.connectedSets();
            if (subgrids.size() <= 0) throw new IllegalStateException("should not happen");

            // if the master node still exists, keep it
            // if the master node was removed the masterful grid gets converted to a masterless
            Set<TileEntityNode> keep = null;
            if (this.getGraph().containsVertex(this.getMaster())) {
                keep = connectivityInspector.connectedSetOf(this.getMaster());
            } else {
                this.vis.close();
            }
            final Set<TileEntityNode> finalKeep = keep;

            // loop through all subnets and create new masterless grids for them
            // but dont convert the network that will be kept
            subgrids.stream().filter((set) -> set != finalKeep).forEach((subgrid) -> {
                // create a new grid
                MasterlessTileEntityGrid newgrid = new MasterlessTileEntityGrid();
                // add all nodes from the subnet to the new grid
                subgrid.forEach((subnode) -> {
                    subnode.setConnectedGrid(newgrid);
                    newgrid.getGraph().addVertex(subnode);
                });
                // add all connections from the subnet to the new grid
                subgrid.forEach((subnode) -> {
                    Set<DefaultEdge> edges = this.getGraph().edgesOf(subnode);
                    edges.forEach((edge) -> {
                        newgrid.getGraph().addEdge(this.getGraph().getEdgeSource(edge), this.getGraph().getEdgeTarget(edge));
                    });
                });
                // remove all nodes from the original network
                this.getGraph().removeAllVertices(subgrid);
                newgrid.vis.update();
            });
        }
        this.vis.update();
    }

    @Override
    public String toString() {
        return "MasterfulTileEntityGrid[size=" + this.getGraph().vertexSet().size() + ", master=" + this.getMaster() + "]";
    }
}
