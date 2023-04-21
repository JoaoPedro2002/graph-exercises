package br.ufsc.graphs.structures.util;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class DirectionalEdge implements Edge {
    final int v1, v2;
    public DirectionalEdge(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public Pair<Integer, Integer> getVertices() {
        return new ImmutablePair<>(v1, v2);
    }

    @Override
    public int getOtherVertex(int v) {
        return v == v1 ? v2 : v1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectionalEdge edge = (DirectionalEdge) o;
        return this.v1 == edge.v1 && this.v2 == edge.v2;
    }
}
