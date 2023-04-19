package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.exceptions.GraphException;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.util.Edge;
import so.dang.cool.Pair;

import java.util.*;

public class HierholzerSearch {

    static final Pair<Boolean, List<Integer>> NO_CYCLE = new Pair<>(false, null);

    public static Pair<Boolean, List<Integer>> search(Graph graph) {
        Set<Edge> undiscoveredEdges = graph.getEdges();
        int vertex = undiscoveredEdges.iterator().next().getVertices().getLeft();
        Pair<Boolean, List<Integer>> pair = searchEuclidianSubCycle(vertex, undiscoveredEdges);
        if (!pair.getLeft()) return NO_CYCLE;
        if (!undiscoveredEdges.isEmpty()) {
            return NO_CYCLE;
        }
        return pair;
    }

    private static Edge randomEdge(Set<Edge> edges, int vertex) throws GraphException {
        return edges.stream()
                .filter(edge -> edge.contains(vertex))
                .findAny()
                .orElseThrow(GraphException::new);
    }

    private static Pair<Boolean, List<Integer>> searchEuclidianSubCycle(int vertex, Set<Edge> undiscoveredEdges) {
        List<Object> cycle = new ArrayList<>();
        cycle.add(vertex);
        int tmp = vertex;
        do {
            Edge edge;
            try {
                edge = randomEdge(undiscoveredEdges, vertex);
            } catch (GraphException e) {
                return NO_CYCLE;
            }
            undiscoveredEdges.remove(edge);
            vertex = edge.getOtherVertex(vertex);
            cycle.add(vertex);
        } while (vertex != tmp);

        for (int i = 0; i < cycle.size(); i++) {
            Integer x = (Integer) cycle.get(i);
            if (undiscoveredEdges.stream().noneMatch(edge -> edge.contains(x))) continue;
            Pair<Boolean, List<Integer>> pair = searchEuclidianSubCycle(x, undiscoveredEdges);
            if (!pair.getLeft()) return NO_CYCLE;
            cycle.set(i, pair.getRight());
        }

        List<Integer> flatCycle = new ArrayList<>();
        flattenCycle(flatCycle, cycle);

        return new Pair<>(true, flatCycle);
    }

    private static void flattenCycle(List<Integer> flatCycle, List<?> cycle) {
        for (Object o : cycle) {
            if (o instanceof List<?> list) {
                flattenCycle(flatCycle, list);
            } else if (o instanceof Integer integer) {
                flatCycle.add(integer);
            }
        }
    }
}
