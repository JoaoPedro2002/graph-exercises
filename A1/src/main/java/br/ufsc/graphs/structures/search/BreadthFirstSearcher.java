package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class BreadthFirstSearcher implements GraphSearcher {

    public static Pair<Integer, Integer> search(Graph graph, int initialV) {
        boolean[] visitArray = new boolean[graph.getVerticesQnt()];
        Queue<Integer> queue = new LinkedList<>(List.of(initialV));
        int distanceFromOrigin = 0;
        int predecessor = initialV;
        int level = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            GraphSearcher.log(level++, graph, u);     // TODO optional log
            for (int neighbor : graph.neighbours(u)) {
                if (!visitArray[neighbor]) {
                    visitArray[neighbor] = true;
                    distanceFromOrigin++;
                    predecessor = neighbor;
                    queue.add(neighbor);
                }
            }
        }
        return new ImmutablePair<>(distanceFromOrigin, predecessor);
    }
}
