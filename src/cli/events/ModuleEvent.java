package cli.events;

import app.App;
import app.events.Event;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

public class ModuleEvent implements app.events.ModuleEvent {
    public Boolean shouldRun;
    public Boolean shouldReturn;

    public ModuleEvent(String command, String data) {
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

    @Override
    public Event runModuleEvent(String command, String data, App app, Event event) throws IOException, ParseException {
        cli.Module cliModule = (cli.Module) app.getModule("cli");

        System.out.println("[verbose] ["+ command + "]["+ data + "]");
        switch (command) {
            case "cli:start":
                cliModule.cli.start();
                break;
        }
        return null;
    }
}
