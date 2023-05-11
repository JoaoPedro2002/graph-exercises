package br.ufsc.graphs.utils;

import java.util.Objects;

public class FileUtils {
    public static String getCompletePathFromResources(String filePath) {
        return Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(filePath)).getFile();
    }
}
