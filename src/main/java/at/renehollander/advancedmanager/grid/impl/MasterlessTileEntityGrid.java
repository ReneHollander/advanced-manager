package at.renehollander.advancedmanager.grid.impl;

import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Set;

/**
 * A {@link TileEntityGrid} network that has no master node
 *
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public class MasterlessTileEntityGrid extends TileEntityGrid {

    @Override
    public void removeNode(TileEntityNode node) {
        this.getGraph().removeVertex(node);
        if (this.getGraph().vertexSet().size() <= 0) {
            vis.close();
        } else {
            ConnectivityInspector<TileEntityNode, DefaultEdge> connectivityInspector = new ConnectivityInspector<>(this.getGraph());
            // if the grid isn't disconnected, don't do anything
            if (!connectivityInspector.isGraphConnected()) {
                // get all subnets of the graph
                List<Set<TileEntityNode>> subgrids = connectivityInspector.connectedSets();
                if (subgrids.size() <= 0) throw new IllegalStateException("should not happen");
                // select a random subnet to keep
                Set<TileEntityNode> keep = subgrids.stream().findAny().get();
                // loop through all subnets and create new masterless grids for them
                // but dont convert the network that will be kept
                subgrids.stream().filter((set) -> set != keep).forEach((subgrid) -> {
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
    }

    @Override
    public String toString() {
        return "MasterlessTileEntityGrid[size=" + this.getGraph().vertexSet().size() + "]";
    }
}
