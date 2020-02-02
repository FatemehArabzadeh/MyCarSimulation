package ir.ac.kntu.model;


import ir.ac.kntu.view.Observer;

public interface Observable {

    void addObserver(Observer observer);

    void updateAllObservers();
}
