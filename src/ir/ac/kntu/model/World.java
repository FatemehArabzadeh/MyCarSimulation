package ir.ac.kntu.model;




import java.io.IOException;
import java.util.*;
import ir.ac.kntu.view.Observer;
public class World implements Observable,Runnable {
    private Set<Observer<World>> observers = new HashSet<>();
    private char[][] map = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', 'C', ' ', ' ', 'C', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C', 'R', 'R', 'R', 'R', 'R', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
    };

    public World() throws IOException {
    }
    private Set<Vehicle> Vehicles = new HashSet<>();

    public void addVehicle(Vehicle vehicle) {
        Vehicles.add(vehicle);
    }

    public char[][] getMap() {
        return map;
    }

    public void addVehicleAndRun(Vehicle vehicle) {
        addVehicle(vehicle);
        run();
    }

    public void addVehicles(List<Vehicle> VehicleList) {
        VehicleList.forEach(c -> Vehicles.add(c));
    }

    public void addVehiclesAndRun(List<Vehicle> VehicleList) {
        addVehicles(VehicleList);
        run();
    }
    @Override
    public void run() {
        Vehicles.forEach(c -> c.setWorld(this));
        Vehicles.forEach(c -> new Thread(c, "Vehicle Thread").start());
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                updateAllObservers();
            }
        };
        timer.scheduleAtFixedRate(task, 10, 500);
    }
    public Set<Vehicle> getVehicles() {
        return Vehicles;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void updateAllObservers() {
        observers.forEach(o -> o.update(this));
    }
}
