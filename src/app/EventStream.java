package app;

import app.events.Event;
import app.events.ModuleEvent;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventStream  {
    private List<ModuleEvent> moduleEventListeners = new ArrayList<ModuleEvent>();
    private App app;
    private UUID uuid;

    public List<ModuleEvent> getModuleEventListeners() {
        return this.moduleEventListeners;
    }

    public EventStream() {
        this.uuid = UUID.randomUUID() ;
    }

    public EventStream(App app) {
        this.app = app;
    }

    public void dataConnector(ModuleEvent moduleEvent) {
        getModuleEventListeners().add(moduleEvent);
    }

    public Event pushData(String command, String data) {
        Event returnValue = null;
        for (ModuleEvent moduleEvent : moduleEventListeners) {
            System.out.println(moduleEvent.getName());
            if(moduleEvent.shouldRun()) {
                try {
                    Event event = moduleEvent.runModuleEvent(command, data, app, returnValue);
                    if (event != null) {
                        returnValue = event;
                    }
                    if(moduleEvent.shouldReturn()) {
                        moduleEvent.returnStop();
                    }
                } catch (IOException e) {
                    System.out.println("Error in Event chain on ");
                    System.out.println("");
                    System.out.println("command -> " + command);
                    System.out.println("data ->" + data);
                    System.out.println("");
                    System.out.println("");
                    e.printStackTrace();
                } catch (ParseException e) {
                    System.out.println("Data Error (corrupt json) in Event chain on ");
                    System.out.println("");
                    System.out.println("command -> " + command);
                    System.out.println("data ->" + data);
                    System.out.println("");
                    System.out.println("");
                    e.printStackTrace();
                }
            }
        }
        return returnValue;
    }
}
