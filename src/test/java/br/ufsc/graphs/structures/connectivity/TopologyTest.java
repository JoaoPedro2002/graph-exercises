package br.ufsc.graphs.structures.connectivity;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.search.BellmanFordSearch;
import br.ufsc.graphs.structures.storage.GraphStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TopologyTest {
    static {
        System.out.println("TESTING: " + TopologyTest.class.getName());
    }

    @Test
    public void testValid() {
        final String file = "instancias/dirigidos/tcc_completo.net";
        final Graph graph = TestHelper.createGraph(false,
                GraphStorage.Implementation.MATRIX, file);
        var result = TopologyOdirnator.order(graph);
        TopologyOdirnator.print(result, graph);
    }
}
