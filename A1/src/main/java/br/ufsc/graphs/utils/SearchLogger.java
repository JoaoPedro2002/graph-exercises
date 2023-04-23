package br.ufsc.graphs.utils;

import java.util.List;
import java.util.stream.Collectors;

public class SearchLogger {

    int level;

    public SearchLogger(int initialLevel) {
        this.level = initialLevel;
    }

    public void log(List<Integer> found) {
        System.out.println(this.level + ": " + found.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));
    }

}
