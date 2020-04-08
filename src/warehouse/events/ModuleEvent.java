package warehouse.events;

import app.App;
import app.events.Event;
import warehouse.entity.Item;

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

    @Override
    public Event runModuleEvent(String command, String data, App app) throws ParseException {
        warehouse.Module warehouseModule = (warehouse.Module) app.getModule("warehouse");
        app.Module appModule = (app.Module) app.getModule("event-stream");

        switch (command) {
            // @todo allow multiple warehouses set a uuid here!
            case "warehouse:build":
                warehouseModule.warehouse.setUp(  Integer.parseInt(data));
                break;
            case "warehouse:store-item":
                if (warehouseModule.warehouse.store(data) != -1) {
                    appModule.eventStream.pushData("warehouse:store-item=success", String.valueOf(warehouseModule.warehouse.store(data)));
                } else {
                    appModule.eventStream.pushData("warehouse:store-item=full_storage", "Storage is full");
                }
                break;
            case "warehouse:get-item":
                this.returnHere();
                return warehouseModule.warehouse.getItem(data);
            case "warehouse:update-item":
                this.returnHere();
                String[] splittedData = data.split("@");
                Item updateItem =  warehouseModule.warehouse.getItem(splittedData[0]);
                updateItem.restoreFromJson(splittedData[1]);
                return updateItem;
            case "warehouse:delete-item":
                Item deleteItem =  warehouseModule.warehouse.getItem(data);
                warehouseModule.warehouse.deleteItem(deleteItem);
                break;
        }
        //this.stopRun();
        return null;
    }
}
