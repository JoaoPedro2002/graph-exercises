package br.ufsc.graphs.structures;


import br.ufsc.graphs.structures.storage.GraphStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;

public class GraphImp implements Graph {
    protected String[] labels;

    final GraphStorage storage;

    public GraphImp(GraphStorage storage) {
        this.storage = storage;
    }
    @Override
    public int getVerticesQnt() {
        return storage.vertices();
    }

    @Override
    public int getEdgesQnt() {
        return storage.vertices();
    }

    @Override
    public int degree(int v) {
        return storage.degree(v);
    }

    @Override
    public String label(int v) {
        return labels[v];
    }

    @Override
    public int[] neighbours(int v) {
        return storage.neighbours(v);
    }

    @Override
    public boolean hasEdge(int v1, int v2) {
        return !storage.get(v1, v2).equals(NULL_VALUE);
    }

    @Override
    public void read(String file) {
        read(file, NULL_VALUE);
    }

    protected void read(String file, Number nullValue) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            readVertices(reader);
            readEdges(reader);
            storage.normalize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void readEdges(BufferedReader reader) throws IOException {
        if (!reader.readLine().contains("*edges")) throw new IllegalStateException(NON_CONFORMANT_FILE);
        reader.lines().parallel().forEach(this::addEdge);
    }

    private void addEdge(String line) {
        int vertex1, vertex2;
        Matcher matcher = NUMBER_PATTERN.matcher(line);
        matcher.find();
        vertex1 = Integer.parseInt(matcher.group()) - 1;
        matcher.find();
        vertex2 = Integer.parseInt(matcher.group()) - 1;
        if (this instanceof WeightedGraphImp) {
            matcher.find();
            float weight = Float.parseFloat(matcher.group());
            storage.add(vertex1, vertex2, weight);
        } else {
            storage.add(vertex1, vertex2, PRESENT_VALUE);
        }
    }

    protected void readVertices(BufferedReader reader) throws IOException {
        String line;
        Matcher matcher;
        line = reader.readLine();
        matcher = NUMBER_PATTERN.matcher(line);
        if (!matcher.find()) throw new IllegalStateException(NON_CONFORMANT_FILE);
        int vertices = Integer.parseInt(matcher.group());
        storage.set(vertices);
        labels = new String[vertices];
        for (int i = 0; i < vertices; i++) {
            line = reader.readLine();
            matcher = LABEL_PATTERN.matcher(line);
            if (!matcher.find()) throw new IllegalStateException(NON_CONFORMANT_FILE);
            labels[i] = matcher.group().replace("\"", "");
        }
    }

    @Override
    public void prettyPrint() {
        storage.prettyPrint(labels);
    }

    protected int[] neighbours(int v, Number nullValue) {
        int[] neighbours = new int[storage.vertices() - 1];
        int len = 0;
        for (int i = 0; i < storage.vertices(); i++) {
            if (!storage.get(v, i).equals(nullValue)) {
                neighbours[len] = i;
                len++;
            }
        }
        return Arrays.copyOf(neighbours, len);
    }

    protected int degree(int v, Number nullValue) {
        int count = 0;
        for (int i = 0; i < storage.vertices(); i++) {
            if (!storage.get(v, i).equals(nullValue)) count++;
        }
        return count;
    }
}
