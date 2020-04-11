package app;

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
    public static int run(App app) {

        warehouse.events.RegisterEvent WarehouseModuleRegister = new warehouse.events.RegisterEvent();
        user.events.RegisterEvent UserModuleRegister = new user.events.RegisterEvent();
        cli.events.RegisterEvent CliModuleRegister = new cli.events.RegisterEvent();

        app.addModule(UserModuleRegister);
        app.addModule(WarehouseModuleRegister);
        app.addModule(CliModuleRegister);
        app.registerModule();  // register payed modules

        app.Module eventStreamModule = (app.Module) app.getModule("event-stream");
        eventStreamModule.eventStream.pushData("warehouse:build","1:100");
        Event cliClose = eventStreamModule.eventStream.pushData("cli:start","4");

        return ((CloseCliEvent) cliClose).getStatus();
    }
}
