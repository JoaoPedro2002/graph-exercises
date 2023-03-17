package br.ufsc.graphs.structures.storage;

public interface GraphStorage {
    void add(int vertex1, int vertex2, Number positionValue);

    void set(int size);

    Number get(int vertex1, int vertex2);

    int vertices();

    int edges();

    void normalize();

    void prettyPrint(String[] labels);

    int degree(int v);

    int[] neighbours(int v);
}
