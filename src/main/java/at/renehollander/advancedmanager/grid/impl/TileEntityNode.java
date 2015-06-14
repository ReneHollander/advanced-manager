package at.renehollander.advancedmanager.grid.impl;

import at.renehollander.advancedmanager.grid.IGrid;
import at.renehollander.advancedmanager.grid.IMasterNode;
import at.renehollander.advancedmanager.grid.IGridBlock;
import at.renehollander.advancedmanager.grid.INode;
import at.renehollander.advancedmanager.grid.exception.MultipleMasterNodesException;
import at.renehollander.advancedmanager.tilentity.base.TileEntityAdvancedManager;
import at.renehollander.advancedmanager.util.Pair;
import at.renehollander.advancedmanager.util.Trio;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TileEntityNode extends TileEntityAdvancedManager implements INode<TileEntityNode> {

    private IGrid<TileEntityNode> grid;

    public void setConnectedGrid(IGrid<TileEntityNode> grid) {
        this.grid = grid;
    }

    public IGrid<TileEntityNode> getConnectedGrid() {
        return this.grid;
    }

    public boolean inGrid() {
        return this.grid != null;
    }

    public void discover() {
        // find all nearby nodes
        Set<Pair<EnumFacing, TileEntityNode>> foundNodes = findNearbyNodes();

        // group nodes into masterful and masterless grids
        Set<Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>>> masterfulGrids = getNearbyMasterfulGrids(foundNodes);
        Set<Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>>> masterlessGrids = getNearbyMasterlessGrids(foundNodes);

        if (masterfulGrids.size() < 1) {
            // if there are no masterful grids
            if (masterlessGrids.size() < 1) {
                // and no masterless
                // create a new grid and asign it to the node and add itself to the graph
                // if the new node is a master node create a new masterful network
                if (this instanceof IMasterNode) {
                    this.setConnectedGrid(new MasterfulTileEntityGrid(this));
                } else {
                    this.setConnectedGrid(new MasterlessTileEntityGrid());
                }
                this.getConnectedGrid().getGraph().addVertex(this);
            } else if (masterlessGrids.size() > 0) {
                if (this instanceof IMasterNode) {
                    this.setConnectedGrid(new MasterfulTileEntityGrid(this));
                    this.getConnectedGrid().getGraph().addVertex(this);
                    merge(this, this.getConnectedGrid(), masterlessGrids);
                } else {
                    // and at least 1 masterless
                    // select a masterless grid to which all nodes are added
                    // select first grid
                    Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>> newgrid = masterlessGrids.stream().findFirst().get();
                    // select all remaining grids
                    Set<Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>>> gridstomerge = masterlessGrids.stream().filter((grid) -> grid != newgrid).collect(Collectors.toSet());
                    // add itself to new grid
                    addSelfToGrid(newgrid);
                    // merge remaining grids into new grid
                    merge(newgrid, gridstomerge);
                }
            }
        } else if (masterfulGrids.size() == 1) {
            if (this instanceof IMasterNode) {
                throw new MultipleMasterNodesException("You cant connect to masterful networks together");
            }
            // if there is one masterful grid and i am not a master node
            Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>> grid = masterfulGrids.stream().findFirst().get();
            if (masterlessGrids.size() == 0) {
                // and no masterless grids
                // add itself to grid
                addSelfToGrid(grid);
            } else if (masterlessGrids.size() > 0) {
                // and at least 1 masterless grid
                // add itself to grid
                addSelfToGrid(grid);
                // merge masterless grids into new grid
                merge(grid, masterlessGrids);
            }
        } else if (masterfulGrids.size() > 1) {
            // if there is more than 1 masterful grid
            // throw exception because two masterful grids cant be connected together
            throw new MultipleMasterNodesException("You cant connect to masterful networks together");
        }
        ((TileEntityGrid) this.getConnectedGrid()).vis.update();
    }

    @Override
    public void destroy() {
        if (this.getConnectedGrid() != null) {
            this.getConnectedGrid().removeNode(this);
        }
    }

    /**
     * Add itself to the specified grid
     *
     * @param grid Grid to add to
     */
    private void addSelfToGrid(Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>> grid) {
        this.setConnectedGrid(grid.getRight());
        this.getConnectedGrid().getGraph().addVertex(this);
        this.getConnectedGrid().getGraph().addEdge(this, grid.getMiddle());
    }

    /**
     * Merge the specified oldgrids into newgrid
     *
     * @param newgrid  Grid to merge into
     * @param oldgrids Grids to merge into newgrid
     */
    private void merge(Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>> newgrid, Set<Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>>> oldgrids) {
        for (Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>> masterless : oldgrids) {
            merge(newgrid, masterless);
        }
    }

    /**
     * Merge the specified oldgrids into newgrid
     *
     * @param newgrid  Grid to merge into
     * @param oldgrids Grids to merge into newgrid
     */
    private void merge(TileEntityNode newMaster, IGrid<TileEntityNode> newgrid, Set<Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>>> oldgrids) {
        for (Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>> masterless : oldgrids) {
            merge(newMaster, newgrid, masterless);
        }
    }

    /**
     * Merge a grid into another
     *
     * @param newgrid Grid to merge to
     * @param oldgrid Grid to merge into newgrid
     */
    private void merge(TileEntityNode newMaster, IGrid<TileEntityNode> newgrid, Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>> oldgrid) {
        if (newgrid != oldgrid.getRight()) {
            for (TileEntityNode vertex : oldgrid.getRight().getGraph().vertexSet()) {
                newgrid.getGraph().addVertex(vertex);
                vertex.setConnectedGrid(newgrid);
            }
            for (DefaultEdge edge : oldgrid.getRight().getGraph().edgeSet()) {
                newgrid.getGraph().addEdge(oldgrid.getRight().getGraph().getEdgeSource(edge), oldgrid.getRight().getGraph().getEdgeTarget(edge));
            }
            ((TileEntityGrid) oldgrid.getRight()).vis.close();
        }
        newgrid.getGraph().addEdge(this, oldgrid.getMiddle());
    }

    /**
     * Merge a grid into another
     *
     * @param newgrid Grid to merge to
     * @param oldgrid Grid to merge into newgrid
     */
    private void merge(Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>> newgrid, Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>> oldgrid) {
        if (newgrid.getRight() != oldgrid.getRight()) {
            for (TileEntityNode vertex : oldgrid.getRight().getGraph().vertexSet()) {
                newgrid.getRight().getGraph().addVertex(vertex);
                vertex.setConnectedGrid(newgrid.getRight());
            }
            for (DefaultEdge edge : oldgrid.getRight().getGraph().edgeSet()) {
                newgrid.getRight().getGraph().addEdge(oldgrid.getRight().getGraph().getEdgeSource(edge), oldgrid.getRight().getGraph().getEdgeTarget(edge));
            }
            ((TileEntityGrid) oldgrid.getRight()).vis.close();
        }
        newgrid.getRight().getGraph().addEdge(this, oldgrid.getMiddle());
    }

    /**
     * Probe nearby blocks in the world. If the block is an {@link IGridBlock}
     * it will get added to the return set,
     *
     * @return Set of all found nodes
     */
    private Set<Pair<EnumFacing, TileEntityNode>> findNearbyNodes() {
        Set<Pair<EnumFacing, TileEntityNode>> foundNodes = new HashSet<>();
        for (EnumFacing side : EnumFacing.values()) {
            BlockPos neighbourPos = this.getPos().add(side.getDirectionVec());
            IBlockState neighbourState = this.getWorld().getBlockState(neighbourPos);
            if (neighbourState.getBlock() instanceof IGridBlock) {
                TileEntityNode nextNode = (TileEntityNode) this.getWorld().getTileEntity(neighbourPos);
                foundNodes.add(new Pair<>(side, nextNode));
            }
        }
        return foundNodes;
    }

    /**
     * Get all masterful grids from the set of nodes
     *
     * @param nodes Nodes to get masterful grids from
     * @return Set of all found masterful grids
     */
    private Set<Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>>> getNearbyMasterfulGrids(Set<Pair<EnumFacing, TileEntityNode>> nodes) {
        return getNearbyGridsByType(nodes, (pair) -> pair.getRight().getConnectedGrid() instanceof MasterfulTileEntityGrid);
    }

    /**
     * Get all masterless grids from the set of nodes
     *
     * @param nodes Nodes to get masterless grids from
     * @return Set of all found masterless grids
     */
    private Set<Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>>> getNearbyMasterlessGrids(Set<Pair<EnumFacing, TileEntityNode>> nodes) {
        return getNearbyGridsByType(nodes, (pair) -> pair.getRight().getConnectedGrid() instanceof MasterlessTileEntityGrid);
    }

    /**
     * Get all grids by the type specified in the predicate
     *
     * @param nodes  Nodes to get grids matching the predicate from
     * @param byType If the test returns <tt>true</tt>, the node will be added to the return set
     * @return Set of all found grids
     */
    private Set<Trio<EnumFacing, TileEntityNode, IGrid<TileEntityNode>>> getNearbyGridsByType(Set<Pair<EnumFacing, TileEntityNode>> nodes, Predicate<Pair<EnumFacing, TileEntityNode>> byType) {
        return nodes.stream().filter(byType).map((pair) -> new Trio<>(pair.getLeft(), pair.getRight(), pair.getRight().getConnectedGrid())).collect(Collectors.toSet());
    }

}
