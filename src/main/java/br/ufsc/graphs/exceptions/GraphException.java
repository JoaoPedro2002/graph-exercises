package br.ufsc.graphs.exceptions;

public class GraphException extends Exception {
    public GraphException(String message) {
        super(message);
    }

    public GraphException(String message, Throwable cause) {
        super(message, cause);
    }

    public GraphException(Throwable cause) {
        super(cause);
    }

    public GraphException() {
        super();
    }
}
