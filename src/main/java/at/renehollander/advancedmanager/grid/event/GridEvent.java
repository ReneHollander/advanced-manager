package at.renehollander.advancedmanager.grid.event;

import at.renehollander.advancedmanager.grid.IGrid;
import at.renehollander.advancedmanager.grid.INode;
import org.apache.commons.lang3.Validate;

/**
 * An Event that has something to do with the supplied Grid.
 *
 * @param <NT> Type of the nodes in the grid
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public class GridEvent<NT extends INode> extends Event {

    private IGrid<NT> grid;

    /**
     * Creates a new GridEvent with the given Grid
     *
     * @param grid Grid from that the event comes from
     */
    public GridEvent(IGrid<NT> grid) {
        Validate.notNull(grid);
        this.grid = grid;
    }

    /**
     * Gets the Grid associated with this GridEvent
     *
     * @return Grid
     */
    public IGrid<NT> getGrid() {
        return grid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GridEvent<?> gridEvent = (GridEvent<?>) o;

        return !(grid != null ? !grid.equals(gridEvent.grid) : gridEvent.grid != null);

    }

    @Override
    public int hashCode() {
        return grid != null ? grid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GridEvent{" +
                "grid=" + grid +
                '}';
    }
}
