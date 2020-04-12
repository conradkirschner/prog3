package app;

import app.events.*;
import app.events.Module;
import cli.validators.Validator;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
                // load autostart events and connect to main event stream
                if (registerModuleEvent instanceof Connectable) {
                    ((Connectable) registerModuleEvent).connectToStream(appModule.eventStream);
                }
                modules.add(newModule);

            }
        }
    }
}
