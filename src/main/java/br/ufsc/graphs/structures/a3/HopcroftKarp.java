package br.ufsc.graphs.structures.a3;

import br.ufsc.graphs.structures.BiPartedGraph;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class HopcroftKarp {
    public static Pair<Integer, int[]> maximumMatching(BiPartedGraph graph) {
        int[] distance = new int[graph.getVerticesQnt() + 1];
        int[] matchings = new int[graph.getVerticesQnt() + 1]; Arrays.fill(matchings, graph.getVerticesQnt());
        // Tamanho do emparelhamento
        int matching = 0;
        while (bfs(graph, distance, matchings)) {
            for (int x : graph.getX()) {
                if (matchings[x] == graph.getVerticesQnt() && dfs(graph, x, distance, matchings)) {
                    matching++;
                }
            }
        }
        return Pair.of(matching, Arrays.copyOf(matchings, graph.getVerticesQnt()));
    }

    private static boolean bfs(BiPartedGraph graph, int[] distance, int[] matchings) {
        Queue<Integer> queue = new LinkedList<>();
        for (int x : graph.getX()) {
            if (matchings[x] == graph.getVerticesQnt()) {
                distance[x] = 0;
                queue.add(x);
            } else {
                distance[x] = Integer.MAX_VALUE;
            }
        }
        distance[graph.getVerticesQnt()] = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (distance[x] < distance[graph.getVerticesQnt()]) {
                for (int y : graph.bipartedNeighbours(x)) {
                    if (distance[matchings[y]] == Integer.MAX_VALUE) {
                        distance[matchings[y]] = distance[x] + 1;
                        queue.add(matchings[y]);
                    }
                }
            }
        }
        return distance[graph.getVerticesQnt()] != Integer.MAX_VALUE;
    }

    private static boolean dfs(BiPartedGraph graph, int x, int[] distance, int[] matchings) {
        if (x == graph.getVerticesQnt()) return true;
        for (int y : graph.bipartedNeighbours(x)) {
            if (distance[matchings[y]] == distance[x] + 1) {
                if (dfs(graph, matchings[y], distance, matchings)) {
                    matchings[y] = x;
                    matchings[x] = y;
                    return true;
                }
            }
        }
        distance[x] = Integer.MAX_VALUE;
        return false;
    }

}
