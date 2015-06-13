package at.renehollander.advancedmanager.util;

import at.renehollander.advancedmanager.grid.IGrid;
import at.renehollander.advancedmanager.grid.INode;
import at.renehollander.advancedmanager.grid.graph.SidedEdge;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import org.jgrapht.ext.JGraphXAdapter;

import javax.swing.*;

public class Visualization<NT extends INode> {

    private IGrid<NT> grid;

    private JFrame frame;
    private mxGraphComponent graphComponent;
    private mxGraphLayout layout;

    public Visualization(IGrid<NT> grid) {
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

    public void update() {
        if (graphComponent != null) {
            SwingUtilities.invokeLater(() -> {
                frame.setTitle(grid.toString());
                graphComponent.setGraph(createAndLayout());
                graphComponent.refresh();
            });
        }
    }

    public void close() {
        if (frame != null) {
            frame.dispose();
        }
        graphComponent = null;
        layout = null;
        frame = null;
    }

    private JGraphXAdapter<NT, SidedEdge<NT>> createAndLayout() {
        JGraphXAdapter<NT, SidedEdge<NT>> jgxAdapter = new JGraphXAdapter<NT, SidedEdge<NT>>(this.grid.getGraph());
        jgxAdapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");
        layout = new mxHierarchicalLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());
        return jgxAdapter;
    }

}

