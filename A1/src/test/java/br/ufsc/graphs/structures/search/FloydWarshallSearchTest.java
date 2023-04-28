package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.storage.GraphStorage;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class FloydWarshallSearchTest {

    static {
        System.out.println("TESTING: " + BreadthFirstSearchTest.class.getName());
    }

    @Test
    public void testValid() {
        final String file = "instancias/arvore_geradora_minima/agm_tiny.net";
        final Graph graph = TestHelper.createGraph(true, false,
                GraphStorage.Implementation.MIXED, file);
        int randomPickedV = ThreadLocalRandom.current().nextInt(0, graph.getVerticesQnt());
        int randomPickedD;
        do {
            randomPickedD = ThreadLocalRandom.current().nextInt(0, graph.getVerticesQnt());
        } while (randomPickedV == randomPickedD);
        var result = FloydWarshallSearcher.search(graph);
        var expectedResult = BellmanFordSearch.search(graph, randomPickedV);

        var resultWeight = result[randomPickedV][randomPickedD];
        var expectedResultWeight = expectedResult.getMiddle()[randomPickedD];

        System.out.printf("%d -> %d\n", randomPickedV, randomPickedD);
        System.out.printf("Floyd-Warshall: %s\nBellman-Ford: %s\n", resultWeight, expectedResultWeight);
        assertEquals(resultWeight, expectedResultWeight);
    }

}
