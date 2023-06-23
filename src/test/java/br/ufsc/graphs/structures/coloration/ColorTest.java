package br.ufsc.graphs.structures.coloration;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.storage.GraphStorage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ColorTest {

    static {
        System.out.println("TESTING: " + Colorizer.class.getName());
    }

    @Test
    public void testValid() {
        final String file = "instancias/coloracao/cor3.net";
        final Graph graph = TestHelper.createGraph(false,
                GraphStorage.Implementation.MATRIX, file);
        var result = Colorizer.minimumColoration(graph);
        Assertions.assertEquals(3, result);
    }
}
