package br.ufsc.graphs.structures;

import br.ufsc.graphs.structures.storage.GraphStorage;

public class WeightedGraphImp extends GraphImp {

    /**
     * Valor que representa a ausência de arestas entre vértices em um grafo ponderado
     */
    public static double NULL_VALUE = Double.POSITIVE_INFINITY;

    public WeightedGraphImp(GraphStorage storage, boolean directional) {
        super(storage, directional);
    }

    @Override
    public boolean hasEdge(int v1, int v2) {
        return !storage.get(v1, v2).equals(NULL_VALUE);
    }
}
