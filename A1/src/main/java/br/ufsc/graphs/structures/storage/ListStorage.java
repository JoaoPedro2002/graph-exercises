package br.ufsc.graphs.structures.storage;

import java.util.HashMap;
import java.util.Map;

public class ListStorage implements GraphStorage {

    private Map<Integer, Number>[] adjacencyList;
    private final boolean directional;

    private int edges = 0;

    public ListStorage(boolean directional) {
        this.directional = directional;
    }

    @Override
    public void add(int vertex1, int vertex2, Number positionValue) {
        adjacencyList[vertex1].put(vertex2, positionValue);
        if (!directional) {
            adjacencyList[vertex2].put(vertex1, positionValue);
        }
        edges++;
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
        return adjacencyList[vertex1].get(vertex2);
    }

    @Override
    public int vertices() {
        return adjacencyList.length;
    }

    @Override
    public int edges() {
        return edges;
    }

    @Override
    public void normalize() {
        // Não aplicável
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
    public int[] neighbours(int v1) {
        return adjacencyList[v1].values().stream().mapToInt(Number::intValue).toArray();
    }
}
