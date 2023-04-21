package br.ufsc.graphs;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.GraphBuilder;
import br.ufsc.graphs.structures.search.HierholzerSearch;
import br.ufsc.graphs.structures.storage.GraphStorage;
import br.ufsc.graphs.utils.FileUtils;

public class Main {
    public static void main(String[] args) {
        String file = FileUtils.getCompletePathFromResources("instancias/ciclo_euleriano/ContemCicloEuleriano.net");
        GraphBuilder builder = new GraphBuilder();
        Graph graph = builder.withImplementation(GraphStorage.Implementation.MIXED).withFile(file).build();
        graph.prettyPrint();
        HierholzerSearch.logSearch(graph);
    }
}