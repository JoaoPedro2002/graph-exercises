package br.ufsc.graphs;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.GraphBuilder;
import br.ufsc.graphs.utils.FileUtils;

public class Main {
    public static void main(String[] args) {
        String file = FileUtils.getCompletePathFromResources("instancias/arvore_geradora_minima/agm_tiny.net");
        GraphBuilder builder = new GraphBuilder();
        Graph graph = builder.withFile(file).build();
        graph.prettyPrint();
    }
}