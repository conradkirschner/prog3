package cli.events;

import app.App;
import app.events.Event;

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
    public String getName() {
        return "cli";
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
    public Event runModuleEvent(String command, String data, App app, Event event) {
        cli.Module cliModule = (cli.Module) app.getModule("cli");

        System.out.println("[verbose] ["+ command + "]["+ data + "]");
        switch (command) {
            case "cli:start":
                return cliModule.getModule().start();
            case "warehouse:store-item=success":
                cliModule.getModule().showResponse("Neues Item gespeichert");
                return null;
            case "warehouse:store-item=full_storage":
                cliModule.getModule().showResponse("Fehler: Das Lager ist voll!");
                return null;
            case "warehouse:store-item=unkownHazard":
                cliModule.getModule().showResponse("Fehler: Die Gefahrenklasse ist ungültig.");
                return null;
            case "warehouse:store-item=customerRequired":
                cliModule.getModule().showResponse(data);
                return null;
        }
        return null;
    }
}
