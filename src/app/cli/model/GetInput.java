package app.cli.model;


import app.cli.CliManager;
import app.cli.events.GetInputEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;
import famework.event.SubscriberContainer;
import famework.event.SubscriberContainerInterface;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class GetInput implements Subscriber {

    @Inject
    CliManager cliManager;

    BufferedInputStream in;

    public GetInput(BufferedInputStream in) {
        this.in = in;
    }

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new GetInputEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        BufferedReader buffer=new BufferedReader(new InputStreamReader(in));
        try {
            return new GetInputEvent(buffer.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GetInputEvent();
    }
}
