package br.ufsc.graphs.structures;

import br.ufsc.graphs.structures.storage.GraphStorage;

import java.util.Objects;

public class GraphBuilder {

    private String filePath;
    private boolean weighted = false;
    private boolean  directed = false;

    private GraphStorage.Implementation implementation = GraphStorage.Implementation.MATRIX;

    public GraphBuilder withFile(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public GraphBuilder withWeightedEdges(boolean weighted) {
        this.weighted = weighted;
        return this;
    }

    public GraphBuilder withDirectionalEdges(boolean directed) {
        this.directed = directed;
        return this;
    }

    public GraphBuilder withImplementation(GraphStorage.Implementation implementation) {
        this.implementation = implementation;
        return this;
    }

    public Graph build() {
        // TODO error message
        if (Objects.isNull(filePath)) {
            throw new IllegalStateException();
        }

        GraphStorage storage = GraphStorage.getNewInstance(weighted, directed, implementation);
        Graph graph = weighted ? new WeightedGraphImp(storage) : new GraphImp(storage);
        graph.read(filePath);
        return graph;
    }
}
