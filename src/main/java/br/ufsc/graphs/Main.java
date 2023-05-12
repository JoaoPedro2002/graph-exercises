package br.ufsc.graphs;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.GraphBuilder;
import br.ufsc.graphs.structures.search.KruskalSearch;
import br.ufsc.graphs.structures.storage.GraphStorage;
import br.ufsc.graphs.utils.FileUtils;

import java.sql.Time;

public class Main {
    public static void main(String[] args) {
        String file = FileUtils.getCompletePathFromResources("instancias/arvore_geradora_minima/agm_tiny.net");
        GraphBuilder builder = new GraphBuilder()
                .withImplementation(GraphStorage.Implementation.MIXED)
                .withWeightedEdges(true)
                .withFile(file);
        Graph graph = builder.build();
        KruskalSearch.search(graph);
//        graph.prettyPrint();
//        for (int i = 0; i < 10000; i++) {
//            FloydWarshallSearcher.search(graph);
//        }
//        file = FileUtils.getCompletePathFromResources("instancias/ciclo_euleriano/ContemCicloEuleriano.net");
//        graph = builder.withFile(file).build();
//        HierholzerSearch.logSearch(graph);
    }
}