package br.ufsc.graphs.structures.coloration;

import br.ufsc.graphs.structures.Graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Colorizer {

    static private List<BitSet> fTable = null;

    static public int colorize(Graph graph) {
        if (graph.isDirectional()) {
            throw new IllegalArgumentException("Graph must be non-directional");
        }
        if (graph.isWeighted()) {
            throw new IllegalArgumentException("Graph must be non-weighted");
        }
        buildFtable(graph.getVerticesQnt());
        int exp = (int) Math.pow(2, graph.getVerticesQnt());
        var X = new int[exp - 1];
        X[0] = 0;

        for (int Sindex = 1; Sindex < exp - 1; Sindex++) {
            List<Integer> S = getS(Sindex);
            int s = f(S);
            System.out.println(s);
            X[s] = Integer.MAX_VALUE;

            for (int I: getAnMaximalIndependent(graph)) {
                List<Integer> T = new ArrayList<>(S);
                T.removeAll(List.of(I));

                int i = f(T);
                if (X[i] + 1 < X[s]) {
                    X[s] = X[i] + 1;
                }
            }
        }
        return X[exp - 2];
    }

    static private ArrayList<Integer> getAnMaximalIndependent(Graph graph) {
        var blacklist = new ArrayList<Integer>();
        var result = new ArrayList<Integer>();
        for (int i = 0; i < graph.getVerticesQnt(); i++) {
            if (!blacklist.contains(i)) {
                result.add(i);
                blacklist.addAll(graph.neighbours(i));
            }
        }
        return result;
    }

    static private int f(List<Integer> S) {
        var b = new BitSet();
        S.forEach(b::set);
        return fTable.indexOf(b);
    }

    // FIXME pegar subconjunto
    static private List<Integer> getS(int i) {
        return fTable.get(i).stream()
                .boxed()
                .collect(Collectors.toList());
    }

    static private void buildFtable(int n) {
        fTable = IntStream.range(0, (int) Math.pow(2, n))
                .mapToObj(i -> BitSet.valueOf(new long[] { i }))
                .collect(Collectors.toList());
        System.out.println(fTable);
    }

}
