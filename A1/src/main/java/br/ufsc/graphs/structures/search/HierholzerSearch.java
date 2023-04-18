package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.exceptions.GraphException;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.util.Edge;
import br.ufsc.graphs.structures.util.NonDirectionalEdge;
import so.dang.cool.Pair;

import java.util.*;
import java.util.function.Predicate;

public class HierholzerSearch {

    static final Pair<Boolean, List<Integer>> NO_CYCLE = new Pair<>(false, null);

    public static Pair<Boolean, List<Integer>> search(Graph graph) {
        Set<Edge> undiscoveredEdges = new HashSet<>();
        for (int i = 0; i < graph.getVerticesQnt(); i++) {
            for (Integer neighbour : graph.neighbours(i)) {
                undiscoveredEdges.add(new NonDirectionalEdge(i, neighbour));
            }
        }
        int vertex;
        try {
            vertex = randomVertex(undiscoveredEdges, edge -> true);
        } catch (GraphException e) {
            return NO_CYCLE;
        }
        Pair<Boolean, List<Integer>> pair = searchEuclidianSubCycle(graph, vertex, undiscoveredEdges);
        if (!pair.getLeft()) return NO_CYCLE;
        if (!undiscoveredEdges.isEmpty()) {
            return NO_CYCLE;
        }
        return pair;
    }

    private static int randomVertex(Set<Edge> edges, Predicate<Edge> edgePredicate) throws GraphException {
        return randomEdge(edges, edgePredicate).getVertices().getLeft();
    }

    private static Edge randomEdge(Set<Edge> edges, Predicate<Edge> edgePredicate) throws GraphException {
        return edges.stream()
                .filter(edgePredicate)
                .findAny()
                .orElseThrow(GraphException::new);
    }

    private static Pair<Boolean, List<Integer>> searchEuclidianSubCycle(Graph graph, int vertex,
                                                                        Set<Edge> undiscoveredEdges) {
        List<Object> cycle = new ArrayList<>();
        cycle.add(vertex);
        int tmp = vertex;
        do {
            if (undiscoveredEdges.isEmpty()) {
                return NO_CYCLE;
            }
            final int finalVertex = vertex;
            Edge edge;
            try {
                edge = randomEdge(undiscoveredEdges,
                        edge1 -> edge1.contains(finalVertex));
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
            Pair<Boolean, List<Integer>> pair = searchEuclidianSubCycle(graph, x, undiscoveredEdges);
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
