package br.ufsc.graphs.structures.a3;

import br.ufsc.graphs.TestHelper;
import br.ufsc.graphs.structures.BiPartedGraph;
import br.ufsc.graphs.structures.storage.GraphStorage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HopcroftKarpTest {
    private static final String FILE = "instancias/emparelhamento_maximo/gr128_10.net";
    private static final BiPartedGraph GRAPH = TestHelper.createBiPartedGraph(
            false, GraphStorage.Implementation.MIXED, FILE, true
    );

    static {
        System.out.println("TESTING: " + HopcroftKarpTest.class.getName());
    }

    @Test
    public void testValid() {
        var matching = HopcroftKarp.maximumMatching(GRAPH);
        // os dois asserts abaixo garantem que todos os vértices apareçam no matching
        assertEquals(128, matching.getLeft());
        assertEquals(matching.getRight().length, lenResultToSet(matching.getRight()));
        // garante que todos os vértices de X estão em um matching com um vértice de Y
        assertTrue(assertCorrectPairing(matching.getRight()));

        System.out.println("Matches:" + matching.getLeft());
        System.out.println("Matches Array:" + Arrays.toString(matching.getRight()));
        log(matching.getRight());
    }

    /**
     * Cada vértice de X deve estar em um matching com um vértice de Y
     * @param matchings array de matchings
     * @return true se todos os vértices de X estão em um matching com um vértice de Y
     */
    private boolean assertCorrectPairing(int[] matchings) {
        for (int i = 0; i < matchings.length; i++) {
            final int matching = matchings[i];
            if (matchings[i] == GRAPH.getVerticesQnt()) return false;
            if (GRAPH.bipartedNeighbours(i).stream().noneMatch(v -> v == matching)) return false;
        }
        return true;
    }

    /**
     * Converte o array de matchings para um set, removendo duplicatas
     * @param matchings array de matchings
     * @return tamanho do set de matchings
     */
    private static int lenResultToSet(int[] matchings) {
        Set<Integer> set = new HashSet<>();
        for (int matching : matchings) {
            set.add(matching);
        }
        return set.size();
    }

    /**
     * Imprime os matchings
     * @param matchings array de matchings
     */
    private void log(int[] matchings) {
        boolean[] visited = new boolean[matchings.length]; Arrays.fill(visited, false);
        System.out.println("Matches paired:");
        for (int i = 0; i < matchings.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            visited[matchings[i]] = true;
            System.out.println(i + " -> " + matchings[i]);
        }
    }
}