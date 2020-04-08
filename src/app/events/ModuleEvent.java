package app.events;

import app.App;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

public interface ModuleEvent {
    public Boolean shouldRun();
    public void returnHere();
    public void returnStop();
    public Boolean shouldReturn();
    public Event runModuleEvent(String command, String data, App app) throws IOException, ParseException;

}
