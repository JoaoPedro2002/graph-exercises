package br.ufsc.graphs.structures.connectivity;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.storage.GraphStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CFCTest {

    static {
        System.out.println("TESTING: " + CFC.class.getName());
    }

    @Test
    public void testValid() {
        final String file = "instancias/dirigidos/cfc.txt";
        final Graph graph = TestHelper.createGraph(true,
                GraphStorage.Implementation.MATRIX, file);
        var result = CFC.findFlorest(graph);
        assertNotNull(result);
    }
}

