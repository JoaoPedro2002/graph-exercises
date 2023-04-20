package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.storage.GraphStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BellmanFordSearchTest {

    static {
        System.out.println("TESTING: " + BellmanFordSearch.class.getName());
    }

    @Test
    public void testValid() {
        final String file = "instancias/caminho_minimo/fln_pequena.net";
        final Graph graph = TestHelper.createGraph(true, false,
                GraphStorage.Implementation.MIXED, file);
        var result = BellmanFordSearch.search(graph);
        assertTrue(result.getLeft());
    }
}
