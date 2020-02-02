package ir.ac.kntu.view;


import ir.ac.kntu.model.Observable;

public interface Observer<T extends Observable> {
    void update(T t);
}
