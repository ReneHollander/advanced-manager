package at.renehollander.advancedmanager.util;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;

import javax.swing.*;

public class Visualization<V, E> {

    private Graph<V, E> graph;

    private JFrame frame;
    private mxGraphComponent graphComponent;
    private mxGraphLayout layout;

    public Visualization(Graph<V, E> graph) {
        this.graph = graph;

        frame = new JFrame();
        frame.setTitle("Graph");
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
                graphComponent.setGraph(createAndLayout());
                graphComponent.refresh();
            });
        }
    }

    public void setGraph(Graph<V, E> newGraph) {
        this.graph = newGraph;
    }

    public void close() {
        if (frame != null) {
            frame.dispose();
        }
        graphComponent = null;
        layout = null;
        frame = null;
    }

    private JGraphXAdapter<V, E> createAndLayout() {
        JGraphXAdapter<V, E> jgxAdapter = new JGraphXAdapter<V, E>(this.graph);
        jgxAdapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");
        layout = new mxHierarchicalLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());
        return jgxAdapter;
    }

}

