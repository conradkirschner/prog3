package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import app.EventStream;
import app.events.Event;
import cli.events.CloseCliEvent;
import cli.screens.*;
import cli.validators.*;
import user.entity.Customer;
import user.events.UserListData;
import warehouse.entity.Item;
import warehouse.events.GetCargoData;
import warehouse.model.Warehouse;
import warehouseManager.entity.AllItems;
import warehouseManager.entity.AllWarehouses;

public class Cli {
    // running
    Boolean running;
    // Input
    BufferedReader input;
    // Output
    PrintStream output;
    // Event Stream
    private EventStream eventStream;

    // Screens
    private InsertScreen insertScreen;
    private MainScreen mainScreen;
    private OverviewScreen overviewScreen;
    private DeleteScreen deleteScreen;
    private UpdateScreen updateScreen;

    // Validators
    private DeleteInput deleteInput;
    private ModuleInput moduleInput;
    private NewCargoInput newCargoInput;
    private NewUserInput newUserInput;
    private SaveInput saveInput;
    private SearchInput searchInput;

    // Commands
    /**
     * @Source: https://programming.guide/java/function-pointers-in-java.html
     */
    private Map<String, Runnable> views = new HashMap<>();

    public Cli(EventStream eventStream, BufferedReader input, PrintStream output) {
        // input
        this.input = input;
        // output 
        this.output = output;
        // event stream
        this.eventStream = eventStream;
        // screens
        this.insertScreen = new InsertScreen(output);
        this.mainScreen = new MainScreen(output);
        this.overviewScreen = new OverviewScreen(output);
        this.deleteScreen = new DeleteScreen(output);
        this.updateScreen = new UpdateScreen(output);

        // validators
        this.deleteInput = new DeleteInput();
        this.moduleInput = new ModuleInput();
        this.newCargoInput = new NewCargoInput();
        this.newUserInput = new NewUserInput();
        this.saveInput = new SaveInput();
        this.searchInput = new SearchInput();

        this.views.put("main:content", () -> this.mainScreen.getContent());
        this.views.put("main:usage", () -> this.mainScreen.getUsage());

        this.views.put("delete:content", () -> this.deleteScreen.getContent());
        this.views.put("delete:usage", () -> this.deleteScreen.getUsage());

        this.views.put("update:content", () -> this.updateScreen.getContent());
        this.views.put("update:usage", () -> this.updateScreen.getUsage());

        this.views.put("input:content", () ->{
            this.insertScreen.getContent();
        });
        this.views.put("input:usage", () -> this.insertScreen.getUsage());

        this.views.put("overview:usage", () ->{
            this.overviewScreen.getUsage();
        });
        this.views.put("overview:customer", () -> {
            this.overviewScreen.setMode("customer");
            this.overviewScreen.getContent();
        });
        this.views.put("overview:cargo", () ->{
            this.overviewScreen.setMode("cargo");
            this.overviewScreen.getContent();
        });
        this.views.put("overview:hazard", () ->{
            this.overviewScreen.setMode("hazard");
            this.overviewScreen.getContent();
        });
    }


    public Event start() {
        this.running = true;
        while (this.running) {
            this.views.get("main:content").run();
            this.output.print("Komando> ");
            String input = this.getInput();
            parseInput(input);
        }
        return new CloseCliEvent(0);
    }


    public void parseInput(String input) {
        if (input.length() > 0 && input.charAt(0) == ':') {
            switch (input.charAt(1)) {
                case 'c':
                        insertMode();
                    break;
                case 'd':
                        deleteMode();
                    break;
                case 'r':
                        overviewMode();
                    break;
                case 'u':
                        updateMode();
                    break;
                case 'p':
                        persistMode();
                    break;
                case 'x':
                    this.running = false;
                    break;
                default:
                    this.output.println("Command not found!");
            }
        }
    }

    private void persistMode() {
    }

    private void updateMode() {
        this.views.get("update:usage").run();
        String[] input = this.getInput().split(" ");
        if(input[0].charAt(0) != '#') {
            this.updateMode();
        }
        this.eventStream.pushData("warehouse:update-item", input[0]);

    }

