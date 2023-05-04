package br.ufsc.graphs;

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
}
