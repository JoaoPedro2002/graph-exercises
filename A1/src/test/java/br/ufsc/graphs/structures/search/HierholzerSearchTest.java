package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.storage.GraphStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HierholzerSearchTest {
    static {
        System.out.println("TESTING: " + HierholzerSearchTest.class.getName());
    }

    @Test
    public void testValidCycle() {
        final String file = "instancias/ciclo_euleriano/ContemCicloEuleriano.net";
        final Graph graph = TestHelper.createGraph(false, false,
                GraphStorage.Implementation.MIXED, file);
        var result = HierholzerSearch.search(graph);
        assertTrue(result.getLeft());
        for (int i = 1; i < result.getRight().size(); i++) {
            assertTrue(graph.hasEdge(result.getRight().get(i - 1), result.getRight().get(i)));
        }
    }

    @Test
    public void testInvalidCycle() {
        final String file = "instancias/ciclo_euleriano/SemCicloEuleriano.net";
        final Graph graph = TestHelper.createGraph(false, false,
                GraphStorage.Implementation.MIXED, file);
        var result = HierholzerSearch.search(graph);
        assertFalse(result.getLeft());
    }
}