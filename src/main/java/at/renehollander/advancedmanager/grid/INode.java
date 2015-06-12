package at.renehollander.advancedmanager.grid;

/**
 * Represents a TileEntityNode in an {@link IGrid}
 *
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public interface INode<NT extends INode> {

    /**
     * Gets the {@link IGrid} associated with this INode.
     *
     * @return the {@link IGrid} associated with this node, {@code null} if the node is not connected to a network
     */
    public IGrid<NT> getConnectedGrid();

    /**
     * Checks if the node is in a {@link IGrid}
     *
     * @return <tt>true</tt> if this node is connected to a grid
     */
    public boolean inGrid();

    /**
     * This events get fired if the node is disconnected from the network. Could
     * also be executed if {@link IGrid#checkIntegrity()} detects that this node is
     * no longer part of the network.
     */
    public void onDisconnect();

    /**
     * Check for nearby nodes and add to the network of the node if it has one.
     */
    public void discoverNearbyNetwork();
}
