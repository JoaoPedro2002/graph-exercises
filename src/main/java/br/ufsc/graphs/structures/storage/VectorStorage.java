package br.ufsc.graphs.structures.storage;

import br.ufsc.graphs.structures.util.Edge;
import br.ufsc.graphs.structures.util.NonDirectionalEdge;

import java.util.*;

/**
 * https://stackoverflow.com/questions/3187957/how-to-store-a-symmetric-matrix
 */
public class VectorStorage implements GraphStorage {
    private Number[] vector;

    private int vertices;
    private int edges = 0;

    private final Number nullValue;

    public VectorStorage(double nullValue) {
        this.nullValue = nullValue;
    }

    @Override
    public void add(int vertex1, int vertex2, Number positionValue) {
        int position = matrixPosToVector(vertex1, vertex2);
        vector[position] = positionValue;
        edges++;
    }

    @Override
    public void set(int size) {
        int trueSize = size * (size + 1) / 2;
        this.vertices = size;
        vector = new Number[trueSize];
        Arrays.fill(vector, nullValue);
    }

    @Override
    public Number get(int vertex1, int vertex2) {
        return vector[matrixPosToVector(vertex1, vertex2)];
    }

    @Override
    public int verticesQnt() {
        return vertices;
    }

    @Override
    public int edgesQnt() {
        return edges;
    }

    @Override
    public void prettyPrint(String[] labels) {
        System.out.printf("%20s", "");
        for (String label : labels) {
            System.out.printf("%20s", label);
        }

        System.out.println();
        int k = 0;
        for (int i = vertices; i > 0; i--) {
            System.out.printf("%20s", labels[labels.length - i]);
            for (int len = vertices- i; len > 0; len--) {
                System.out.printf("%20s", "");
            }
            for (int len = i; len > 0; len--) {
                System.out.printf("%20s", vector[k]);
                k++;
            }
            System.out.println();
        }
    }

    @Override
    public int degree(int v) {
        int count = 0;
        for (int i = 0; i < verticesQnt(); i++) {
            if (!get(v, i).equals(nullValue)) count++;
        }
        return count;
    }

    @Override
    public Collection<Integer> neighbours(int v) {
        Set<Integer> neighbours = new HashSet<>(vertices - 1);
        for (int i = 0; i < verticesQnt(); i++) {
            if (!get(v, i).equals(nullValue)) {
                neighbours.add(i);
            }
        }
        return neighbours;
    }

    @Override
    public Set<Edge> getEdges() {
        Set<Edge> edges = new HashSet<>();
        for (int i = 0; i < vertices; i++) {
            for (Integer neighbour : neighbours(i)) {
                edges.add(new NonDirectionalEdge(i, neighbour));
            }
        }
        return edges;
    }

    private int matrixPosToVector(int i, int j) {
        if (i <= j) return i * vertices - (i - 1) * i / 2 + j - i;
        return j * vertices - (j - 1) * j / 2 + i - j;
    }

}
