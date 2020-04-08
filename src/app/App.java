package app;

import app.events.Event;
import app.events.Module;
import app.events.ModuleEvent;
import app.events.RegisterModuleEvent;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class App {
    private List<RegisterModuleEvent> listeners = new ArrayList<RegisterModuleEvent>();
    private List<Module> modules = new ArrayList<Module>();

    public Module getModule(String name) {
        for (Module module : modules) {
            if (module.getName().equals(name)) {
                return module;
            }
        }
        return null;
    }

    public void addModule(RegisterModuleEvent toAdd) {
        listeners.add(toAdd);
    }

    public void registerModule() {
        for (RegisterModuleEvent registerModuleEvent : listeners) {
            Boolean isRegistered = false;
            Module newModule =  registerModuleEvent.registerModule(this);
            for (Module module : modules) {
                if (module.getName().equals(newModule.getName())) {
                    isRegistered = true;
                }
            }
            if (!isRegistered) {
                app.Module appModule = (app.Module) this.getModule("event-stream");
                if (appModule == null) {
                    // @todo check here if event stream geladen wird
                    modules.add(newModule);
                    return;
                }
                registerModuleEvent.connectToStream(appModule.eventStream);
                modules.add(newModule);

            }
        }
    }
}
