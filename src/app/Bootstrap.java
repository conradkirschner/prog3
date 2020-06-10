package app;

import app.events.Event;
import app.events.RegisterEventCliInput;
import app.events.RegisterEventOutput;
import app.events.RegisterModuleEvent;
import cli.events.CloseCliEvent;
import simulation.Simulation;

import java.io.BufferedReader;
import java.io.PrintStream;

public class Bootstrap {
    public static App setup(BufferedReader cliInput, PrintStream ouput) {
        App app = new App();
        app.events.RegisterEventStreamEvent registerEventStreamEvent = new app.events.RegisterEventStreamEvent();
        RegisterModuleEvent CliInput = new RegisterEventCliInput(cliInput);
        RegisterModuleEvent CliOutput = new RegisterEventOutput(ouput);

        app.addModule(registerEventStreamEvent); // < --- THIS MUST BE THE FIRST MODULE!
        app.addModule(CliInput);
        app.addModule(CliOutput);
        app.registerModule();  // starts module communication
        return app;
    }
    public static int bootGUI() {

        return 0;
    }

    public static int run(App app, boolean simulate, boolean gui) {

        warehouse.events.RegisterEvent WarehouseModuleRegister = new warehouse.events.RegisterEvent();
        user.events.RegisterEvent UserModuleRegister = new user.events.RegisterEvent();
        warehouseManager.events.RegisterEvent WarehouseManagerRegister = new warehouseManager.events.RegisterEvent();

        app.addModule(UserModuleRegister);
        app.addModule(WarehouseManagerRegister);
        app.addModule(WarehouseModuleRegister);
        app.registerModule();  // register payed modules
        app.Module eventStreamModule = (app.Module) app.getModule("event-stream");


        // console simulation
        if (simulate && !gui) {
            setDefaultData((EventStream) eventStreamModule.getModule());
            Simulation simulation = new Simulation(eventStreamModule.getModule());
            simulation.start();
            return 0;
        }
        // gui module
        if (gui) {

            gui.events.RegisterEvent GUIModuleRegister = new gui.events.RegisterEvent();
            app.addModule(GUIModuleRegister);
            app.registerModule();

            gui.Module GUIModule = (gui.Module) app.getModule("gui");
            String[] arguments = new String[0];
            setDefaultData((EventStream) eventStreamModule.getModule());
            return 0;
        }
        // gui simulation
        if (simulate) {
            Simulation simulation = new Simulation(eventStreamModule.getModule());
            simulation.start();
            return 0;
        }

        if (!gui) {
            cli.events.RegisterEvent CliModuleRegister = new cli.events.RegisterEvent();
            app.addModule(CliModuleRegister);
            app.registerModule();
            setDefaultData((EventStream) eventStreamModule.getModule());

            Event cliClose = eventStreamModule.eventStream.pushData("cli:start","4");
            return ((CloseCliEvent) cliClose).getStatus();
        }
        return 1;

    }
    public static void setDefaultData(EventStream eventStream) {
        eventStream.pushData("warehouse-manager:new","Hauptwarenlager");
        eventStream.pushData("warehouse-manager:new","Hauptwarenlager1");
        eventStream.pushData("warehouse-manager:new","Hauptwarenlager2");

    }
}