    public void showResponse(String message) {
        this.insertScreen.setMessage(message);
        this.views.get("input:content").run();
    }
    private void overviewMode() {
        this.views.get("overview:usage").run();
        String[] input = this.getInput().split(" ");
        if (this.searchInput.isValid(input)) {
            this.overviewScreen.rows = null;
            switch(this.searchInput.getType()) {
                case "customer":
                    Event userList = this.eventStream.pushData("user:getAll","");
                    if (userList instanceof  UserListData) {
                        ArrayList<Customer> customer = ((UserListData) userList).getUsers();

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
                    Event filteredCargo = this.eventStream.pushData("warehouse-manager:get-all-items", "type:" + input[1]);
                    insertHazardList(filteredCargo);
                    break;
                case "hazard:y":
                    Event hazardListY = this.eventStream.pushData("warehouse-manager:get-all-items","hazard:y");
                    insertHazardList(hazardListY);
                    break;
                case "hazard:n":
                    Event hazardListN = this.eventStream.pushData("warehouse-manager:get-all-items","hazard:n");
                    insertHazardList(hazardListN);
                    break;
                }

            this.views.get("overview:" + this.searchInput.getType().split(":")[0]).run();

            return;
        }
        this.overviewMode();
    }

    private void insertHazardList(Event hazardList) {
        ArrayList<Item> items = new ArrayList<>();

        if (hazardList instanceof GetCargoData ) {
            items = ((GetCargoData) hazardList).getItems();
        }
        if (hazardList instanceof AllItems ) {
            items = ((AllItems) hazardList).getItems();
        }


        this.overviewScreen.rows = new String[items.size()][];

        for(int i = 0; i < items.size(); i++) {
            this.overviewScreen.rows[i] = new String[7];
            this.overviewScreen.rows[i][0] = String.valueOf(items.get(i).getId());
            this.overviewScreen.rows[i][1] = String.valueOf((items.get(i).getHazards() == null)?"":items.get(i).getHazards().toString());
            this.overviewScreen.rows[i][2] = String.valueOf(items.get(i).getValue());
            this.overviewScreen.rows[i][3] = items.get(i).getDurationOfStorage().toMillis() / 1000 + " s";
            this.overviewScreen.rows[i][4] = String.valueOf(items.get(i).getOwner().getName());
            this.overviewScreen.rows[i][5] = String.valueOf(items.get(i).getLastInspectionDate());
            this.overviewScreen.rows[i][6] = String.valueOf(items.get(i).type);
        }

    }
    private void deleteMode() {
        this.views.get("delete:usage").run();
        String[] input = this.getInput().split(" ");
        if (input[0].charAt(0) == '#') {
            this.eventStream.pushData("warehouse:delete-item", input[0]);
            return;
        }
        this.eventStream.pushData("user:delete", input[0]);
    }

    private void insertMode() {
        this.views.get("input:usage").run();

        String[] input = this.getInput().split(" ");

        if (this.newUserInput.isValid(input)) {

            this.eventStream.pushData("user:new", this.newUserInput.get());
            return;
        }
        if (this.newCargoInput.isValid(input)) {

            Error error = null;

            AllWarehouses warehouses = (AllWarehouses) this.eventStream.pushData("warehouse-manager:get-all", "");
            for (Warehouse warehouse: warehouses.getWarehouses()) {
                Event event = this.eventStream.pushData("warehouse:store-item", warehouse.getWarehouseName() + "$$" + this.newCargoInput.getData());
                if (!(event instanceof Error)) { // no error means success
                    error = null;
                    break;
                } else {
                    error = (Error) event;
                }
            }
            if (error != null ){
                this.insertScreen.setMessage(error.getMessage());
                this.views.get("input:content").run();
                return;
            }
            /*
             * push success message then return to mainPage
             */
            return;
        }

        this.insertMode();
    }

    private String getInput() {
        BufferedReader br = this.input;
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
