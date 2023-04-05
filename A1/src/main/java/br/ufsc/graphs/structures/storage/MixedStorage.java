package br.ufsc.graphs.structures.storage;

import java.util.Collection;

public class MixedStorage implements GraphStorage {
    ListStorage listStorage;
    GraphStorage matrixStorage;

    public MixedStorage(boolean directional, boolean weighted) {
        listStorage = new ListStorage(directional);
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
    public int vertices() {
        return matrixStorage.vertices();
    }

    @Override
    public int edges() {
        return matrixStorage.edges();
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
}
