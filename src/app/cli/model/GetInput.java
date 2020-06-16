package app.cli.model;


import app.cli.CliManager;
import app.cli.events.GetInputEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;

import java.io.*;
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
    public ArrayList<Event> getSubscribedEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new GetInputEvent(""));
        return events;
    }

    @Override
    public Event update(Event event) {
        BufferedReader buffer=new BufferedReader(new InputStreamReader(in));
        try {
            String line=buffer.readLine();
            return new GetInputEvent(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GetInputEvent("");
    }
}
