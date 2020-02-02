package ir.ac.kntu.view;

import ir.ac.kntu.model.Vehicle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class VehicleGraphics extends Group {

    public VehicleGraphics(Vehicle vehicle) {
        Rectangle rectangle = new Rectangle(vehicle.getCenter().getKey() * 41,
                vehicle.getCenter().getValue() * 41, vehicle.getLength() * 4,
                vehicle.getWidth() * 6);
        getChildren().add(rectangle);
        System.out.println("=================");;
        System.out.println(vehicle.getBackLeft().getKey() + " " + vehicle.getBackLeft().getValue());
        System.out.println(vehicle.getBackRight().getKey() + " " + vehicle.getBackRight().getValue());
        setRotate(vehicle.getTheta());
        rectangle.setFill(Color.RED);
    }

}
