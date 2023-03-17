package br.ufsc.graphs.structures;

import br.ufsc.graphs.structures.storage.GraphStorage;

public class WeightedGraphImp extends GraphImp {
    public static float NULL_VALUE = Float.MAX_VALUE;

    public WeightedGraphImp(GraphStorage storage) {
        super(storage);
    }

    @Override
    public boolean hasEdge(int v1, int v2) {
        return storage.get(v1, v2).equals(NULL_VALUE);
    }

    public Float weight(int v1, int v2) {
        return (Float) storage.get(v1, v2);
    }

    @Override
    public void read(String file) {
        read(file, NULL_VALUE);
    }
}
