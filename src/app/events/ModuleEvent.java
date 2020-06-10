package app.events;

import app.App;

import java.io.IOException;
import java.text.ParseException;

public interface ModuleEvent {
    public String getName();
    public Boolean shouldRun();
    public void returnHere();
    public void returnStop();
    public Boolean shouldReturn();
    public Event runModuleEvent(String command, String data, App app, Event event) throws IOException, ParseException;

}
