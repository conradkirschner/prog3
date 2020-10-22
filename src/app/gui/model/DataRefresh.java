package app.gui.model;


import app.cli.CliManager;
import app.gui.Window;
import app.user.entity.User;
import app.user.events.CreateUserEvent;
import app.user.events.DeleteUserEvent;
import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.events.DeleteItemEvent;
import app.warehouse.events.GetItemEvent;
import app.warehouse.events.StoreItemEvent;
import app.warehouse.events.UpdateItemEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.io.BufferedInputStream;
import java.io.PrintStream;
import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class DataRefresh implements Subscriber {

    private boolean active = true;

    @Inject
    EventHandler eventHandler;

    @Inject
    CliManager cliManager;

    @Inject
    Window window;

    PrintStream printStream;
    BufferedInputStream in;

    public DataRefresh(BufferedInputStream in, PrintStream printStream, Window window) {
        this.in = in;
        this.printStream = printStream;
        this.window = window;
    }


    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new CreateUserEvent(), 0));
        events.add(new SubscriberContainer(new DeleteUserEvent(), 0));
        events.add(new SubscriberContainer(new StoreItemEvent(), 0));
        events.add(new SubscriberContainer(new DeleteItemEvent(), 0));
        events.add(new SubscriberContainer(new UpdateItemEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        //user
        if (window.tableUserController != null) {
            window.tableUserController.removeUser();
            GetUserEvent getUser = (GetUserEvent) eventHandler.push(new GetUserEvent());
            ArrayList<app.user.entity.User> users = getUser.getUsers();

            for(User item : users){
                window.tableUserController.addUser(item.getName());
            }
        }


        // Items
        if (window.tableWarenController != null) {
            window.tableWarenController.removeItem();


            GetItemEvent getItem = (GetItemEvent) eventHandler.push(new GetItemEvent());
            ArrayList<Item> items = getItem.getItems();
            if (items == null) {
                return event;
            }
            for(Item item : items){
                window.tableWarenController.addItem(item);
            }
        }
        return event;
    }

    @Override
    public String getName() {
        return "gui:DataRefresh";
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
