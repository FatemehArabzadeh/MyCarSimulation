package ir.ac.kntu.model;

import javafx.util.Pair;

public class Motorcycle extends Vehicle {
    public Motorcycle() {
        this.setLength(4d);
        this.setWidth(2d);
        this.setCenter(new Pair<>(3d,4d));
        this.setEnd(new Pair<>(18d,4d));
        super.setCoordinates();
    }

    public Motorcycle(Pair<Double, Double> center) {
        super(center);
        this.setLength(4d);
        this.setWidth(2d);
        this.setCenter(new Pair<>(3d,4d));
        this.setEnd(new Pair<>(18d,14d));
    }

    public Motorcycle(Pair<Double, Double> center, Double length, Double width) {
        super(center, length, width);
        this.setLength(4d);
        this.setWidth(2d);
        this.setCenter(new Pair<>(3d,4d));
        this.setEnd(new Pair<>(18d,14d));
this.setCoordinates();

    }
}
