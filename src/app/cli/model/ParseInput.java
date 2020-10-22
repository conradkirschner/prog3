package app.cli.model;


import app.cli.CliManager;
import app.cli.events.GetInputEvent;
import app.cli.events.ToggleActiveStateEvent;
import app.cli.screens.*;
import app.cli.validators.*;
import app.persistence.events.SaveApplicationEvent;
import app.user.entity.User;
import app.user.events.CreateUserEvent;
import app.user.events.DeleteUserEvent;
import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.entity.Type;
import app.warehouse.events.*;
import app.warehouseDistributor.events.ActivateWarehouseEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@AutoloadSubscriber
public class ParseInput implements Subscriber {


    @Inject
    DeleteScreen deleteScreen;

    @Inject
    InsertScreen insertScreen;

    @Inject
    MainScreen mainScreen;

    @Inject
    OverviewScreen overviewScreen;

    @Inject
    UpdateScreen updateScreen;

    @Inject
    ConfigScreen configScreen;

    @Inject
    PersistenceScreen persistenceScreen;

    @Inject
    NewUserInput newUserInput;

    @Inject
    NewCargoInput newCargoInput;

    @Inject
    SearchInput searchInput;

    @Inject
    DeleteInput deleteInput;

    @Inject
    UpdateInput updateInput;

    @Inject
    PersistenceInput persistenceInput;
    @Inject
    ConfigInput configInput;


    @Inject
    CliManager cliManager;

    @Inject
    EventHandler eventHandler;

