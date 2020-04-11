package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;

import app.EventStream;
import app.events.Event;
import cli.events.CloseCliEvent;
import cli.screens.InsertScreen;
import cli.screens.MainScreen;
import cli.screens.OverviewScreen;
import cli.validators.*;
import user.entity.Customer;
import user.events.UserListData;
import warehouse.entity.Item;
import warehouse.entity.LiquidBulkCargo;
import storageContract.cargo.Hazard;
import user.model.UserManager;
import warehouse.Warehouse;
import warehouse.events.GetCargoData;

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

        // validators
        this.deleteInput = new DeleteInput();
        this.moduleInput = new ModuleInput();
        this.newCargoInput = new NewCargoInput();
        this.newUserInput = new NewUserInput();
        this.saveInput = new SaveInput();
        this.searchInput = new SearchInput();

        this.views.put("main:content", () -> this.mainScreen.getContent());
        this.views.put("main:usage", () -> this.mainScreen.getUsage());

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
        if (input.charAt(0) == ':') {
            switch (input.charAt(1)) {
                case 'c':
                        insertMode();
                    break;
                case 'r':
                        overviewMode();
                    break;
                case 'x':
                    this.running = false;
                    break;
                default:
                    this.output.println("Command not found!");
            }
        }
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
                            this.overviewScreen.rows = new ArrayList<String>();
                            ArrayList<Customer> customer = ((UserListData) userList).getUsers();
                            for(int i = 0; i < customer.size(); i++) {
                                this.overviewScreen.rows.add(customer.get(i).getName());
                            }
                        }
                 break;
                case "cargo":
                    Event filteredCargo = this.eventStream.pushData("warehouse:get-all-items","hazard:y");
                    insertHazardList(filteredCargo);
                    break;
                case "hazard:y":
                    Event hazardListY = this.eventStream.pushData("warehouse:get-all-items","hazard:y");
                    insertHazardList(hazardListY);
                    break;
                case "hazard:n":
                    Event hazardListN = this.eventStream.pushData("warehouse:get-all-items","hazard:n");
                    insertHazardList(hazardListN);
                    break;
                }

            this.views.get("overview:" + this.searchInput.getType().split(":")[0]).run();

            return;
        }
        this.overviewMode();
    }

    private void insertHazardList(Event hazardList){
        if (hazardList instanceof GetCargoData) {
            this.overviewScreen.rows = new ArrayList<String>();
            ArrayList<Item> items = ((GetCargoData) hazardList).getItems();
            for(int i = 0; i < items.size(); i++) {
                this.overviewScreen.rows.add(items.get(i).getId());
            }
        }
    }
    private void insertMode() {
        this.views.get("input:usage").run();

        String[] input = this.getInput().split(" ");

        if (this.newUserInput.isValid(input)) {

            this.eventStream.pushData("user:new", this.newUserInput.get());
            return;
        }
        if (this.newCargoInput.isValid(input)) {
            Event event = this.eventStream.pushData("warehouse:store-item", this.newCargoInput.getData());
            if (event instanceof Error) {
                // @todo add handling (invalid username or warehouse full - message display)
                this.insertScreen.setMessage(((Error) event).getMessage());
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
