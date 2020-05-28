package app;

import simulation.Simulation;
import warehouseManager.model.WarehouseManager;
import app.events.Event;
import app.events.RegisterEventCliInput;
import app.events.RegisterEventOutput;
import app.events.RegisterModuleEvent;
import cli.events.CloseCliEvent;

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
    public static int run(App app, boolean simulate) {

        warehouse.events.RegisterEvent WarehouseModuleRegister = new warehouse.events.RegisterEvent();
        user.events.RegisterEvent UserModuleRegister = new user.events.RegisterEvent();
        cli.events.RegisterEvent CliModuleRegister = new cli.events.RegisterEvent();
        warehouseManager.events.RegisterEvent WarehouseManagerRegister = new warehouseManager.events.RegisterEvent();

        app.addModule(UserModuleRegister);
        app.addModule(WarehouseManagerRegister);
        app.addModule(WarehouseModuleRegister);
        app.addModule(CliModuleRegister);
        app.registerModule();  // register payed modules

        app.Module eventStreamModule = (app.Module) app.getModule("event-stream");
        eventStreamModule.eventStream.pushData("warehouse-manager:new","Hauptwarenlager");
        eventStreamModule.eventStream.pushData("warehouse-manager:new","Hauptwarenlager1");
        eventStreamModule.eventStream.pushData("warehouse-manager:new","Hauptwarenlager2");

        if (simulate) {
            Simulation simulation = new Simulation(eventStreamModule.getModule());
            simulation.start();
            return 0;
        }

        Event cliClose = eventStreamModule.eventStream.pushData("cli:start","4");

        return ((CloseCliEvent) cliClose).getStatus();
    }
}

