package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import app.EventStream;
import app.events.Event;
import cli.screens.InsertScreen;
import cli.screens.MainScreen;
import cli.validators.*;
import warehouse.entity.LiquidBulkCargo;
import storageContract.cargo.Hazard;
import user.model.UserManager;
import warehouse.Warehouse;

public class Cli {
    private Warehouse warehouse;
    private UserManager userManager;
    private EventStream eventStream;
    private String currentScreen;

    // input
    private Scanner scanner;

    // Screens
    private InsertScreen insertScreen;
    private MainScreen mainScreen;

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

    public Cli(UserManager userManager, EventStream eventStream) {
        this.userManager = userManager;
        this.eventStream = eventStream;

        // input reader
        this.scanner = new Scanner(System.in);

        // screens
        this.insertScreen = new InsertScreen();
        this.mainScreen = new MainScreen();

        // validators
        this.deleteInput = new DeleteInput();
        this.moduleInput = new ModuleInput();
        this.newCargoInput = new NewCargoInput();
        this.newUserInput = new NewUserInput();
        this.saveInput = new SaveInput();
        this.searchInput = new SearchInput();


        // set start view
        this.currentScreen = "main:content";

        this.views.put("main:content", () -> this.mainScreen.getContent());
        this.views.put("main:usage", () -> this.mainScreen.getUsage());
        this.views.put("input:content:success", () ->{
            this.insertScreen.setStatus(true);
            this.insertScreen.getContent();
        });
        this.views.put("input:content:error", () ->{
            this.insertScreen.setStatus(false);
            this.insertScreen.getContent();
        });
        this.views.put("input:usage", () -> this.insertScreen.getUsage());

    }


    public void start() {
        while (true) {
            this.views.get(this.currentScreen).run();
            System.out.print("Komando> ");
            String input = this.scanner.next();

            for (int i = 0; i < 50; ++i) System.out.println();

            parseInput(input);

        }
    }


    public void parseInput(String input) {
        if (input.charAt(0) == ':') {
            switch (input.charAt(1)) {
                case 'c':
                        insertMode();
                    break;
                default:
                    System.out.println("Command not found!");
            }
        }
    }

    private void insertMode() {
        this.currentScreen = "input:usage"; // define next screen

        this.views.get(this.currentScreen).run();
        this.currentScreen = "main:content";

        String[] input = this.getInput().split(" ");

        if (this.newUserInput.isValid(input)) {

            this.eventStream.pushData("user:new", this.newUserInput.get());
            return;
        }
        if (this.newCargoInput.isValid(input)) {
            Event event = this.eventStream.pushData("warehouse:store-item", this.newCargoInput.getData());
            if (event instanceof Error) {
                // @todo add handling (invalid username or warehouse full - message display)
                this.currentScreen = "input:content:success";
                this.views.get(this.currentScreen).run();
                return;
            }
            /*
             * push success message then return to mainPage
             */
            this.currentScreen = "input:content:success";
            this.views.get(this.currentScreen).run();
            this.currentScreen = "main:content";
            return;
        }
        this.currentScreen = "input:content:error";
        this.views.get(this.currentScreen).run();
        this.insertMode();
    }

    private String getInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
