package cli;

import app.App;
import app.EventStream;
import warehouse.Module;
import warehouse.events.ModuleEvent;
import warehouse.events.RegisterEvent;
import warehouse.entity.LiquidBulkCargo;
import storageContract.cargo.Hazard;
import user.model.UserManager;
import warehouse.Warehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Scanner;
public class Cli {
    private Warehouse warehouse;
    private UserManager userManager;
    private EventStream eventStream;

    public Cli(Warehouse warehouse, UserManager userManager, EventStream eventStream) {
        this.warehouse = warehouse;
        this.userManager = userManager;
        this.eventStream = eventStream;
    }

    public void start() throws IOException {
        while (true) {

            // create a scanner so we can read the command-line input
            Scanner scanner = new Scanner(System.in);
            System.out.println("--------------------------------------------");
            System.out.println("##--PROG-3--####--Warehouse-v0.0.1--######");
            System.out.println(":c - Einfügemodus");
            System.out.println(":d - Löschmodus");
            System.out.println(":r - Anzeigemodus");
            System.out.println(":u - Änderungsmodus");
            System.out.println(":p - Persistenzmodus");
            System.out.println(":config - Konfigurationsmodus");
            System.out.println("--------------------------------------------");
            System.out.print("Komando> ");

            // get their input as a String
            String input = scanner.next();
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
    private String getInput(String message) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(message);
        System.out.flush();
        return input.readLine();
    }

    private void insertMode() {
        for (int i = 0; i < 50; ++i) System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("[Kundenname] fügt einen Kunden ein");
        System.out.println("");
        System.out.println("[Frachttyp] [Kundenname] [Wert] [Einlagerungsdauer\n" +
                "in Sekunden] [kommaseparierte Gefahrenstoffe,\n" +
                "einzelnes Komma für keine] [[zerbrechlich (y/n)]\n" +
                "[unter Druck (y/n)] [fest (y/n)]]");
        System.out.println("--------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        String input = null;
        try {
            input = getInput("Kommand >");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (input.split(" ").length == 1) {
            eventStream.pushData("user:new", input);
            return;
        }
        LiquidBulkCargo liquidBulkCargo = new LiquidBulkCargo(
                new BigDecimal(4),
                this.userManager.getCustomer().get(0),
                Collections.singleton(Hazard.explosive),
                ZonedDateTime.now()
        );
        System.out.println(liquidBulkCargo.getJson());
        this.eventStream.pushData("warehouse:store-item",liquidBulkCargo.getJson());



    }
}
