package br.ufsc.graphs.structures.storage;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.WeightedGraphImp;
import br.ufsc.graphs.structures.util.DirectionalEdge;
import br.ufsc.graphs.structures.util.Edge;
import br.ufsc.graphs.structures.util.NonDirectionalEdge;

import java.util.*;

public class ListStorage implements GraphStorage {

    private Map<Integer, Number>[] adjacencyList;
    private final boolean directional;
    private final Number nullValue;

    private int edgesQnt = 0;

    public ListStorage(boolean directional, boolean weighted) {
        this.directional = directional;
        this.nullValue = weighted ?
                WeightedGraphImp.NULL_VALUE : Graph.NULL_VALUE;
    }

    @Override
    public void add(int vertex1, int vertex2, Number positionValue) {
        adjacencyList[vertex1].put(vertex2, positionValue);
        if (!directional) {
            adjacencyList[vertex2].put(vertex1, positionValue);
        }
        edgesQnt++;
    }

    @Override
    public void set(int size) {
        adjacencyList = new Map[size];
        for (int i = 0; i < size; i++) {
            adjacencyList[i] = new HashMap<>(size - 1);
        }
    }

    @Override
    public Number get(int vertex1, int vertex2) {
        if (!adjacencyList[vertex1].containsKey(vertex2)) {
            return nullValue;
        }
        return adjacencyList[vertex1].get(vertex2);
    }

    @Override
    public int verticesQnt() {
        return adjacencyList.length;
    }

    @Override
    public int edgesQnt() {
        return edgesQnt;
    }

    @Override
    public void prettyPrint(String[] labels) {
        for (int i = 0; i < labels.length; i++) {
            System.out.println(labels[i] + " {");
            Map<Integer, Number> map = adjacencyList[i];
            map.keySet().forEach(key -> {
                System.out.printf("%10s", "");
                System.out.println(labels[key] + ": " + map.get(key));
            });
            System.out.println("}");
        }
    }

    @Override
    public int degree(int v1) {
        return adjacencyList[v1].size();
    }

    @Override
    public Collection<Integer> neighbours(int v1) {
        return adjacencyList[v1].keySet();
    }

    @Override
    public Set<Edge> getEdges() {
        Set<Edge> edges = new HashSet<>();
        for (int i = 0; i < adjacencyList.length; i++) {
            for (Integer neighbour : neighbours(i)) {
                edges.add(directional ? 
                        new DirectionalEdge(i, neighbour) : new NonDirectionalEdge(i, neighbour));
            }
        }
        return edges;
    }
}
