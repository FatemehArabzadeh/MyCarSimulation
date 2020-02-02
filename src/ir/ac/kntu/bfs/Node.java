package ir.ac.kntu.bfs;

import javafx.util.Pair;

import java.util.Objects;

public class Node {
    private Pair<Integer, Integer> coordinate;

    public Node(Integer x, Integer y) {
        this.coordinate = new Pair<>(x, y);
    }

    public Pair<Integer, Integer> getCoordinate() {
        return coordinate;
    }

    public Node(Pair<Integer, Integer> coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getX() {
        return coordinate.getKey();
    }

    public Integer getY() {
        return coordinate.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return getX().equals(node.getX()) && getY().equals(node.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
