package br.ufsc.graphs.structures.storage;

import br.ufsc.graphs.structures.util.DirectionalEdge;
import br.ufsc.graphs.structures.util.Edge;
import br.ufsc.graphs.structures.util.NonDirectionalEdge;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MixedStorage implements GraphStorage {
    private final boolean directional;
    ListStorage listStorage;
    GraphStorage matrixStorage;
    Set<Edge> edges = new HashSet<>();

    public MixedStorage(boolean directional, boolean weighted) {
        listStorage = new ListStorage(directional);
        this.directional = directional;
        if (directional) {
            matrixStorage = new MatrixStorage(weighted);
        } else {
            matrixStorage = new ListStorage(weighted);
        }
    }

    @Override
    public void add(int vertex1, int vertex2, Number positionValue) {
        listStorage.add(vertex1, vertex2, positionValue);
        matrixStorage.add(vertex1, vertex2, positionValue);
        edges.add(directional ? new DirectionalEdge(vertex1, vertex2) : new NonDirectionalEdge(vertex1, vertex2));
    }

    @Override
    public void set(int size) {
        listStorage.set(size);
        matrixStorage.set(size);
    }

    @Override
    public Number get(int vertex1, int vertex2) {
        return matrixStorage.get(vertex1, vertex2);
    }

    @Override
    public int verticesQnt() {
        return matrixStorage.verticesQnt();
    }

    @Override
    public int edgesQnt() {
        return edges.size();
    }

    @Override
    public void prettyPrint(String[] labels) {
        matrixStorage.prettyPrint(labels);
    }

    @Override
    public int degree(int v) {
        return listStorage.degree(v);
    }

    @Override
    public Collection<Integer> neighbours(int v) {
        return listStorage.neighbours(v);
    }

    @Override
    public Set<Edge> getEdges() {
        return edges;
    }
}
