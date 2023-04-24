package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.WeightedGraphImp;
import br.ufsc.graphs.structures.storage.MatrixStorage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FloydWarshallSearcher {

//    public static final double POSITIVE_INFINITY = Infinity;

    public static int[][] search(Graph g) {
        int[][] matrix = buildInitialMatrix(g);

        // TODO

        return matrix;
    }

    private static int[][] buildInitialMatrix(Graph g) {
        return new int[][]{};
    }

}
