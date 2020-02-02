package ir.ac.kntu.model;


import javafx.util.Pair;

public class Truck extends Vehicle {
    public Truck() {
        this.setLength(9d);
        this.setWidth(6d);
        this.setCenter(new Pair<>(6d,4d));
        this.setEnd(new Pair<>(15d,4d));

        this.setCoordinates();
    }

    public Truck(Pair<Double, Double> center) {
        super(center);
        this.setLength(9d);
        this.setWidth(6d);
        this.setCenter(center);
        this.setEnd(new Pair<>(3d,14d));

    }

    public Truck(Pair<Double, Double> center, Double length, Double width) {
        super(center, length, width);
        this.setLength(length);
        this.setWidth(width);
        this.setCenter(center);
        this.setEnd(new Pair<>(3d,14d));
        this.setCoordinates();
    }
}
