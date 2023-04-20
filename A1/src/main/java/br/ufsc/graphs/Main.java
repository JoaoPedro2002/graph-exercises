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
        var r = HierholzerSearch.search(graph);
        System.out.println(r.getLeft());
        for (int i = 1; i < r.getRight().size(); i++) {
            System.out.println(graph.hasEdge(r.getRight().get(i - 1), r.getRight().get(i)));
        }
    }
}