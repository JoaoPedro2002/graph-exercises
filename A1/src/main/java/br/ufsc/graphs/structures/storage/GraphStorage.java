package br.ufsc.graphs.structures.storage;

import java.util.Collection;

public interface GraphStorage {
    enum Implementation {
        MATRIX, ADJACENCY_LIST, MIXED;
    }

    void add(int vertex1, int vertex2, Number positionValue);

    void set(int size);

    Number get(int vertex1, int vertex2);

    int vertices();

    int edges();

    void prettyPrint(String[] labels);

    int degree(int v);

    Collection<Integer> neighbours(int v);

    /**
     * Cria uma novo instância de um GraphStorage com base nos parâmetros
     * enviados
     * @param weighted se o grafo é ponderado
     * @param directional se o grafo é direcional
     * @param implementation implementação do Storage
     * @return nova instância criada
     */
    static GraphStorage getNewInstance(boolean weighted, boolean directional,
                                       Implementation implementation) {
        switch (implementation) {
            case MATRIX -> {
                return directional ? new MatrixStorage(weighted) : new VectorStorage(weighted);
            }
            case ADJACENCY_LIST -> {
                return new ListStorage(directional);
            }
            case MIXED -> {
                return new MixedStorage(directional, weighted);
            }
            default -> throw new IllegalStateException();
        }
    }
}
