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
    public String getName() {
        return "gui";
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
        gui.Module gui = (gui.Module) app.getModule("gui");
        switch (command) {

            case "gui:refresh-items":
                gui.getModule().loadWare();
                break;
            case "warehouse:store-item=success":
                gui.getModule().closeWarenDialog();
                gui.getModule().loadWare();
                return null;
            case "warehouse:store-item=full_storage":
                gui.getModule().showWareResponse("Lager ist voll");
                return null;
            case "warehouse:store-item=unkownHazard":
                gui.getModule().showWareResponse("Gefahrenklasse ist unbekannt");
                return null;
            case "warehouse:store-item=customerRequired":
                gui.getModule().showWareResponse("User ist unbekannt");
                return null;
        }

        System.out.println("gui triggered - ");
        System.out.println(command);
        System.out.println(data);
        //this.stopRun();
        return null;
    }
}
