package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.storage.GraphStorage;
import br.ufsc.graphs.structures.util.Edge;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KruskalSearchTest {

    static final String file = "instancias/arvore_geradora_minima/agm_tiny.net";
    static final Graph graph = TestHelper.createGraph(true, GraphStorage.Implementation.MIXED, file);

    static {
        System.out.println("TESTING: " + KruskalSearchTest.class.getName());
    }

    @Test
    public void testMinimumSpanningForest() {
        var result = KruskalSearch.search(graph);
        double sum = result.stream().mapToDouble(graph::weight).sum();
        System.out.println(sum);
        result.stream()
                .map(Object::toString)
                .reduce((a, b) -> a + ", " + b)
                .ifPresent(System.out::println);

        assertEquals(graph.getVerticesQnt() - 1, result.size());
        assertEquals(9.8, sum);
        assertTrue(result.stream().anyMatch(edge -> edge.getLeft() == 2 && edge.getRight() == 5));
    }

    private static void printWithLabels(List<Edge> result) {
        result.stream()
                .map(edge -> new String[] {
                        graph.label(edge.getLeft()),
                        graph.label(edge.getRight())
                }).map(a -> a[0] + "->" + a[1])
                .reduce((a, b) -> a + "\n" + b)
                .ifPresent(System.out::println);
    }
}