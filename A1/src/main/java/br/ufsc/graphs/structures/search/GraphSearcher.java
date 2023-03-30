package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;

import java.util.Arrays;

public interface GraphSearcher {
    static void log(int l, Graph g, int v) {
        if (l != 0) System.out.println(l + ": "
                + Arrays.toString(g.neighbours(v).toArray()).replaceAll("(\\[)|(\\])", ""));
        else System.out.println(l + ": " + v);
    }
}
