package at.renehollander.advancedmanager.grid.event.impl;

import at.renehollander.advancedmanager.grid.IGrid;
import at.renehollander.advancedmanager.grid.INode;
import at.renehollander.advancedmanager.grid.event.GridNodeEvent;

public class GridNodeRemoveEvent<NT extends INode> extends GridNodeEvent<NT> {

    public GridNodeRemoveEvent(IGrid<NT> grid, INode<NT> node) {
        super(grid, node);
    }
}
