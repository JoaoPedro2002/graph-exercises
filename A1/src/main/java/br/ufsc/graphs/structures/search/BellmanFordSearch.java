package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.util.Edge;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class BellmanFordSearch {

    static final String NEGATIVE_CYCLE = "Ciclo negativo encontrado!";

    public static Triple<Boolean, Double[], Integer[]> search(Graph graph){
        Set<Edge> undiscoveredEdges = graph.getEdges();
        int source = undiscoveredEdges.iterator().next().getVertices().getLeft();
        Triple<Boolean, Double[], Integer[]> result = search(graph, source);
        log(result);
        return result;
    }

    public static Triple<Boolean, Double[], Integer[]> search(Graph graph, int source){
        Double[] distances = new Double[graph.getVerticesQnt()];
        Integer[] predecessors = new Integer[graph.getVerticesQnt()];

        Arrays.fill(distances, Double.POSITIVE_INFINITY);

        distances[source] = (double) 0;

        for (int i = 0; i < graph.getVerticesQnt(); i++) {
            for (Edge edge : graph.getEdges()) {
                int u = edge.getVertices().getLeft();
                int v = edge.getVertices().getRight();
                double weight = graph.weight(u, v);
                if (distances[v] > distances[u] + weight) {
                    distances[v] = distances[u] + weight;
                    predecessors[v] = u;
                }
                if (!graph.isDirectional()) {
                    if (distances[u] > distances[v] + weight) {
                        distances[u] = distances[v] + weight;
                        predecessors[u] = v;
                    }
                }
            }
        }

        for (Edge edge : graph.getEdges()) {
            int u = edge.getVertices().getLeft();
            int v = edge.getVertices().getRight();
            double weight = graph.weight(u, v);
            if (distances[v] > distances[u] + weight) {
                return Triple.of(false, null, null);
            }
        }

        return Triple.of(true, distances, predecessors);
    }

    private static void log(Triple<Boolean, Double[], Integer[]> result) {
        if (result.getLeft()) {
            for (int i = 0; i < result.getMiddle().length; i++) {
                List<String> list = new ArrayList<>();
                Integer j = i;
                do {
                    int aux = j + 1;
                    list.add(0, Integer.toString(aux));
                    j = result.getRight()[j];
                } while (j != null);
                System.out.println((i + 1) + ": " + String.join( ", ", list) + "; d=" + result.getMiddle()[i]);
            }
        } else {
            System.out.println(NEGATIVE_CYCLE);
        }
    }
}
