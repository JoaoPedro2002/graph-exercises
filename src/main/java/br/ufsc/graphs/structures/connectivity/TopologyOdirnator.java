package br.ufsc.graphs.structures.connectivity;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.GraphBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TopologyOdirnator {

    public static List<Integer> order(Graph graph) {
        if (!graph.isDirectional()) {
            throw new IllegalArgumentException("Graph must be directional");
        }

        boolean[] visited = new boolean[graph.getVerticesQnt()];
        List<Integer> times = new ArrayList<>(Collections.nCopies(graph.getVerticesQnt(), (int) Graph.NULL_WEIGHTED_VALUE));
        List<Integer> times2 = new ArrayList<>(Collections.nCopies(graph.getVerticesQnt(), (int) Graph.NULL_WEIGHTED_VALUE));
        LinkedList<Integer> order = new LinkedList<>();
        int currentTime = 0;

        for (int i = 0; i < graph.getVerticesQnt(); i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, times, times2, currentTime, order);
            }
        }
        return order;
    }

    private static void dfs(Graph graph,
                     int i,
                     boolean[] visited,
                     List<Integer> times,
                     List<Integer> times2,
                     int currentTime,
                     LinkedList<Integer> order) {

        visited[i] = true;
        currentTime++;
        times.set(i, currentTime);
        for (int j : graph.neighbours(i)) {
            if (!visited[j]) {
                dfs(graph, j, visited, times, times2, currentTime, order);
            }
        }
        currentTime++;
        times2.set(i, currentTime);
        order.addFirst(i);
    }

    public static void print(List<Integer> result, Graph originalGraph) {
        System.out.println(result.stream()
                .map(originalGraph::label)
                .collect(Collectors.joining(" -> ")));
    }
}
