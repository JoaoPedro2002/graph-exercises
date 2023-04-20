package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface GraphSearcher {
    static void log(int l, Graph g, int v) {
        if (l != 0) System.out.println(l + ": "
                + Arrays.toString(g.neighbours(v).toArray()).replaceAll("(\\[)|(\\])", ""));
        else System.out.println(l + ": " + v);
    }

    static void log(int level, List<Integer> found) {
        System.out.println(level + ": " + found.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));
    }
}
