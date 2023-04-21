package br.ufsc.graphs.structures;


import br.ufsc.graphs.structures.storage.GraphStorage;
import br.ufsc.graphs.structures.util.Edge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

public class GraphImp implements Graph {
    private final boolean directional;

    String[] labels;

    final GraphStorage storage;

    public GraphImp(GraphStorage storage, boolean directional) {
        this.storage = storage;
        this.directional = directional;
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
        return !storage.get(v1, v2).equals(NULL_VALUE);
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
            readVertices(reader);
            // adiciona as arestas e seu peso caso se aplique
            readEdges(reader);
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
        vertex1 = Integer.parseInt(getIfPresent(matcher)) - 1;
        vertex2 = Integer.parseInt(getIfPresent(matcher)) - 1;
        Number weight = this instanceof WeightedGraphImp ?
                Double.parseDouble(getIfPresent(matcher)) : PRESENT_VALUE;
        storage.add(vertex1, vertex2, weight);
    }

    protected void readVertices(BufferedReader reader) throws IOException {
        String line;
        Matcher matcher;
        line = reader.readLine();
        matcher = NUMBER_PATTERN.matcher(line);
        int vertices = Integer.parseInt(getIfPresent(matcher));
        storage.set(vertices);
        labels = new String[vertices];
        for (int i = 0; i < vertices; i++) {
            line = reader.readLine();
            matcher = LABEL_PATTERN.matcher(line);
            labels[i] = getIfPresent(matcher).replace("\"", "");
        }
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
}