package app.cli.model;


import app.cli.CliManager;
import app.cli.events.GetInputEvent;
import app.cli.screens.*;
import app.cli.validators.*;
import app.persistence.events.SaveApplicationEvent;
import app.user.entity.User;
import app.user.events.CreateUserEvent;
import app.user.events.DeleteUserEvent;
import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.events.DeleteItemEvent;
import app.warehouse.events.GetItemEvent;
import app.warehouse.events.UpdateItemEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.util.ArrayList;

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
    CliManager cliManager;

    @Inject
    EventHandler eventHandler;

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
           }
       }
       else if(screen instanceof InsertScreen) {
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
               switch(this.searchInput.getType()) {
                   case "customer":
                       this.overviewScreen.setMode("customer");
                       GetUserEvent userList = (GetUserEvent) this.eventHandler.push(new GetUserEvent());
                       if (userList.getUsers() != null) {
                           ArrayList<User> customer = userList.getUsers();
                           this.overviewScreen.rows = new String[customer.size()][];
                           for(int i = 0; i < customer.size(); i++) {
                               this.overviewScreen.rows[i] = new String[3];
                               this.overviewScreen.rows[i][0] = customer.get(i).getName();
                               this.overviewScreen.rows[i][1] = String.valueOf(customer.get(i).getMaxDurationOfStorage());
                               this.overviewScreen.rows[i][2] = String.valueOf(customer.get(i).getMaxValue());
                           }
                       }
                       break;
                   case "cargo":
                       this.overviewScreen.setMode("cargo");
                       getItems = (GetItemEvent) this.eventHandler.push(new GetItemEvent(searchInput.getArg2()));

                       if (getItems.getItems() != null) {
                           ArrayList<Item> items = getItems.getItems();
                           this.overviewScreen.rows = new String[items.size()][];
                           for(int i = 0; i < items.size(); i++) {
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
                           for(int i = 0; i < items.size(); i++) {
                               this.overviewScreen.rows[i] = new String[7];
                               this.overviewScreen.rows[i][0] = items.get(i).getId();
                               this.overviewScreen.rows[i][1] = items.get(i).getHazards().toString();
                               this.overviewScreen.rows[i][2] = items.get(i).getValue().toString();
                               this.overviewScreen.rows[i][3] = items.get(i).getDurationOfStorage().toString();
                               this.overviewScreen.rows[i][4] = items.get(i).getOwner().getName();
                               this.overviewScreen.rows[i][5] = items.get(i).getLastInspectionDate().toString();
                               this.overviewScreen.rows[i][6] = items.get(i).type;
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
           if (screen instanceof MainScreen && input.equals("")) {
           cliManager.stop();
       }
       return null;
    }
}
