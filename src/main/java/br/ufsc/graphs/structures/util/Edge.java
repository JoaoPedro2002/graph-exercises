package br.ufsc.graphs.structures.util;

import org.apache.commons.lang3.tuple.Pair;

public interface Edge {
    Pair<Integer, Integer> getVertices();

    int getOtherVertex(int v);

    default boolean contains(int v) {
        return getVertices().getLeft().equals(v) ||
                getVertices().getRight().equals(v);
    }
}
