package at.renehollander.advancedmanager.grid.event;

import at.renehollander.advancedmanager.grid.IGrid;
import at.renehollander.advancedmanager.grid.INode;

/**
 * An Event that has something to do with the supplied Grid and Node.
 *
 * @param <NT> Type of the node and nodes in the grid
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class GridNodeEvent<NT extends INode> extends GridEvent<NT> {

    private INode<NT> node;

    /**
     * Creates a new GridNodeEvent with the given Grid and Node
     *
     * @param grid Grid
     * @param node Node
     */
    public GridNodeEvent(IGrid<NT> grid, INode<NT> node) {
        super(grid);
        this.node = node;
    }

    /**
     * Getter for property 'node'.
     *
     * @return Value for property 'node'.
     */
    public INode<NT> getNode() {
        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GridNodeEvent<?> that = (GridNodeEvent<?>) o;

        return !(node != null ? !node.equals(that.node) : that.node != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (node != null ? node.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GridNodeEvent{" +
                "node=" + node +
                '}';
    }
}
