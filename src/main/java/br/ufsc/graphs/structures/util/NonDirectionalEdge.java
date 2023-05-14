package br.ufsc.graphs.structures.util;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import java.util.Set;

public class NonDirectionalEdge implements Edge {
    final Set<Integer> vertices;
    final int v1, v2;

    public NonDirectionalEdge(int v1, int v2) {
        this.vertices = Set.of(v1, v2);
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public int hashCode() {
        return vertices.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
          return true;
        }
        if (obj instanceof NonDirectionalEdge edge) {
            return vertices.equals(edge.vertices);
        }
        return false;
    }

    @Override
    public int getOtherVertex(int v) {
        return v == v1 ? v2 : v1;
    }

    @Override
    public int getLeft() {
        return v1;
    }

    @Override
    public int getRight() {
        return v2;
    }

    @Override
    public String toString() {
        return String.format("%d-%d", v1, v2);
    }
}
