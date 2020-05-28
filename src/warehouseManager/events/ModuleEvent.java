package warehouseManager.events;

import warehouse.entity.Item;
import warehouseManager.Module;
import app.App;
import app.events.Event;
import warehouse.model.Warehouse;
import warehouseManager.entity.AllItems;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

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

    @Override
    public Event runModuleEvent(String command, String data, App app, Event event) throws IOException, ParseException {
        Module warehouseManagerModule = (Module) app.getModule("warehouse-manager");
        app.Module appModule = (app.Module) app.getModule("event-stream");

        switch (command) {
            case "warehouse-manager:new":
                Warehouse warehouse = new Warehouse(data, appModule.eventStream);
                warehouse.setUp(10,1000);
                warehouseManagerModule.getModule().addWarehouse(warehouse);
                break;
            case "warehouse-manager:get-all-items":
                ArrayList<Warehouse> warehouseArrayList = warehouseManagerModule.getModule().getWarehouses();
                ArrayList<Item> items = new ArrayList<>();
                for (Warehouse warehouse1: warehouseArrayList) {
                    items.addAll(warehouse1.getItems());
                }
                returnHere();
                returnStop();
                return (Event) new AllItems(items);
            case "warehouse-manager:get":
                returnHere();
                returnStop();
                warehouseManagerModule.getModule().getWarehouse(data);
                break;
            case "warehouse:remove":
                //not required
        }
        //this.stopRun();
        return null;
    }
}