    private boolean active = true;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new GetInputEvent(), 100));
        return events;
    }

    @Override
    public Event update(Event event) {
        GetInputEvent getInputEvent = (GetInputEvent) event;
        String input = getInputEvent.getContent();
        Screen screen = cliManager.getCurrentScreen();
        if (screen instanceof MainScreen) {
            String[] inputs = input.split(":");
            if (inputs.length == 2) {
                if (inputs[1].equals("c")) {
                    cliManager.setCurrentScreen(insertScreen);
                }
                if (inputs[1].equals("d")) {
                    cliManager.setCurrentScreen(deleteScreen);
                }
                if (inputs[1].equals("r")) {
                    cliManager.setCurrentScreen(overviewScreen);
                }
                if (inputs[1].equals("u")) {
                    cliManager.setCurrentScreen(updateScreen);
                }
                if (inputs[1].equals("p")) {
                    cliManager.setCurrentScreen(persistenceScreen);
                }
                if (inputs[1].equals("config")) {
                    cliManager.setCurrentScreen(configScreen);
                }
            }
        }
        else if (screen instanceof InsertScreen) {
            String[] inputs = input.split(" ");
            if (this.newUserInput.isValid(inputs)) {
                this.eventHandler.push(new CreateUserEvent(this.newUserInput.get()));
                return null;
            }

            if (this.newCargoInput.isValid(inputs)) {
                Error error = null;
                return new GetInputEvent(((GetInputEvent) event).getContent(), true);
            }
            return null;

        }
        else if (screen instanceof OverviewScreen) {
            String[] inputs = input.split(" ");
            if (this.searchInput.isValid(inputs)) {
                this.overviewScreen.rows = null;
                GetItemEvent getItems;
                switch (this.searchInput.getType()) {
                    case "customer":
                        this.overviewScreen.setMode("customer");
                        GetUserEvent userList = (GetUserEvent) this.eventHandler.push(new GetUserEvent());
                        if (userList.getUsers() != null) {
                            ArrayList<User> customer = userList.getUsers();
                            this.overviewScreen.rows = new String[customer.size()][];
                            for (int i = 0; i < customer.size(); i++) {
                                this.overviewScreen.rows[i] = new String[3];
                                this.overviewScreen.rows[i][0] = customer.get(i).getName();
                                this.overviewScreen.rows[i][1] = String.valueOf(customer.get(i).getMaxDurationOfStorage());
                                this.overviewScreen.rows[i][2] = String.valueOf(customer.get(i).getMaxValue());
                            }
                        }
                        break;
                    case "cargo":
                        this.overviewScreen.setMode("cargo");
                        getItems = (GetItemEvent) this.eventHandler.push(new GetItemEvent(new Type(searchInput.getArg2())));

                        if (getItems.getItems() != null) {
                            ArrayList<Item> items = getItems.getItems();
                            this.overviewScreen.rows = new String[items.size()][];
                            for (int i = 0; i < items.size(); i++) {
                                this.overviewScreen.rows[i] = new String[1];
                                this.overviewScreen.rows[i][0] = items.get(i).getId();
                            }
                        }
                        break;
                    case "hazard":
                        getItems = (GetItemEvent) this.eventHandler.push(new GetItemEvent(searchInput.getHazardFilter()));
                        this.overviewScreen.setMode("hazard");

                        if (getItems.getItems() != null) {
                            ArrayList<Item> items = getItems.getItems();
                            this.overviewScreen.rows = new String[items.size()][];
                            for (int i = 0; i < items.size(); i++) {
                                this.overviewScreen.rows[i] = new String[7];
                                this.overviewScreen.rows[i][0] = items.get(i).getId();
                                this.overviewScreen.rows[i][1] = items.get(i).getHazards().toString();
                                this.overviewScreen.rows[i][2] = items.get(i).getValue().toString();
                                this.overviewScreen.rows[i][3] = items.get(i).getDurationOfStorage().toString();
                                this.overviewScreen.rows[i][4] = items.get(i).getOwner().getName();
                                this.overviewScreen.rows[i][5] = items.get(i).getLastInspectionDate().toString();
                                this.overviewScreen.rows[i][6] = items.get(i).getType();
                            }
                        }
                        break;
                }

                //this.views.get("overview:" + this.searchInput.getType().split(":")[0]).run();

                return null;
            } else {
                this.cliManager.setFlashMessage("Command not found");
                this.cliManager.setPreviousScreen();
            }
        }
        else if (screen instanceof DeleteScreen) {
            String[] inputs = input.split(" ");
            if (this.deleteInput.isValid(inputs)) {
                String username = this.deleteInput.getUsername();
                String position = this.deleteInput.getPosition();
                if (username != null) {
                    DeleteUserEvent userDelete = (DeleteUserEvent) eventHandler.push(new DeleteUserEvent(username));
                    if (userDelete.getSuccess()) {
                        this.cliManager.setFlashMessage("User \"" + username + "\" erfolgreich gelöscht");
                        this.cliManager.setPreviousScreen();
                    }
                    return null;
                }
                DeleteItemEvent itemDelete = (DeleteItemEvent) eventHandler.push(new DeleteItemEvent(position));

                if (itemDelete.getSuccess()) {
                    this.cliManager.setFlashMessage("Item \"" + position + "\" erfolgreich gelöscht");
                    this.cliManager.setPreviousScreen();
                    return null;
                }
                return null;
            } else {
                this.cliManager.setFlashMessage("Going back");
                this.cliManager.setPreviousScreen();
            }
        }
        else if (screen instanceof UpdateScreen) {
            String[] inputs = input.split(" ");
            if (this.updateInput.isValid(inputs)) {
                this.eventHandler.push(new UpdateItemEvent(this.updateInput.getId()));
            } else {
                this.cliManager.setFlashMessage("Going back");
                this.cliManager.setPreviousScreen();
            }
        }
        else if (screen instanceof PersistenceScreen) {
            String[] inputs = input.split(" ");
            if (persistenceInput.isValid(inputs)) {
                SaveApplicationEvent saveApplication = (SaveApplicationEvent) this.eventHandler.push(new SaveApplicationEvent(this.persistenceInput.getType()));
                if (saveApplication.getStatus()) {
                    this.cliManager.setFlashMessage("Erfolgreich gespeichert");
                    this.cliManager.setPreviousScreen();
                } else {
                    this.cliManager.setFlashMessage("Speichern gescheitert!");
                }

            } else {
                this.cliManager.setFlashMessage("Going back");
                this.cliManager.setPreviousScreen();
            }
        }
        else if (screen instanceof ConfigScreen) {
            if (configInput.isValid(input)) {

                if (configInput.getType().equals("storage")) {
                    ActivateWarehouseEvent clearActiveWarehouses =
                            (ActivateWarehouseEvent) this.eventHandler.push(
                                    new ActivateWarehouseEvent(new ArrayList<>())
                            );
                    for (String name : configInput.getStorageNames()) {
                        CreateWarehouseEvent createWarehouseEvent = (CreateWarehouseEvent) this.eventHandler.push(new CreateWarehouseEvent(name));
                    }
                    this.cliManager.setFlashMessage("Storage befehl ausgeführt: erstellt und einsatzbereit (aktiviert)");
                    this.cliManager.setPreviousScreen();
                }
                if (configInput.getType().equals("deactivate")) {
                    String[] inputs = input.split(" ");
                    String[] deactivateSubscriber = Arrays.copyOfRange(inputs, 1, inputs.length);
                    eventHandler.push(new ToggleActiveStateEvent(false, deactivateSubscriber));

                    this.cliManager.setFlashMessage("Event deaktiviert");
                    this.cliManager.setPreviousScreen();
                }
                if (configInput.getType().equals("activate")) {
                    String[] inputs = input.split(" ");
                    String[] activateSubscriber = Arrays.copyOfRange(inputs, 1, inputs.length);
                    eventHandler.push(new ToggleActiveStateEvent(true, activateSubscriber));

                    this.cliManager.setFlashMessage("Event Aktiviert");
                    this.cliManager.setPreviousScreen();
                }
                if (configInput.getType().equals("place")) {
                    CreateStoragePlaceEvent createStoragePlaceEvent = (CreateStoragePlaceEvent) this.eventHandler.push(
                            new CreateStoragePlaceEvent(
                                    configInput.getSize(),
                                    configInput.getStorageNumber(),
                                    configInput.getStorageName()
                            )
                    );
                    this.cliManager.setFlashMessage("Erfolgreich Storage Platz angelegt");
                    this.cliManager.setPreviousScreen();
                }

            } else {
                this.cliManager.setFlashMessage("Going back");
                this.cliManager.setPreviousScreen();
            }
        }
        if (screen instanceof MainScreen && input.equals("")) {
            cliManager.stop();
        }
        return null;
    }

    @Override
    public String getName() {
        return "cli:ParseInput";
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }
}
