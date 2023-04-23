package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.WeightedGraphImp;
import br.ufsc.graphs.utils.SearchLogger;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class BreadthFirstSearcher implements GraphSearcher {

    public static Pair<List<Integer>, List<Integer>> search(Graph graph, int initialV) {
        SearchLogger logger = new SearchLogger(0);

        // configura todos os vertices
        boolean[] visitArray = new boolean[graph.getVerticesQnt()];
        List<Integer> distances = new ArrayList<>(Collections.nCopies(graph.getVerticesQnt(), (int) WeightedGraphImp.NULL_VALUE));
        List<Integer> predecessors = new ArrayList<>(List.of(initialV));

        // configura vertice de origem
        visitArray[initialV] = true;
        distances.set(initialV, 0);

        // preparando fila de visitas
        Queue<Integer> queue = new LinkedList<>();
        queue.add(initialV);

        logger.log(predecessors);

        // propagacao das visitas
        while (!queue.isEmpty()) {
            int unqueued = queue.remove();
            List<Integer> newVertices = graph.neighbours(unqueued).stream()
                    .filter(neighbor -> !visitArray[neighbor])
                    .peek(neighbor -> {
                        visitArray[neighbor] = true;
                        distances.set(neighbor, distances.get(unqueued) + 1);
                        predecessors.add(neighbor);
                        queue.add(neighbor);
                    }).toList();

            if (!newVertices.isEmpty()) logger.log(newVertices);
        }
        return new ImmutablePair<>(distances, predecessors);
    }

}
