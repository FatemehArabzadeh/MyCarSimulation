package ir.ac.kntu.controller;






import ir.ac.kntu.model.SmallCar;
import ir.ac.kntu.model.World;
import ir.ac.kntu.view.View;

import java.io.IOException;

public final class Controller {
    public static Controller controller;
    private static boolean started = false;
    private World world;

    public Controller(View view) throws IOException {
        world = new World();
        world.addObserver(view);
        world.addVehicle(new SmallCar());

//        System.out.println(world.getMap()[0].length + " " + world.getMap().length);
        world.run();
//        new Thread(world).start();
    }

    public  World getWorld() {
        return world;
    }

    public static void init(View view) throws IOException {
        if (started) return;
        started = true;
        controller = new Controller(view);
    }
}
