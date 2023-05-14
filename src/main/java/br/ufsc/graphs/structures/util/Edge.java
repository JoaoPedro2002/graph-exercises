package br.ufsc.graphs.structures.util;

public interface Edge {
    int getOtherVertex(int v);

    int getLeft();
    int getRight();

    default boolean contains(int v) {
        return getLeft() == v || getRight() == v;
    }
}
