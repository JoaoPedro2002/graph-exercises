package br.ufsc.graphs.structures;


import br.ufsc.graphs.structures.storage.GraphStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
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
        return storage.edges();
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
    public Collection<Integer> neighbours(int v) {
        return storage.neighbours(v);
    }

    @Override
    public boolean hasEdge(int v1, int v2) {
        return !storage.get(v1, v2).equals(NULL_VALUE);
    }

    @Override
    public void read(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            // define o tamanho da estrutura de dados e insere os vértices e seus rótulos
            readVertices(reader);
            // adiciona as arestas e seu peso caso se aplique
            readEdges(reader);
            // normaliza o valor dos dados caso se aplique
            storage.normalize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void readEdges(BufferedReader reader) throws IOException {
        if (!reader.readLine().contains("*edges")) throw new IllegalStateException(NON_CONFORMANT_FILE);
        reader.lines().forEach(this::addEdge);
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
}
