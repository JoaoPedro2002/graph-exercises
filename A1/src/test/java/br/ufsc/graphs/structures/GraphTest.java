package br.ufsc.graphs.structures;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.storage.GraphStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private static final String file = "instancias/arvore_geradora_minima/agm_tiny.net";

    private static final Graph graph = TestHelper.createGraph(false, false,
            GraphStorage.Implementation.MATRIX, file);
    static {
        System.out.println("TESTING: " + GraphTest.class.getName());
    }

    @Test
    void getVerticesQnt() {
        assertEquals(6, graph.getVerticesQnt());
    }

    @Test
    void getEdgesQnt() {
        assertEquals(12, graph.getEdgesQnt());
    }

    @Test
    void degree() {
    }

    @Test
    void neighbours() {
    }

    @Test
    void hasEdge() {
        assertTrue(graph.hasEdge(1, 3));
        assertTrue(graph.hasEdge(3, 1));
    }
}