package br.ufsc.graphs;

import br.ufsc.graphs.structures.BiPartedGraph;
import br.ufsc.graphs.structures.GraphBuilder;
import br.ufsc.graphs.structures.a3.HopcroftKarp;
import br.ufsc.graphs.structures.storage.GraphStorage;
import br.ufsc.graphs.utils.FileUtils;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String file = FileUtils.getCompletePathFromResources("instancias/emparelhamento_maximo/gr128_10.net");
        GraphBuilder builder = new GraphBuilder()
                .withImplementation(GraphStorage.Implementation.MIXED)
                .biparted(true)
                .withFile(file);
        BiPartedGraph graph = (BiPartedGraph) builder.build();
        var matching = HopcroftKarp.maximumMatching(graph);
        System.out.println(matching.getLeft());
        System.out.println(Arrays.toString(matching.getRight()));
    }
}