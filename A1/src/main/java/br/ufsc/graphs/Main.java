package br.ufsc.graphs;

import br.ufsc.graphs.structures.GraphBuilder;
import br.ufsc.graphs.utils.FileUtils;

public class Main {
    public static void main(String[] args) {
        String file = FileUtils.getCompletePathFromResources("instancias/arvore_geradora_minima/agm_tiny.net");
        GraphBuilder builder = new GraphBuilder();

        builder.withFile(file).build().prettyPrint();

        builder.withWeightedEdges(true).build().prettyPrint();

        builder.withDirectionalEdges(true).build().prettyPrint();

        builder.withImplementation(GraphBuilder.Implementation.ADJACENCY_LIST).build().prettyPrint();

        builder.withDirectionalEdges(false).build().prettyPrint();

    }
}