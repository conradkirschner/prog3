package app.cli.model;


import app.cli.CliManager;
import app.cli.events.GetInputEvent;
import app.cli.screens.*;
import app.cli.validators.NewCargoInput;
import app.cli.validators.NewUserInput;
import app.cli.validators.SearchInput;
import app.user.entity.User;
import app.user.events.CreateUserEvent;
import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.events.GetItemEvent;
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
    NewUserInput newUserInput;

    @Inject
    NewCargoInput newCargoInput;

    @Inject
    SearchInput searchInput;

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
           if (input.length() > 0 && input.charAt(0) == ':') {
               switch (input.charAt(1)) {
                   case 'c':
                       cliManager.setCurrentScreen(insertScreen);
                       break;
                   case 'd':
                       cliManager.setCurrentScreen(deleteScreen);
                       break;
                   case 'r':
                       cliManager.setCurrentScreen(overviewScreen);
                       break;
                   case 'u':
                       cliManager.setCurrentScreen(updateScreen);
                       break;
                   case 'p':
                       cliManager.setCurrentScreen(mainScreen);
                       break;
               }
           }
//           else if (input.equals("config")) {
//               cliManager.setCurrentScreen(configScreen);
//           }
       } else if(screen instanceof InsertScreen) {
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
               //
//               AllWarehouses warehouses = (AllWarehouses) this.eventStream.pushData("warehouse-manager:get-all", "");
//               for (Warehouse warehouse: warehouses.getWarehouses()) {
//                   Event event = this.eventStream.pushData("warehouse:store-item", warehouse.getWarehouseName() + "$$" + this.newCargoInput.getData());
//                   if (!(event instanceof Error)) { // no error means success
//                       error = null;
//                       break;
//                   } else {
//                       error = (Error) event;
//                   }
//               }
//               if (error != null ){
//                   this.insertScreen.setMessage(error.getMessage());
//                   this.views.get("input:content").run();
//                   return;
//               }
               /*
                * push success message then return to mainPage
                */

       } else if(screen instanceof OverviewScreen) {
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


        return null;
    }
}
