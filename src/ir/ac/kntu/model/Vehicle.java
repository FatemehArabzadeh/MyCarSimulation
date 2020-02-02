package ir.ac.kntu.model;

import ir.ac.kntu.bfs.BreadthFirstSearch;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.Arrays;

public class Vehicle implements Turnable, HorizontalMovable, Serializable, Runnable {
    public final Double MAX_SPEED = 100d;
    private final Double TURNING_THETA = 2d;

    public Pair<Double, Double> getEnd() {
        return end;
    }

    //Coordinates
    private Pair<Double, Double> frontLeft, frontRight, backLeft, backRight,
            center ;
    //center of the car == start
    private Pair<Double, Double> end ;
    private Double length , width ;
    private Double acceleration = 1d, currentVelocity = 0d;
    private Double theta = 0d;

    public void setWorld(World world) {
        this.world = world;
    }

    public void setFrontLeft(Pair<Double, Double> frontLeft) {
        this.frontLeft = frontLeft;
    }

    public void setFrontRight(Pair<Double, Double> frontRight) {
        this.frontRight = frontRight;
    }

    public void setBackLeft(Pair<Double, Double> backLeft) {
        this.backLeft = backLeft;
    }

    public void setBackRight(Pair<Double, Double> backRight) {
        this.backRight = backRight;
    }

    public void setCenter(Pair<Double, Double> center) {
        this.center = center;
    }

    public void setEnd(Pair<Double, Double> end) {
        this.end = end;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    public void setCurrentVelocity(Double currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    public void setTheta(Double theta) {
        this.theta = theta;
    }

    private World world = null;

    public Vehicle() {

    }

    public Vehicle(Pair<Double, Double> center) {
        this(center, 6d, 4d);
    }

    public Vehicle(Pair<Double, Double> center, Double length, Double width) {
        this.length = length;
        this.width = width;
        this.center = center;

    }

    public static Pair<Double, Double> rotate(Double theta,
                                              Pair<Double, Double> init,
                                              Pair<Double, Double> centerOfRotation) {
        Double normalizedX = init.getKey() - centerOfRotation.getKey();
        Double normalizedY = init.getValue() - centerOfRotation.getValue();
        Pair<Double, Double> rotated = rotate(theta,
                new Pair<>(normalizedX, normalizedY));
        return new Pair<>(rotated.getKey() + centerOfRotation.getKey(),
                rotated.getValue() + centerOfRotation.getValue());
    }

    public static Pair<Double, Double> rotate(Double degree,
                                              Pair<Double, Double> pair) {
        double x = pair.getKey() * (Math.cos(Math.toRadians(degree))) - pair.getValue() * Math.sin(Math.toRadians(degree));
        double y = pair.getKey() * (Math.sin(Math.toRadians(degree))) + pair.getValue() * Math.cos(Math.toRadians(degree));
        return new Pair<>(x, y);
    }

    public Pair<Double, Double> getCenter() {
        return center;
    }

    public final void setCoordinates() {
        frontLeft = new Pair<>(center.getKey() + length / 2, center.getValue() - width / 2);
        frontRight = new Pair<>(center.getKey() + length / 2,
                center.getValue() + width / 2);
        backRight = new Pair<>(center.getKey() - length / 2,
                center.getValue() + width / 2);
        backLeft = new Pair<>(center.getKey() - length / 2,
                center.getValue() - width / 2);
    }

    public void setOnAcceleration() {
        acceleration = 1d;
    }

    public void setOnDecceletation() {
        acceleration = -1d;
    }

    public void calcVelocity() {
        if (currentVelocity > MAX_SPEED) {
            return;
        }
        currentVelocity += acceleration;
        //velocity in x direction
    }

    @Override
    public void turnRight() {
        theta += TURNING_THETA;
        Arrays.asList(backLeft, backRight, frontLeft, frontRight).
                forEach((Pair<Double, Double> coord) -> coord = rotate(TURNING_THETA, coord,
                        center));
    }

    @Override
    public void turnLeft() {
        theta -= TURNING_THETA;
        Arrays.asList(backLeft, backRight, frontLeft, frontRight).
                forEach((Pair<Double, Double> coord) -> coord = rotate(TURNING_THETA, coord,
                        center));
    }

    @Override
    public void moveForward() {
        Pair<Double, Double> velocityVector = new Pair<>(currentVelocity, 0d);
        Pair<Double, Double> rotatedVelocityVector = rotate(theta,
                velocityVector, center);
        center = new Pair<>(center.getKey() + rotatedVelocityVector.getKey(),
                center.getValue() + rotatedVelocityVector.getValue());
        backLeft = new Pair<>(backLeft.getKey() + rotatedVelocityVector.getKey(),
                backLeft.getValue() + rotatedVelocityVector.getValue());
        backRight = new Pair<>(backRight.getKey() + rotatedVelocityVector.getKey(),
                backRight.getValue() + rotatedVelocityVector.getValue());
        frontRight = new Pair<>(frontRight.getKey() + rotatedVelocityVector.getKey(),
                frontRight.getValue() + rotatedVelocityVector.getValue());
        frontLeft = new Pair<>(frontLeft.getKey() + rotatedVelocityVector.getKey(),
                frontLeft.getValue() + rotatedVelocityVector.getValue());
    }


    @Override
    public void run() {
        while (!reachedDestination()) {
            act();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void act() {
//        calcVelocity(); // simple form - no finding path
//        moveForward();
        System.out.println(center.getKey() + " " + center.getValue());
        calcVelocity();
        if (currentVelocity < 1) {
            return;
        }
        BreadthFirstSearch.prepareBFS(world.getMap(), 'R', center, end);
        Pair<Integer, Integer> next =
                BreadthFirstSearch.getInstance().getRoute().get((int) (currentVelocity - 1) >= BreadthFirstSearch.getInstance().getRoute().size() ? BreadthFirstSearch.getInstance().getRoute().size() - 1 : (int) (currentVelocity - 1)).getCoordinate();
        center = new Pair<>((double) next.getKey(), (double) next.getValue());
        setCoordinates();
        backLeft = rotate(theta, backLeft, center);
        backRight = rotate(theta, backRight, center);
        frontLeft = rotate(theta, frontLeft, center);
        frontRight = rotate(theta, frontRight, center);
        world.updateAllObservers();
    }

    public boolean reachedDestination() {
        if (calculateDistance(center, end) <= 1) {
            return true;
        }
        return false;
    }

    private double calculateDistance(Pair<Double, Double> center, Pair<Double,
            Double> end) {
        return Math.sqrt(Math.pow(center.getKey() - end.getKey(), 2) + Math.pow(center.getValue() - end.getValue(), 2));
    }

    public Pair<Double, Double> getFrontLeft() {
        return frontLeft;
    }

    public Pair<Double, Double> getFrontRight() {
        return frontRight;
    }

    public Double getLength() {
        return length;
    }

    public Double getWidth() {
        return width;
    }

    public Double getTheta() {
        return theta;
    }

    public Pair<Double, Double> getBackLeft() {
        return backLeft;
    }

    public Pair<Double, Double> getBackRight() {
        return backRight;
    }
}
