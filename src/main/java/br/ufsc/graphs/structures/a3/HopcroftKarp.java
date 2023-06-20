package br.ufsc.graphs.structures.a3;

import br.ufsc.graphs.structures.BiPartedGraph;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class HopcroftKarp {
    public static Pair<Integer, int[]> maximumMatching(BiPartedGraph graph) {
        int[] distance = new int[graph.getVerticesQnt() + 1]; Arrays.fill(distance, Integer.MAX_VALUE);
        int[] matchings = new int[graph.getVerticesQnt()]; Arrays.fill(matchings, -1);
        // Tamanho do emparelhamento
        int matching = 0;
        while (bfs(graph, distance, matchings)) {
            for (int x : graph.getX()) {
                if (matchings[x] == -1 && dfs(graph, x, distance, matchings)) {
                    matching++;
                }
            }
        }
        return Pair.of(matching, matchings);
    }

    private static boolean dfs(BiPartedGraph graph, int x, int[] distance, int[] matchings) {
        if (x == -1) return true;
        for (int y : graph.bipartedNeighbours(x)) {
            if (distance[matchings[y]] == distance[x] + 1) {
                if (matchings[y] != -1 && dfs(graph, matchings[y], distance, matchings)) {
                    matchings[y] = x;
                    matchings[x] = y;
                    return true;
                }
            }
        }
        distance[x] = Integer.MAX_VALUE;
        return false;
    }

    private static boolean bfs(BiPartedGraph graph, int[] distance, int[] matchings) {
        Queue<Integer> queue = new LinkedList<>();
        for (int x : graph.getX()) {
            if (matchings[x] == -1) {
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
                    // TODO matchings[y] == -1
                    if (distance[matchings[y]] == Integer.MAX_VALUE) {
                        distance[matchings[y]] = distance[x] + 1;
                        queue.add(matchings[y]);
                    }
                }
            }
        }
        return distance[graph.getVerticesQnt()] != Integer.MAX_VALUE;
    }

}
