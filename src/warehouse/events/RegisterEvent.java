package warehouse.events;

import app.App;
import app.EventStream;
import app.events.Connectable;
import app.events.RegisterModuleEvent;
import warehouse.Module;

public class RegisterEvent implements RegisterModuleEvent, Connectable {

    @Override
    public Module registerModule(App app) {
        System.out.println("Warehouse Module registered");
        return new Module(app);
    }

    @Override
    public void connectToStream(EventStream eventStream) {
        ModuleEvent moduleEvent = new ModuleEvent();
        eventStream.dataConnector(moduleEvent);
    }

    public Module registerModule(App app, Boolean connect) {
        System.out.println("Warehouse Module registered");
        app.Module appModule = (app.Module) app.getModule("event-stream");

        ModuleEvent moduleEvent = new ModuleEvent();
        appModule.eventStream.dataConnector(moduleEvent);
        return new Module(app);

    }
}
