package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FloydWarshallSearcher {

    public static void searchAndPrint(Graph g) {
        printResult(search(g));
    }

    public static Number[][] search(Graph g) {
        Number[][] costs = buildInitialMatrix(g);
        int n = g.getVerticesQnt();

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Number altCost = addCosts(costs[i][k], costs[k][j]);
                    if (altCost.doubleValue() < costs[i][j].doubleValue()) {
                        costs[i][j] = altCost;
                    }
                }
            }
        }
        return costs;
    }

    private static Number addCosts(Number a, Number b) {
        if (a.doubleValue() == Double.POSITIVE_INFINITY || b.doubleValue() == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        return a.doubleValue() + b.doubleValue();
    }

    private static Number[][] buildInitialMatrix(Graph g) {
        int n = g.getVerticesQnt();
        Number[][] matrix = new Number[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Number cost = g.weight(i, j);
                matrix[i][j] = i == j ? 0 : cost;
            }
        }
        return matrix;
    }

    public static void printResult(Number[][] m) {
        var format = new DecimalFormat("0.###");
        IntStream.range(0, m.length)
                .mapToObj(
                        index -> String.format("%d:%s", index+1,
                                    Arrays.stream(m[index])
                                        .map(format::format)
                                        .collect(Collectors.joining(",")))
                ).forEach(System.out::println);
    }

}
