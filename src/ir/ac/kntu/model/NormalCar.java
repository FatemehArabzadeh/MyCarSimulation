package ir.ac.kntu.model;


import javafx.util.Pair;

public class NormalCar extends Vehicle {
    public NormalCar() {
        this.setLength(6d);
        this.setWidth(4d);
        this.setCenter(new Pair<>(4d,4d));
        this.setEnd(new Pair<>(17d,4d));
        this.setCoordinates();
    }

    public NormalCar(Pair<Double, Double> center) {
        super(center);
        this.setLength(6d);
        this.setWidth(4d);
        this.setCenter(center);
        this.setEnd(new Pair<>(3d,14d));

    }

    public NormalCar(Pair<Double, Double> center, Double length, Double width) {
        super(center, length, width);
        this.setLength(length);
        this.setWidth(width);
        this.setCenter(center);
        this.setEnd(new Pair<>(3d,14d));
        this.setCoordinates();

    }
}
