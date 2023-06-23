package br.ufsc.graphs.structures.a3;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.util.Edge;

import java.util.*;

public class EdmondsKarp {

    public static double findMaximumFlow(Graph graph, int source, int target) {
        if (!graph.isDirectional()) {
            throw new IllegalArgumentException("Graph must be directional");
        }

        return edmondsKarp(graph, source, target);
    }

    private static double edmondsKarp(Graph graph, int source, int target) {
        double maxFlow = 0.0;

        List<Integer> path = new ArrayList<>();
        while (bfs(graph, source, target, path)) {
            double pathFlow = Double.MAX_VALUE;
            for (int i = path.size() - 1; i > 0; i--) {
                int u = path.get(i);
                int v = path.get(i - 1);
                pathFlow = Math.min(pathFlow, graph.weight(u, v));
            }

            maxFlow += pathFlow;

            for (int i = path.size() - 1; i > 0; i--) {
                int u = path.get(i);
                int v = path.get(i - 1);
                graph.setWeight(u, v, graph.weight(u, v) - pathFlow);
                graph.setWeight(v, u, graph.weight(v, u) + pathFlow);
            }

        }

        return maxFlow;
    }

    private static boolean bfs(Graph graph, int source, int target, List<Integer> path) {
        path.clear();
        int[] ancestor = new int[graph.getVerticesQnt()];
        boolean[] visited = new boolean[graph.getVerticesQnt()];
        visited[source] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int neighbour : graph.neighbours(u)) {
                if (!visited[neighbour] && graph.weight(u, neighbour) > 0) {
                    visited[neighbour] = true;
                    ancestor[neighbour] = u;
                    if (neighbour == target) {
                        for (int v = target; v != source; v = ancestor[v]) {
                            path.add(v);
                        }
                        return true;
                    }
                    queue.add(neighbour);
                }
            }
        }
        return false;
    }

    private static void log() {

    }
}
