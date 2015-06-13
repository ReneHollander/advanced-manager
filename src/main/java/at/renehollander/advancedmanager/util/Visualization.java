package at.renehollander.advancedmanager.util;

import at.renehollander.advancedmanager.grid.IGrid;
import at.renehollander.advancedmanager.grid.INode;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;

/**
 * Visualize a {@link IGrid} with JGraph in an extra Swing GUI screen.
 *
 * @param <NT> Type of the nodes in the IGrid
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public class Visualization<NT extends INode> {

    private static boolean ENABLE_GRID_VISUALIZATION = false;

    private IGrid<NT> grid;

    private JFrame frame;
    private mxGraphComponent graphComponent;
    private mxGraphLayout layout;

    /**
     * Create a new Visualization from the specified grid. Can be updated with the {@link #update()} method.
     * The JFrame gets opened automatically. Only displays if {@link #ENABLE_GRID_VISUALIZATION} is set <tt>true</tt>.
     *
     * @param grid IGrid to display
     */
    public Visualization(IGrid<NT> grid) {
        if (ENABLE_GRID_VISUALIZATION) {
            this.grid = grid;

            frame = new JFrame();
            frame.setTitle(grid.toString());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setAutoRequestFocus(false);
            frame.toBack();
            frame.setSize(800, 600);

            graphComponent = new mxGraphComponent(createAndLayout());
            graphComponent.setEnabled(false);
            frame.getContentPane().add(graphComponent);

            frame.setVisible(true);
        }
    }

    /**
     * Update the visualizazion. If {@link #ENABLE_GRID_VISUALIZATION} is false, it just does nothing.
     */
    public void update() {
        if (ENABLE_GRID_VISUALIZATION) {
            if (graphComponent != null) {
                SwingUtilities.invokeLater(() -> {
                    frame.setTitle(grid.toString());
                    graphComponent.setGraph(createAndLayout());
                    graphComponent.refresh();
                });
            }
        }
    }

    /**
     * Closes and cleans up JFrame and other GUI components. If {@link #ENABLE_GRID_VISUALIZATION}
     * is false, it just does nothing.
     */
    public void close() {
        if (ENABLE_GRID_VISUALIZATION) {
            if (frame != null) {
                frame.dispose();
            }
            graphComponent = null;
            layout = null;
            frame = null;
        }
    }

    private JGraphXAdapter<NT, DefaultEdge> createAndLayout() {
        JGraphXAdapter<NT, DefaultEdge> jgxAdapter = new JGraphXAdapter<NT, DefaultEdge>(this.grid.getGraph());
        jgxAdapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");
        layout = new mxHierarchicalLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());
        return jgxAdapter;
    }

}

