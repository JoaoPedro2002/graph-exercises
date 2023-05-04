package br.ufsc.graphs;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.GraphBuilder;
import br.ufsc.graphs.structures.search.BreadthFirstSearcher;
import br.ufsc.graphs.structures.search.FloydWarshallSearcher;
import br.ufsc.graphs.structures.search.HierholzerSearch;
import br.ufsc.graphs.structures.storage.GraphStorage;
import br.ufsc.graphs.utils.FileUtils;

import java.sql.Time;

public class Main {
    public static void main(String[] args) {
        String file = FileUtils.getCompletePathFromResources("instancias/caminho_minimo/fln_pequena.net");
        GraphBuilder builder = new GraphBuilder();
        Graph graph = builder.withImplementation(GraphStorage.Implementation.MIXED).withWeightedEdges(true).withFile(file).build();
//        graph.prettyPrint();
        for (int i = 0; i < 10000; i++) {
            FloydWarshallSearcher.search(graph);
        }
//        file = FileUtils.getCompletePathFromResources("instancias/ciclo_euleriano/ContemCicloEuleriano.net");
//        graph = builder.withFile(file).build();
//        HierholzerSearch.logSearch(graph);
    }
}