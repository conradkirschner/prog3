package app;

import app.events.Event;
import storageContract.cargo.Hazard;
import user.entity.Customer;
import warehouse.Module;
import warehouse.entity.LiquidBulkCargo;
import warehouse.events.ModuleEvent;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        App app = new App();

        app.events.RegisterEventStreamEvent registerEventStreamEvent = new app.events.RegisterEventStreamEvent();
        app.addModule(registerEventStreamEvent); // < --- THIS MUST BE THE FIRST MODULE!
        app.registerModule();  // starts module communication

        warehouse.events.RegisterEvent WarehouseModuleRegister = new warehouse.events.RegisterEvent();
        user.events.RegisterEvent UserModuleRegister = new user.events.RegisterEvent();
        cli.events.RegisterEvent CliModuleRegister = new cli.events.RegisterEvent();

        app.addModule(UserModuleRegister);
        app.addModule(WarehouseModuleRegister);
        app.addModule(CliModuleRegister);
        app.registerModule();  // register payed modules

        app.Module eventStreamModule = (app.Module) app.getModule("event-stream");
        eventStreamModule.eventStream.pushData("warehouse:build","4");
        eventStreamModule.eventStream.pushData("user:new","test-kunde");
        LiquidBulkCargo liquidBulkCargo = new LiquidBulkCargo(
                new BigDecimal(4),
                (storageContract.administration.Customer) eventStreamModule.eventStream.pushData("user:get","0"),
                Collections.singleton(Hazard.explosive),
                ZonedDateTime.now()
        );
        System.out.println(liquidBulkCargo.getJson());
        eventStreamModule.eventStream.pushData("warehouse:store-item",liquidBulkCargo.getJson());
        Event item =  eventStreamModule.eventStream.pushData("warehouse:get-item","4");
        eventStreamModule.eventStream.pushData("user:new","wann");
        eventStreamModule.eventStream.pushData("cli:start","4");

        System.exit(1);
    }
}
