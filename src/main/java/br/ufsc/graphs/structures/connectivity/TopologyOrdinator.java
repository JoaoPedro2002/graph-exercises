package br.ufsc.graphs.structures.connectivity;

import br.ufsc.graphs.structures.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TopologyOrdinator{

    public static List<Integer> order(Graph graph) {
        if (!graph.isDirectional()) {
            throw new IllegalArgumentException("Graph must be directional");
        }

        boolean[] visited = new boolean[graph.getVerticesQnt()];
        LinkedList<Integer> order = new LinkedList<>();

        for (int i = 0; i < graph.getVerticesQnt(); i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, order);
            }
        }
        return order;
    }

    private static void dfs(Graph graph,
                     int i,
                     boolean[] visited,
                     LinkedList<Integer> order) {

        visited[i] = true;
        for (int j : graph.neighbours(i)) {
            if (!visited[j]) {
                dfs(graph, j, visited, order);
            }
        }
        order.addFirst(i);
    }

    public static void print(List<Integer> result, Graph originalGraph) {
        System.out.println(result.stream()
                .map(originalGraph::label)
                .collect(Collectors.joining(" -> ")));
    }
}
