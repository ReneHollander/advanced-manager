package at.renehollander.advancedmanager.grid.impl;

import at.renehollander.advancedmanager.grid.IGrid;
import at.renehollander.advancedmanager.util.Visualization;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * A Grid of TileEntities that form an information network. The {@link TileEntityNode TileEntityNodes} are automatically
 * discovered and managed. If a part of the network gets disconnected from the master network, the disconnected nodes will
 * get informed of the change and then removed from the Graph.
 *
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class TileEntityGrid implements IGrid<TileEntityNode> {

    private SimpleGraph<TileEntityNode, DefaultEdge> graph;

    public Visualization<TileEntityNode> vis;

    public TileEntityGrid() {
        this.graph = new SimpleGraph<>(DefaultEdge.class);
        this.vis = new Visualization<>(this);
    }

    @Override
    public SimpleGraph<TileEntityNode, DefaultEdge> getGraph() {
        return this.graph;
    }

    @Override
    public String toString() {
        return "TileEntityGrid{" +
                "graph=" + graph +
                '}';
    }
}
