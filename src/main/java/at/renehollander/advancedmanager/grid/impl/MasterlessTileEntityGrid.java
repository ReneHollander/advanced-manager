package at.renehollander.advancedmanager.grid.impl;

import at.renehollander.advancedmanager.grid.graph.SidedEdge;
import org.jgrapht.alg.ConnectivityInspector;

import java.util.List;
import java.util.Set;

public class MasterlessTileEntityGrid extends TileEntityGrid {

    @Override
    public void removeNode(TileEntityNode node) {
        this.getGraph().removeVertex(node);
        if (this.getGraph().vertexSet().size() <= 0) {
            vis.close();
        } else {
            ConnectivityInspector<TileEntityNode, SidedEdge<TileEntityNode>> connectivityInspector = new ConnectivityInspector<>(this.getGraph());
            if (!connectivityInspector.isGraphConnected()) {
                List<Set<TileEntityNode>> subgrids = connectivityInspector.connectedSets();
                if (subgrids.size() <= 0) throw new IllegalStateException("should not happen");
                Set<TileEntityNode> keep = subgrids.stream().findAny().get();
                subgrids.stream().filter((set) -> set != keep).forEach((subgrid) -> {
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
    }

    @Override
    public String toString() {
        return "MasterlessTileEntityGrid[size: " + this.getGraph().vertexSet().size() + "]";
    }
}
