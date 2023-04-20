package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class BreadthFirstSearcher implements GraphSearcher {

    public static Pair<Integer, List<Integer>> search2(Graph graph, int initialV) {
        Queue<Integer> unvisitedQueue = new LinkedList<>(List.of(initialV));
        List<Integer> visitTrack = new ArrayList<>();
        int distance = 0;
        int level = 0;

        while (!unvisitedQueue.isEmpty()) {
            GraphSearcher.log(level, unvisitedQueue.stream().toList());
            System.out.println(visitTrack);
            List<Integer> newVertices = unvisitedQueue.stream()
                    .map(graph::neighbours)
                    .flatMap(Collection::stream)
                    .distinct()
                    .filter(v -> !visitTrack.contains(v))
                    .toList();

            visitTrack.addAll(unvisitedQueue);
            unvisitedQueue.removeAll(unvisitedQueue);
            unvisitedQueue.addAll(newVertices);

            distance += newVertices.size();
            level++;
        }
        return new ImmutablePair<>(distance, visitTrack);
    }

    public static Pair<Integer, List<Integer>> search(Graph graph, int initialV) {
        boolean[] visitArray = new boolean[graph.getVerticesQnt()];
        List<Integer> predecessors = new ArrayList<>(initialV);
        Queue<Integer> queue = new LinkedList<>(List.of(initialV));
        int distanceFromOrigin = 0;
        int level = 0;

        while (!queue.isEmpty()) {
            GraphSearcher.log(level++, queue.stream().toList());     // TODO optional log
            int u = queue.poll();
            for (int neighbor : graph.neighbours(u)) {
                if (!visitArray[neighbor]) {
                    visitArray[neighbor] = true;
                    distanceFromOrigin++;
                    predecessors.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return new ImmutablePair<>(distanceFromOrigin, predecessors);
    }
}
