package app.cli.model;


import app.cli.CliManager;
import app.cli.events.CliStartEvent;
import app.warehouse.events.CreateWarehouseEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.io.*;
import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class Run implements Subscriber {

    @Inject
    EventHandler eventHandler;

    @Inject
    CliManager cliManager;

    PrintStream printStream;
    BufferedInputStream in;

    public Run(BufferedInputStream in, PrintStream printStream) {
        this.in = in;
        this.printStream = printStream;
    }


    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new CliStartEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        CreateWarehouseEvent createWarehouse = (CreateWarehouseEvent) this.eventHandler.push(new CreateWarehouseEvent("own"));

        boolean protectedExit = true;
        do {
            cliManager.start();
            while (cliManager.shouldRun()) {
                cliManager.run();
            }
            printStream.println("Do you wanna exit, press y");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
            try {
                if(buffer.readLine().equals("y")) {
                    protectedExit = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (protectedExit);
        return null;
    }
}
