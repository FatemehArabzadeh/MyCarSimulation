package ir.ac.kntu.bfs;

public class NoRouteException extends RuntimeException {
    public NoRouteException(String message) {
        super(message);
    }

    public NoRouteException() {
    }
}
