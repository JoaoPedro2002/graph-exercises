package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.storage.GraphStorage;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BreadthFirstSearchTest {

    static {
        System.out.println("TESTING: " + BreadthFirstSearchTest.class.getName());
    }

    @Test
    public void testValid() {
        final String file = "instancias/arvore_geradora_minima/agm_tiny.net";
        final Graph graph = TestHelper.createGraph(true, false,
                GraphStorage.Implementation.MIXED, file);
        for (int v = 0; v < graph.getVerticesQnt(); v++) {
            var result = BreadthFirstSearcher.search(graph, v);
            assertTrue(result.getRight().containsAll(IntStream.range(0, graph.getVerticesQnt()).boxed().toList()));
        }
    }
}
