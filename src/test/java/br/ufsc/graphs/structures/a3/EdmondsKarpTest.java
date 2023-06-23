package br.ufsc.graphs.structures.a3;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.storage.GraphStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EdmondsKarpTest {

    static {
        System.out.println("TESTING: " + EdmondsKarp.class.getName());
    }

    @Test
    public void testValid() {
        final String file = "instancias/dirigidos/fluxoMaximo1.txt";
        // final String file = "instancias/dirigidos/fluxoMaximo.txt";
        final Graph graph = TestHelper.createGraph(true,
                GraphStorage.Implementation.ADJACENCY_LIST, file);
        // Mudar o source e o target para testar outros casos
        final int source = 0;
        final int target = 3;
        var result = EdmondsKarp.findMaximumFlow(graph, source, target);
        System.out.println(result);
        assertNotNull(result);
    }
}

