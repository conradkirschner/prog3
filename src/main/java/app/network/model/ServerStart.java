package app.network.model;



import app.network.NetworkManager;
import app.network.events.ServerStartEvent;
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
public class ServerStart implements Subscriber {

    @Inject
    NetworkManager networkManager;


    public ServerStart() {

    }

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new ServerStartEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        networkManager.runServer();

        return null;
    }
}
