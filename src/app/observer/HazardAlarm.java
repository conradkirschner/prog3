package app.observer;


import app.warehouse.events.StoreItemEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;
import storageContract.cargo.Hazard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintStream;
import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class HazardAlarm implements Subscriber, PropertyChangeListener {
    private HazardChecker hazardChecker;

    @Inject
    PrintStream printStream;

    @Inject
    private EventHandler eventHandler;

    public HazardAlarm(PrintStream printStream, EventHandler eventHandler) {
        this.printStream = printStream;
        this.eventHandler = eventHandler;
        hazardChecker = new HazardChecker();
        hazardChecker.addPropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        ArrayList<Hazard> hazards = hazardChecker.getHazards();
        if (0 != hazards.size()) {
            printStream.println("[Es wurden Gefahrenstoffe eingelagert]" + hazardChecker.getHazards().toString());
        }
    }

    private boolean active = true;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new StoreItemEvent(), 1000));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof StoreItemEvent){
            hazardChecker.setHazards(((StoreItemEvent) event).getItem().getHazards());
            }
        return event;
    }

    @Override
    public String getName() {
        return "observer:HazardAlarm";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
