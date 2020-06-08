package gui.events;

import app.App;
import app.events.Event;

import java.io.IOException;
import java.text.ParseException;

public class ModuleEvent implements app.events.ModuleEvent {

    public Boolean shouldRun;
    public Boolean shouldReturn;


    public ModuleEvent() {
        this.shouldRun = true;
        this.shouldReturn = false;
    }
    public void stopRun() {
        this.shouldRun = false;
    }
    @Override
    public Boolean shouldRun() {
        return shouldRun;
    }

    @Override
    public void returnHere() {
        this.shouldReturn = true;
    }

    @Override
    public void returnStop() {
        this.shouldReturn = false;
    }

    @Override
    public Boolean shouldReturn() {
        if (this.shouldReturn) {
            this.shouldReturn = false;
            return true;
        }
        return false;
    }

    public Event runModuleEvent(String command, String data, App app, Event event) throws IOException, ParseException {
        switch (command) {

        }

        System.out.print("gui triggered - ");
        //this.stopRun();
        return null;
    }
}
