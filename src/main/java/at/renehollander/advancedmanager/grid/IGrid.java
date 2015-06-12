package at.renehollander.advancedmanager.grid;

import at.renehollander.advancedmanager.grid.graph.SidedEdge;
import at.renehollander.advancedmanager.grid.old.Node;
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
     * Gets the masternode of the grid
     *
     * @return master node of the grid
     */
    public NT getMaster();

    /**
     * Get the Undirected Graph that represents the node layout.
     *
     * @return Graph representing node layout
     */
    public SimpleGraph<NT, SidedEdge<NT>> getGraph();

    /**
     * Check the integrity of the network. If there are areas of the network not connected anymore
     * they will get removed. When a {@link INode} gets removed, the {@link INode#onDisconnect()
     * onDisconnect} method of the TileEntityNode gets called
     */
    public void checkIntegrity();


}
