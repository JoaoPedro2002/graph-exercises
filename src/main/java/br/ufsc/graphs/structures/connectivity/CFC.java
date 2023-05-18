package br.ufsc.graphs.structures.connectivity;

import br.ufsc.graphs.structures.Graph;

import java.util.*;

public class CFC {

    public static List<List<Integer>> findFlorest(Graph graph) {
        List<List<Integer>> result = find(graph);
        log(result);
        return result;
    }
    private static List<List<Integer>> find(Graph graph) {
        if (!graph.isDirectional()) {
            throw new IllegalArgumentException("Graph must be directional");
        }
        Stack<Integer> least = DFS(graph);
        Graph transposed = graph.getTransposed();
        return DFSAdaptado(transposed, least);
    }

    private static Stack<Integer> DFS(Graph graph) {
        boolean[] visited = new boolean[graph.getVerticesQnt()];
        Stack<Integer> least = new Stack<>();

        for (int i = 0; i < graph.getVerticesQnt(); i++) {
            if (!visited[i]) {
                DFSVisit(graph, i, visited, least);
            }
        }

        return least;
    }

    private static void DFSVisit(Graph graph, int i, boolean[] visited, Stack<Integer> least) {
        visited[i] = true;
        for (int j : graph.neighbours(i)) {
            if (!visited[j]) {
                DFSVisit(graph, j, visited, least);
            }
        }
        least.push(i);
    }

    private static List<List<Integer>> DFSAdaptado(Graph graph, Stack<Integer> least) {
        boolean[] visited = new boolean[graph.getVerticesQnt()];

        List<List<Integer>> florest = new ArrayList<>();

        while (!least.isEmpty()) {
            Integer i = least.pop();
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                DFSVisitAdaptado(graph, i, visited, component);
                florest.add(component);
            }
        }

        return florest;
    }

    private static void DFSVisitAdaptado(Graph graph, int i, boolean[] visited, List<Integer> component) {
        visited[i] = true;
        for (int j : graph.neighbours(i)) {
            if (!visited[j]) {
                DFSVisitAdaptado(graph, j, visited, component);
            }
        }
        component.add(i);
    }

    private static void log(List<List<Integer>> result) {
        for (List<Integer> component : result) {
            StringJoiner joiner = new StringJoiner(",");
            component.forEach(v -> joiner.add((++v).toString()));
            System.out.println(joiner);
        }
    }
}
