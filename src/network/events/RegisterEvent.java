package network.events;

import app.App;
import app.EventStream;
import app.events.Connectable;
import app.events.RegisterModuleEvent;
import network.model.NetworkManager;
import network.Module;

public class RegisterEvent implements Connectable {

    @Override
    public Module registerModule(App app) {
        System.out.println("Network Module registered");
        app.Module appModule = (app.Module) app.getModule("event-stream");


        return new Module(app, new NetworkManager(appModule.getModule()));

    }

    @Override
    public void connectToStream(EventStream eventStream) {
        ModuleEvent moduleEvent = new ModuleEvent();
        eventStream.dataConnector(moduleEvent);

    }
}
