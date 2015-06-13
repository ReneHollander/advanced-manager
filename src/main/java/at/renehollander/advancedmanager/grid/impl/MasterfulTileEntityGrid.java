package at.renehollander.advancedmanager.grid.impl;

import at.renehollander.advancedmanager.grid.graph.SidedEdge;
import org.jgrapht.alg.ConnectivityInspector;

import java.util.List;
import java.util.Set;

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
        if (this.getGraph().vertexSet().size() <= 0) {
            vis.close();
        } else {
            ConnectivityInspector<TileEntityNode, SidedEdge<TileEntityNode>> connectivityInspector = new ConnectivityInspector<>(this.getGraph());
            List<Set<TileEntityNode>> subgrids = connectivityInspector.connectedSets();
            Set<TileEntityNode> keep = null;
            if (this.getGraph().containsVertex(this.getMaster())) {
                keep = connectivityInspector.connectedSetOf(this.getMaster());
            } else {
                this.vis.close();
            }
            if (subgrids.size() <= 0) throw new IllegalStateException("should not happen");
            final Set<TileEntityNode> finalKeep = keep;
            subgrids.stream().filter((set) -> set != finalKeep).forEach((subgrid) -> {
                MasterlessTileEntityGrid newgrid = new MasterlessTileEntityGrid();
                subgrid.forEach((subnode) -> {
                    subnode.setConnectedGrid(newgrid);
                    newgrid.getGraph().addVertex(subnode);
                });
                subgrid.forEach((subnode) -> {
                    Set<SidedEdge<TileEntityNode>> edges = this.getGraph().edgesOf(subnode);
                    edges.forEach((edge) -> {
                        newgrid.getGraph().addEdge(edge.getV1(), edge.getV2(), edge);
                    });
                });
                this.getGraph().removeAllVertices(subgrid);
                newgrid.vis.update();
            });
        }
        this.vis.update();
    }

    @Override
    public String toString() {
        return "MasterfulTileEntityGrid[size: " + this.getGraph().vertexSet().size() + ", master: " + this.getMaster() + "]";
    }
}
