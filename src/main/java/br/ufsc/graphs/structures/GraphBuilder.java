package br.ufsc.graphs.structures;

import br.ufsc.graphs.structures.storage.GraphStorage;

import java.util.Objects;

public class GraphBuilder {

    private String filePath;
    private boolean weighted = false;

    private boolean biparted = false;

    private GraphStorage.Implementation implementation = GraphStorage.Implementation.MATRIX;

    public GraphBuilder withFile(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public GraphBuilder withWeightedEdges(boolean weighted) {
        this.weighted = weighted;
        return this;
    }

    public GraphBuilder withImplementation(GraphStorage.Implementation implementation) {
        this.implementation = implementation;
        return this;
    }

    public GraphBuilder biparted(boolean biparted) {
        this.biparted = biparted;
        return this;
    }

    public Graph build() {
        // TODO error message
        if (Objects.isNull(filePath)) {
            throw new IllegalStateException();
        }


        double nullValue = weighted ? Graph.NULL_WEIGHTED_VALUE : GraphImp.NULL_VALUE;
        Graph graph = biparted ? new BiPartedGraph(implementation, nullValue) : new GraphImp(implementation, nullValue);
        graph.read(filePath);
        return graph;
    }
}
