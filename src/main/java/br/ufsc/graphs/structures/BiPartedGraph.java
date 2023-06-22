package br.ufsc.graphs.structures;

import br.ufsc.graphs.structures.storage.GraphStorage;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class BiPartedGraph extends GraphImp {

    private final Set<Integer> x = new HashSet<>();
    private final Set<Integer> y = new HashSet<>();

    public BiPartedGraph(GraphStorage.Implementation implementation, double nullValue) {
        super(implementation, nullValue);
    }

    public Set<Integer> getX() {
        return x;
    }

    public Set<Integer> getY() {
        return y;
    }

    public Collection<Integer> bipartedNeighbours(int v) {
        Set<Integer> neighbours = new HashSet<>(neighbours(v));
        if (x.contains(v)) {
            neighbours.retainAll(y);
        } else {
            neighbours.retainAll(x);
        }
        return neighbours;
    }

    @Override
    public void read(String file) {
        super.read(file);
        bipartition();
    }

    public void bipartition() {
        boolean[] visited = new boolean[getVerticesQnt()];
        Arrays.fill(visited, false);
        visit(0, visited, true, x, y);
    }

    public void visit(int u, boolean[] visited, boolean isX, Set<Integer> x, Set<Integer> y) {
        if (visited[u]) return;
        visited[u] = true;
        if (isX) x.add(u);
        else y.add(u);
        for (int v : neighbours(u)) {
            if (!visited[v]) {
                visit(v, visited, !isX, x, y);
            }
        }
    }

}
