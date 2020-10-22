package app.observer;

import storageContract.cargo.Hazard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class HazardChecker {
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private ArrayList<Hazard> hazards = new ArrayList<>();

    public HazardChecker() {
    }

    public void setHazards(ArrayList<Hazard> newStorageLimit)
    {
        this.hazards = newStorageLimit;
        changes.firePropertyChange("hazardAlarm", hazards, null);
    }

    public ArrayList<Hazard> getHazards() {
        return hazards;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }
}