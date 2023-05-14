package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.util.Edge;

import java.util.*;


public class KruskalSearch {
    private static class DisjointSetMerger {
        private final int[] parent;
        private final int[] rank;

        public DisjointSetMerger(int size) {
            this.parent = new int[size];
            this.rank = new int[size];
            for (int i = 0; i < size; i++) {
                this.parent[i] = i;
            }
        }

        public int findRoot(int n) {
            if (parent[n] != n) {
                parent[n] = findRoot(parent[n]);
            }
            return parent[n];
        }

        public void union(int x, int y) {
            int rootOfX = findRoot(x);
            int rootOfY = findRoot(y);
            if (rootOfX == rootOfY) return;
            switch (Integer.compare(rank[rootOfX], rank[rootOfY])) {
                case 1 -> parent[rootOfY] = rootOfX;
                case -1 -> parent[rootOfX] = rootOfY;
                default -> {
                    parent[rootOfY] = rootOfX;
                    rank[rootOfX] = rank[rootOfX] + 1;
                }
            }
        }
    }

    public static List<Edge> search(Graph graph) {
        List<Edge> edges = new ArrayList<>();
        DisjointSetMerger disjointSetMerger = new DisjointSetMerger(graph.getVerticesQnt());
        Queue<Edge> sortedEdges = new PriorityQueue<>(Comparator.comparingDouble(graph::weight));
        sortedEdges.addAll(graph.getEdges());
        while (!sortedEdges.isEmpty()) {
            Edge edge = sortedEdges.poll();
            int v1 = edge.getLeft();
            int v2 = edge.getRight();
            if (disjointSetMerger.findRoot(v1) != disjointSetMerger.findRoot(v2)) {
                disjointSetMerger.union(v1, v2);
                edges.add(edge);
            }
        }
        return edges;
    }
}
