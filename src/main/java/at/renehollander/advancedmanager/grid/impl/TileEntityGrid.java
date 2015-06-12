package at.renehollander.advancedmanager.grid.impl;

import at.renehollander.advancedmanager.grid.IGrid;
import at.renehollander.advancedmanager.grid.INode;
import at.renehollander.advancedmanager.grid.graph.SidedEdge;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.SimpleGraph;

import java.util.List;
import java.util.Set;

/**
 * A Grid of TileEntities that form an information network. The {@link TileEntityNode TileEntityNodes} are automatically
 * discovered and managed. If a part of the network gets disconnected from the master network, the disconnected nodes will
 * get informed of the change and then removed from the Graph.
 *
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public class TileEntityGrid implements IGrid<TileEntityNode> {

    private TileEntityNode master;
    private SimpleGraph<TileEntityNode, SidedEdge<TileEntityNode>> graph;

    public TileEntityGrid(TileEntityNode master) {
        this.master = master;
        this.graph = new SimpleGraph<TileEntityNode, SidedEdge<TileEntityNode>>(SidedEdge.class);
    }

    public TileEntityNode getMaster() {
        return master;
    }

    @Override
    public SimpleGraph<TileEntityNode, SidedEdge<TileEntityNode>> getGraph() {
        return this.graph;
    }

    @Override
    public void checkIntegrity() {
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
    }

}
