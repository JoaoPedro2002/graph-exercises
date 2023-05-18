package br.ufsc.graphs.structures.connectivity;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.storage.GraphStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TopologyTest {
    static {
        System.out.println("TESTING: " + TopologyTest.class.getName());
    }

    @Test
    public void testValid() {
        // no manha.net foi adicionado uma aresta no "escovar os dentes", pois não se tinha dependencia alguma
        final String file = "instancias/dirigidos/manha.net";
        final Graph graph = TestHelper.createGraph(false,
                GraphStorage.Implementation.MATRIX, file);
        var result = TopologyOrdinator.order(graph);
        TopologyOrdinator.print(result, graph);
    }
}
