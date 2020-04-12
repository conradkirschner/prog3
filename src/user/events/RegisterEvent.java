package user.events;

import app.App;
import app.EventStream;
import app.events.Connectable;
import app.events.RegisterModuleEvent;
import user.Module;
import user.events.ModuleEvent;

public class RegisterEvent implements RegisterModuleEvent, Connectable {

    @Override
    public Module registerModule(App app) {
        System.out.println("User Module registered");
        app.Module appModule = (app.Module) app.getModule("event-stream");


        return new Module(app);

    }

    @Override
    public void connectToStream(EventStream eventStream) {
        user.events.ModuleEvent moduleEvent = new ModuleEvent();
        eventStream.dataConnector(moduleEvent);

    }
}
