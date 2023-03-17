package br.ufsc.graphs.structures.storage;

import br.ufsc.graphs.structures.Graph;
import br.ufsc.graphs.structures.WeightedGraphImp;

import java.util.Arrays;
import java.util.Objects;

public class MatrixStorage implements GraphStorage {
    private Number[][] matrix;
    private int edges = 0;

    private final Number nullValue;

    public MatrixStorage(boolean weighted) {
        if (weighted) {
            nullValue = WeightedGraphImp.NULL_VALUE;
        } else {
            nullValue = Graph.NULL_VALUE;
        }
    }

    @Override
    public void add(int vertex1, int vertex2, Number positionValue) {
        matrix[vertex1][vertex2] = positionValue;
        edges++;
    }

    @Override
    public void set(int size) {
        matrix = new Number[size][size];
    }

    @Override
    public Number get(int vertex1, int vertex2) {
        return matrix[vertex1][vertex2];
    }

    @Override
    public int vertices() {
        return matrix.length;
    }

    @Override
    public int edges() {
        return edges;
    }

    @Override
    public void normalize() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = Objects.requireNonNullElse(matrix[i][j], nullValue);
            }
        }
    }

    @Override
    public void prettyPrint(String[] labels) {
        System.out.printf("%20s", "");
        for (String label : labels) {
            System.out.printf("%20s", label);
        }
        System.out.println();
        for(int i=0; i< matrix.length; i++){
            System.out.printf("%20s", labels[i]);
            for(int j=0; j< matrix.length; j++){
                System.out.printf("%20s", matrix[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public int degree(int v) {
        int count = 0;
        for (int i = 0; i < vertices(); i++) {
            if (!get(v, i).equals(nullValue)) count++;
        }
        return count;    }

    @Override
    public int[] neighbours(int v) {
        int[] neighbours = new int[vertices() - 1];
        int len = 0;
        for (int i = 0; i < vertices(); i++) {
            if (!get(v, i).equals(nullValue)) {
                neighbours[len] = i;
                len++;
            }
        }
        return Arrays.copyOf(neighbours, len);
    }
}
