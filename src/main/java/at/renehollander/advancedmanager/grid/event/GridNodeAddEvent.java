package at.renehollander.advancedmanager.grid.event;

import at.renehollander.advancedmanager.grid.IGrid;
import at.renehollander.advancedmanager.grid.INode;

/**
 * This event gets called, if a new node gets added to the grid
 *
 * @param <NT> Type of the node and nodes in the grid
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public class GridNodeAddEvent<NT extends INode> extends GridNodeEvent<NT> {

    /**
     * Creates a new GridNodeAddEvent with the given Grid and Node
     *
     * @param grid Grid
     * @param node Node
     */
    public GridNodeAddEvent(IGrid<NT> grid, INode<NT> node) {
        super(grid, node);
    }
}
