package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.util.Edge;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;


public class KruskalSearch {
    private static class DisjointSet {
        private final int[] parent;
        private final int[] rank;

        public DisjointSet(int size) {
            this.parent = new int[size];
            this.rank = new int[size];
            for (int i = 0; i < size; i++) {
                this.parent[i] = i;
            }
        }

        public int find(int n) {
            if (parent[n] != n) {
                parent[n] = find(parent[n]);
            }
            return parent[n];
        }

        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);
            if (xRoot == yRoot) {
                return;
            }
            if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            } else if (rank[yRoot] < rank[xRoot]) {
                parent[yRoot] = xRoot;
            } else {
                parent[yRoot] = xRoot;
                rank[xRoot] = rank[xRoot] + 1;
            }
        }
    }

    public static List<Edge> search(Graph graph) {
        List<Edge> edges = new ArrayList<>();
        DisjointSet disjointSet = new DisjointSet(graph.getVerticesQnt());
        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        sortedEdges.sort(Comparator.comparingDouble(graph::weight));
        for (Edge edge : sortedEdges) {
            Pair<Integer, Integer> vertices = edge.getVertices();
            int v1 = vertices.getLeft();
            int v2 = vertices.getRight();
            if (disjointSet.find(v1) != disjointSet.find(v2)) {
                disjointSet.union(v1, v2);
                edges.add(edge);
            }
        }
        return edges;
    }
}
