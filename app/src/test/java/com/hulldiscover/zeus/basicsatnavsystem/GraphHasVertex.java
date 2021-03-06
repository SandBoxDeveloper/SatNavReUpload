package com.hulldiscover.zeus.basicsatnavsystem;

import com.hulldiscover.zeus.basicsatnavsystem.Production.DirectedGraph;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Zeus on 12/06/16.
 */
public class GraphHasVertex {
    private static final DirectedGraph.Edge[] GRAPH = {
            new DirectedGraph.Edge("A", "B", 5),
            new DirectedGraph.Edge("B", "C", 4),
            new DirectedGraph.Edge("C", "D", 7),
            new DirectedGraph.Edge("D", "C", 8),
            new DirectedGraph.Edge("D", "E", 6),
            new DirectedGraph.Edge("A", "D", 5),
            new DirectedGraph.Edge("C", "E", 2),
            new DirectedGraph.Edge("E", "B", 3),
            new DirectedGraph.Edge("A", "E", 7),
    };

    DirectedGraph directedDirectedGraph = new DirectedGraph(GRAPH);

    /*
     * Does the graph have this
     * associated vertex ?
     *
     * Test should return true
     * for all vertex values.
     */

    // Vertex A
    @Test
    public void grapHasVertexA() throws Exception {
        assertTrue(directedDirectedGraph.graphHasVertex("A"));
    }

    // Vertex B
    @Test
    public void grapHasVertexB() throws Exception {
        assertTrue(directedDirectedGraph.graphHasVertex("B"));
    }

    // Vertex C
    @Test
    public void grapHasVertexC() throws Exception {
        assertTrue(directedDirectedGraph.graphHasVertex("C"));
    }

    // Vertex D
    @Test
    public void grapHasVertexD() throws Exception {
        assertTrue(directedDirectedGraph.graphHasVertex("D"));
    }

    // Vertex E
    @Test
    public void grapHasVertexE() throws Exception {
        assertTrue(directedDirectedGraph.graphHasVertex("E"));
    }

    // Vertex F - Doesn't exist
    // Test should return false
    @Test
    public void grapHasVertexF() throws Exception {
        assertFalse(directedDirectedGraph.graphHasVertex("F"));
    }

    //TODO: Test ValidateVertex Method that is set as private

}
