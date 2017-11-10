package model.observer_pattern;

import model.observer_pattern.Observer;

public interface Subject {

    //MODIFIES: this
    //EFFECTS: adds observer to list of observers
    public void addObserver(Observer o);

    //EFFECTS: notifies observers of state change
    public void notifyObservers();

}
