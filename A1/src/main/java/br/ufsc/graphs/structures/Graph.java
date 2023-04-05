package br.ufsc.graphs.structures;

import java.util.Collection;
import java.util.regex.Pattern;

public interface Graph {

    /**
     * Padrão para identificar números no método {@link Graph#read(String)}
     */
    Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*\\.?[0-9]+");

    /**
     * Padrão para identificar rótulos no método {@link Graph#read(String)}
     */
    Pattern LABEL_PATTERN = Pattern.compile("\"([^\"]*)\"");

    String NON_CONFORMANT_FILE = "Arquivo não conforme com o formato de entrada";

    /**
     * Valor que representa a ausência de arestas entre vértices em um grafo não-ponderado
     */
    byte NULL_VALUE = 0;

    /**
     * Valor que representa a presença de arestas entre vértices em um grafo não-ponderado
     */
    byte PRESENT_VALUE = 1;

    int getVerticesQnt();

    int getEdgesQnt();

    int degree(int v);

    String label(int v);

    Collection<Integer> neighbours(int v);

    boolean hasEdge(int v1, int v2);

    void read(String file);

    void prettyPrint();

    double weight(int v1, int v2);
}
