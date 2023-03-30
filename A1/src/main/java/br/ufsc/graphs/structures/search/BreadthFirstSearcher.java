package br.ufsc.graphs.structures.search;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.GraphImp;

import java.util.*;

public class BreadthFirstSearcher implements GraphSearcher {

    public static void search(Graph graph) {
        boolean[] visitArray = new boolean[graph.getVerticesQnt()];
        Arrays.fill(visitArray, false);
        int distanceFromOrigin = 0;
        int predecessor = 0;
        int counter = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.println(counter++ + ": " + Arrays.toString(graph.neighbours(u).stream().map(graph::label).toArray()));
            for (int neighbor : graph.neighbours(u)) {
                if (!visitArray[neighbor]) {
                    visitArray[neighbor] = true;
                    distanceFromOrigin++;
                    predecessor = neighbor;
                    queue.add(neighbor);
                }
            }
        }

    }

}
