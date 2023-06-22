package br.ufsc.graphs;

import br.ufsc.graphs.structures.BiPartedGraph;
import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.GraphBuilder;
import br.ufsc.graphs.structures.storage.GraphStorage.Implementation;
import br.ufsc.graphs.utils.FileUtils;

public class TestHelper {
    public static Graph createGraph(boolean weighted, Implementation implementation, String filePath) {
        String fullPath = FileUtils.getCompletePathFromResources(filePath);
        return new GraphBuilder()
                .withWeightedEdges(weighted)
                .withImplementation(implementation)
                .withFile(fullPath)
                .build();
    }

    public static BiPartedGraph createBiPartedGraph(boolean weighted, Implementation implementation,
                                    String filePath, boolean biparted) {
        String fullPath = FileUtils.getCompletePathFromResources(filePath);
        return (BiPartedGraph) new GraphBuilder()
                .withWeightedEdges(weighted)
                .withImplementation(implementation)
                .withFile(fullPath)
                .biparted(biparted)
                .build();
    }
}
