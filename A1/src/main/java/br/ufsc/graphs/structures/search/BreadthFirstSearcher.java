package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.WeightedGraphImp;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class BreadthFirstSearcher implements GraphSearcher {

    public static Pair<List<Integer>, List<Integer>> search(Graph graph, int initialV) {
        boolean[] visitArray = new boolean[graph.getVerticesQnt()];
        List<Integer> distances = new ArrayList<>(Collections.nCopies(graph.getVerticesQnt(), (int) WeightedGraphImp.NULL_VALUE));
        List<Integer> predecessors = new ArrayList<>(List.of(initialV));

        visitArray[initialV] = true;
        distances.set(initialV, 0);

        Queue<Integer> queue = new LinkedList<>(List.of(initialV));
        int level = 0;

        while (!queue.isEmpty()) {
            log(level++, queue.stream().toList());
            int unqueued = queue.remove();
            for (int neighbor : graph.neighbours(unqueued)) {
                if (!visitArray[neighbor]) {
                    visitArray[neighbor] = true;
                    distances.set(neighbor, distances.get(unqueued) + 1);
                    predecessors.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return new ImmutablePair<>(distances, predecessors);
    }

    private static void log(int level, List<Integer> found) {
        System.out.println(level + ": " + found.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));
    }

}
