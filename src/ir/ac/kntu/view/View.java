package ir.ac.kntu.view;


import ir.ac.kntu.model.World;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class View implements Observer<World> {
    private Stage stage;
    private Scene scene;
    private Group root;

    public View(Stage primaryStage) {
        this.stage = primaryStage;
        root = new Group();
        scene = new Scene(root, 1024, 1024, Color.WHEAT);
        stage.setScene(scene);
        stage.show();
    }

    void draw(World world) { //24 * 20
        root.getChildren().clear();
        Group worldView = new Group();
        root.getChildren().add(worldView);
        for (int i = 0; i < world.getMap()[0].length; i++) {
            for (int j = 0; j < world.getMap().length; j++) {
                Rectangle rectangle = new Rectangle(i * 40, j * 40, 40, 40);
                if (world.getMap()[j][i] != 'R') {
                    rectangle.setFill(Color.DARKGREEN);
                } else {
                    rectangle.setFill(Color.LIGHTGRAY);
                }
                int finalI = i;
                int finalJ = j;
                world.getVehicles().forEach(x -> {
                    if (x.getEnd().getKey() ==
                            (finalI) && x.getEnd().getValue() == (finalJ)) {
                        rectangle.setFill(Color.BLUE);
                        rectangle.setArcWidth(10);
                        rectangle.setArcHeight(10);

                    }
                });
                worldView.getChildren().add(rectangle);
            }
        }
        world.getVehicles().forEach(c -> {
//            CarGraphics car = new CarGraphics(c.getFrontLeft(),
//                    c.getFrontRight(), c.getBackLeft(), c.getBackRight());
            VehicleGraphics vehicle = new VehicleGraphics(c);
            worldView.getChildren().add(vehicle);
        });
    }

    @Override
    public void update(World world) {
        AtomicBoolean run = new AtomicBoolean(false);
        Platform.runLater(() -> {
            if (run.get()) {
                System.err.println("WTF");
                return;
            }
            run.set(true);
            draw(world);
        });
    }
}
