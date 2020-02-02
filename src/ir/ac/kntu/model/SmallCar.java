package ir.ac.kntu.model;


import javafx.util.Pair;

public class SmallCar extends Vehicle {
    public SmallCar() {
        super();
        this.setLength(6d);
        this.setWidth(3d);
        this.setCenter(new Pair<>(5d,4d));
        this.setEnd(new Pair<>(16d,4d));
        this.setCoordinates();
    }

    public SmallCar(Pair<Double, Double> center) {
        super(center);
        this.setLength(6d);
        this.setWidth(3d);
        this.setCenter(center);
        this.setEnd(new Pair<>(3d,14d));

    }

    public SmallCar(Pair<Double, Double> center, Double length, Double width) {
        super(center, length, width);
        this.setLength(length);
        this.setWidth(width);
        this.setCenter(center);
        this.setEnd(new Pair<>(3d,14d));
        this.setCoordinates();
    }
}
