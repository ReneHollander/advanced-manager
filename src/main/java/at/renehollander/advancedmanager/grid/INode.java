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
     * @return the {@link IGrid} associated with this node
     */
    public IGrid<NT> getConnectedGrid();

    /**
     * Sets the {@link IGrid} associated with this INode.
     *
     * @param grid {@link IGrid} that should be associated with this node
     */
    public void setConnectedGrid(IGrid<NT> grid);

    /**
     * Check for nearby nodes. If no exisiting networks are found create a new one.
     */
    public void discover();

    /**
     * Call to destroy node and create new networks from resulting subnets
     */
    public void destroy();
}
