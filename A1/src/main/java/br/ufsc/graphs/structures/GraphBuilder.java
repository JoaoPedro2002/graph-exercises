package br.ufsc.graphs.structures;

import br.ufsc.graphs.structures.storage.GraphStorage;
import br.ufsc.graphs.structures.storage.ListStorage;
import br.ufsc.graphs.structures.storage.MatrixStorage;
import br.ufsc.graphs.structures.storage.VectorStorage;
import com.sun.istack.internal.NotNull;

import java.util.Objects;

public class GraphBuilder {

    public enum Implementation {
        MATRIX, ADJACENCY_LIST, MIXED
    }

    private String filePath;
    private boolean weighted = false;
    private boolean  directed = false;

    private Implementation implementation = Implementation.MATRIX;

    public GraphBuilder withFile(@NotNull String filePath) {
        this.filePath = filePath;
        return this;
    }

    public GraphBuilder withWeightedEdges(@NotNull boolean weighted) {
        this.weighted = weighted;
        return this;
    }

    public GraphBuilder withDirectionalEdges(@NotNull boolean directed) {
        this.directed = directed;
        return this;
    }

    public GraphBuilder withImplementation(@NotNull Implementation implementation) {
        this.implementation = implementation;
        return this;
    }

    public Graph build() {
        // TODO error message
        if (Objects.isNull(filePath)) {
            throw new IllegalStateException();
        }

        GraphStorage storage = null;
        switch (implementation) {
            case MATRIX -> {
                if (directed) {
                    storage = new MatrixStorage(weighted);
                } else {
                    storage = new VectorStorage(weighted);
                }
            }
            case ADJACENCY_LIST -> storage = new ListStorage(directed);
            case MIXED -> storage = new MatrixStorage(weighted);
        }

        Graph graph;
        if (weighted) {
            graph = new WeightedGraphImp(storage);
        } else {
            graph = new GraphImp(storage);
        }

        graph.read(filePath);
        return graph;
    }
}
