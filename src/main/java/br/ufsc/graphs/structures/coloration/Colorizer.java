package br.ufsc.graphs.structures.coloration;

import br.ufsc.graphs.structures.Graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Colorizer {


    static public int minimumColoration(Graph g) {
        if (g.isDirectional()) {
            throw new IllegalArgumentException("Graph must be non-directional");
        }
        if (g.isWeighted()) {
            throw new IllegalArgumentException("Graph must be non-weighted");
        }
        int[] colors = new int[g.getVerticesQnt()];
        int[] colorCount = new int[g.getVerticesQnt()];
        int maxColor = 0;
        for (int i = 0; i < g.getVerticesQnt(); i++) {
            int color = 1;
            for (int j = 0; j < g.getVerticesQnt(); j++) {
                if (g.neighbours(i).contains(j) && colors[j] != 0) {
                    colorCount[colors[j]]++;
                }
            }
            for (int j = 1; j < g.getVerticesQnt(); j++) {
                if (colorCount[j] == 0) {
                    color = j;
                    break;
                }
            }
            colors[i] = color;
            maxColor = Math.max(maxColor, color);
            Arrays.fill(colorCount, 0);
        }
        return maxColor;
    }

}
