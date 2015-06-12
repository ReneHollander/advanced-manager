package at.renehollander.advancedmanager.grid;

import at.renehollander.advancedmanager.grid.graph.SidedEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * Describes a TileEntityGrid/Network of {@link INode INodes} connected together.
 *
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IGrid<NT extends INode> {

    /**
     * Get the Undirected Graph that represents the node layout.
     *
     * @return Graph representing node layout
     */
    public SimpleGraph<NT, SidedEdge<NT>> getGraph();

    /**
     * Remove the node from the grid. If the grid splits into subnets, create new grids from those subnets.
     *
     * @param node Node to remove from network
     */
    public void removeNode(NT node);

}
