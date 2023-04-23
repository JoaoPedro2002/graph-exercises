package br.ufsc.graphs;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.GraphBuilder;
import br.ufsc.graphs.structures.search.BreadthFirstSearcher;
import br.ufsc.graphs.structures.search.HierholzerSearch;
import br.ufsc.graphs.structures.storage.GraphStorage;
import br.ufsc.graphs.utils.FileUtils;

public class Main {
    public static void main(String[] args) {
        String file = FileUtils.getCompletePathFromResources("instancias/arvore_geradora_minima/agm_tiny.net");
        GraphBuilder builder = new GraphBuilder();
        Graph graph = builder.withImplementation(GraphStorage.Implementation.MIXED).withFile(file).build();
        graph.prettyPrint();
        BreadthFirstSearcher.search(graph, 3);
        file = FileUtils.getCompletePathFromResources("instancias/ciclo_euleriano/ContemCicloEuleriano.net");
        graph = builder.withFile(file).build();
        HierholzerSearch.logSearch(graph);
    }
}