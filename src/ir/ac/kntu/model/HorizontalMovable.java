package ir.ac.kntu.model;

public interface HorizontalMovable {
    void moveForward();

    default void moveBackward() {
        throw new UnsupportedOperationException();
    }
}
