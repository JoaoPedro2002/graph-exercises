package br.ufsc.graphs.structures;


import br.ufsc.graphs.structures.storage.GraphStorage;
import br.ufsc.graphs.structures.util.DirectionalEdge;
import br.ufsc.graphs.structures.util.Edge;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;

public class GraphImp implements Graph {
    private boolean directional;

    String[] labels;
    GraphStorage storage;
    final GraphStorage.Implementation implementation;
    final double nullValue;

    public GraphImp(GraphStorage.Implementation implementation, double nullValue) {
        this.implementation = implementation;
        this.nullValue = nullValue;
    }

    public GraphImp(GraphStorage.Implementation implementation, double nullValue, String[] labels, boolean directional, int vertices, List<Pair<Edge, Double>> transversedEdges) {
        this.implementation = implementation;
        this.nullValue = nullValue;
        this.labels = labels;
        this.directional = directional;
        setStorage(vertices);
        addEdge(transversedEdges);
    }

    @Override
    public int getVerticesQnt() {
        return storage.verticesQnt();
    }

    @Override
    public int getEdgesQnt() {
        return storage.edgesQnt();
    }

    @Override
    public Set<Edge> getEdges() {
        return new HashSet<>(storage.getEdges());
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
        return !storage.get(v1, v2).equals(nullValue);
    }

    /**
     * Cria os vértices e arestas do grafo com base em um arquivo
     * @param file caminho do arquivo
     * @throws IllegalStateException arquivo não conforme com o padrão
     */
    @Override
    public void read(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            // define o tamanho da estrutura de dados e insere os vértices e seus rótulos
            int vertices = readVertices(reader);
            // Define o armazenamento
            setStorage(reader, vertices);
            // adiciona as arestas e seu peso caso se aplique
            reader.lines().forEach(this::addEdge);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setStorage(BufferedReader reader, int vertices) throws IOException {
        String line = reader.readLine();
        if (line.contains("*edges")) this.directional = false;
        else if (line.contains("*arcs")) this.directional = true;
        else throw new IllegalStateException(NON_CONFORMANT_FILE);
        this.storage = GraphStorage.getNewInstance(nullValue, this.directional, implementation);
        this.storage.set(vertices);
    }

    private void setStorage(int vertices) {
        this.storage = GraphStorage.getNewInstance(nullValue, this.directional, implementation);
        this.storage.set(vertices);
    }

    private void addEdge(String line) {
        int vertex1, vertex2;
        Matcher matcher = NUMBER_PATTERN.matcher(line);
        vertex1 = Integer.parseInt(getIfPresent(matcher)) - 1;
        vertex2 = Integer.parseInt(getIfPresent(matcher)) - 1;
        Number weight = isWeighted() ?
                Double.parseDouble(getIfPresent(matcher)) : PRESENT_VALUE;
        storage.add(vertex1, vertex2, weight);
    }

    private void addEdge(List<Pair<Edge, Double>> transversedEdges) {
        for (Pair<Edge, Double> edge : transversedEdges) {
            storage.add(edge.getLeft().getLeft(), edge.getLeft().getRight(), edge.getRight());
        }
    }

    @Override
    public boolean isWeighted() {
        return nullValue == Graph.NULL_WEIGHTED_VALUE;
    }

    protected int readVertices(BufferedReader reader) throws IOException {
        String line;
        Matcher matcher;
        line = reader.readLine();
        matcher = NUMBER_PATTERN.matcher(line);
        int vertices = Integer.parseInt(getIfPresent(matcher));
        labels = new String[vertices];
        for (int i = 0; i < vertices; i++) {
            line = reader.readLine();
            line = line.replace(String.valueOf(i+1), "");
            labels[i] = line.replace("\"", "").trim();
        }
        return vertices;
    }

    @Override
    public void prettyPrint() {
        storage.prettyPrint(labels);
    }

    @Override
    public double weight(int v1, int v2) {
        return storage.get(v1, v2).doubleValue();
    }

    @Override
    public double weight(Edge edge) {
        return weight(edge.getLeft(), edge.getRight());
    }

    @Override
    public boolean isDirectional() {
        return directional;
    }

    /**
     * Procura o próximo String que segue determinado padrão e retorna.
     * @return o string encontrado
     * @throws IllegalStateException arquivo não conforme
     */
    private static String getIfPresent(Matcher matcher) {
        if (!matcher.find()) throw new IllegalStateException(NON_CONFORMANT_FILE);
        return matcher.group();
    }

    public Graph getTransposed() {
        List<Pair<Edge, Double>> transversedEdges = new ArrayList<>();
        for (Edge edge : getEdges()) {
            int left = edge.getLeft();
            int right = edge.getRight();
            Double weight = weight(left, right);
            DirectionalEdge directionalEdge = new DirectionalEdge(right, left);
            transversedEdges.add(Pair.of(directionalEdge, weight));
        }
        return new GraphImp(implementation, nullValue, labels, directional, getVerticesQnt(), transversedEdges);
    }
}
