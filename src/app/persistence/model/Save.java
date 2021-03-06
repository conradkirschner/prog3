package app.persistence.model;


import app.persistence.Persistence;
import app.persistence.events.SaveApplicationEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;
import famework.event.SubscriberContainer;
import famework.event.SubscriberContainerInterface;

import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class Save implements Subscriber {

    private boolean active = true;

    @Inject
    Persistence persistence;
    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new SaveApplicationEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof SaveApplicationEvent){
            SaveApplicationEvent saveEvent = (SaveApplicationEvent) event;
            if(saveEvent.getType() == null) return new SaveApplicationEvent(false);

            if (saveEvent.getType().equals("JOS")) {
                persistence.saveAsJOS();
                return new SaveApplicationEvent(true);
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "persistence:Save";
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
