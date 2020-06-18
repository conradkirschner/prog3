package app.persistence.model;


import app.persistence.Persistence;
import app.persistence.events.LoadApplicationEvent;
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
public class Load implements Subscriber {

    @Inject
    Persistence persistence;
    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new LoadApplicationEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof LoadApplicationEvent){
            LoadApplicationEvent loadApplication = (LoadApplicationEvent) event;
            if(loadApplication.getType() == null) return new LoadApplicationEvent(false);

            if (loadApplication.getType().equals("JOS")) {
                persistence.loadFromJOS();
                return new LoadApplicationEvent(true);
            }
            if (loadApplication.getType().equals("JBP")) {
                persistence.loadFromJBP();
                return new LoadApplicationEvent(true);
            }
        }
        return null;
    }


}
