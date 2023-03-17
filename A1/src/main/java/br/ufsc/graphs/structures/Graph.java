package br.ufsc.graphs.structures;

import java.util.regex.Pattern;

public interface Graph {

    Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*\\.?[0-9]+");

    Pattern LABEL_PATTERN = Pattern.compile("\"([^\"]*)\"");

    String NON_CONFORMANT_FILE = "Arquivo não conforme com o formato de entrada";

    byte NULL_VALUE = 0;
    byte PRESENT_VALUE = 1;

    int getVerticesQnt();

    int getEdgesQnt();

    int degree(int v);

    String label(int v);

    int[] neighbours(int v);

    boolean hasEdge(int v1, int v2);

    void read(String file);

    void prettyPrint();
}
