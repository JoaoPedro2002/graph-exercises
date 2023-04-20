package br.ufsc.graphs.structures.util;

import so.dang.cool.Pair;

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
        } if (obj instanceof NonDirectionalEdge edge) {
            return vertices.equals(edge.vertices);
        }
        return false;
    }

    @Override
    public Pair<Integer, Integer> getVertices() {
        return new Pair<>(v1, v2);
    }

    @Override
    public int getOtherVertex(int v) {
        return v == v1 ? v2 : v1;
    }
}
