package ir.ac.kntu;

import ir.ac.kntu.controller.Controller;
import ir.ac.kntu.model.Motorcycle;
import ir.ac.kntu.model.NormalCar;
import ir.ac.kntu.model.Truck;
import ir.ac.kntu.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
       Controller.init(new View(primaryStage));

       Controller.controller.getWorld().addVehicleAndRun(new NormalCar());
       Controller.controller.getWorld().addVehicleAndRun(new Motorcycle());
       Controller.controller.getWorld().addVehicleAndRun(new Truck());
       // Controller.init(new View(primaryStage));
        /*Controller.getInstance(new View(primaryStage));
        Controller.getInstance(new View(primaryStage)).getWorld().addVehicleAndRun(new NormalCar());*/
      /*  Controller.getInstance(new View(primaryStage)).getWorld()
        Controller.getInstance(new View(primaryStage)).getWorld().addVehicle(new SmallCar());
        Controller.getInstance(new View(primaryStage)).getWorld().addVehicle(new Truck());
        Controller.getInstance(new View(primaryStage)).getWorld().addVehicle(new Motorcycle());*/







    }
}
