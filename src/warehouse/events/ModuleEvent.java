package warehouse.events;

import app.App;
import app.events.Event;
import warehouse.entity.Item;
import warehouse.model.Warehouse;

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
    public String getName() {
        return "warehouse";
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
        warehouseManager.Module warehouseManagerModule = (warehouseManager.Module) app.getModule("warehouse-manager");
        app.Module appModule = (app.Module) app.getModule("event-stream");
        String[] idAndCargo = null;
        switch (command) {
            // Notify InsertThread
            case "warehouse:store-item":
                idAndCargo = data.split("\\$\\$");

                String itemId = String.valueOf(warehouseManagerModule.getModule().getWarehouse(idAndCargo[0]).store(idAndCargo[1]));

                if (itemId.equals("-1")) {
                    appModule.eventStream.pushData("warehouse:store-item=full_storage", "Storage is full in " + idAndCargo[0] + ". Try other one");
                    returnStop();
                    returnHere();
                    return new FailedToInsert();

                } else if (itemId.equals("-2")) {
                    appModule.eventStream.pushData("warehouse:store-item=unkownHazard", "Unknown Hazard");
                }else if (itemId.equals("-3")) {
                    appModule.eventStream.pushData("warehouse:store-item=customerRequired", "Es wird ein gültiger Benutzer für das Einlagern des Items benötigt");
                } else {
                    appModule.eventStream.pushData("warehouse:store-item=success", itemId);

                }
                break;
            case "warehouse:get-item":
                this.returnHere();
                for (Warehouse warehouse: warehouseManagerModule.getModule().getWarehouses().getWarehouses()) {
                    Item item = warehouse.getItem(data);
                    if (item != null) return item;
                }
                return null;

                // data is the filter setting
            case "warehouse:get-all-items":
                String[] nameAndFilter = data.split("\\$\\$");

                this.returnHere();
                GetCargoData returnData = new GetCargoData(new ArrayList<Item>());
                returnData.addItems(warehouseManagerModule.getModule().getWarehouse(nameAndFilter[0]).getItems(nameAndFilter[1]));


                if (event instanceof  GetCargoData) {
                    ((GetCargoData) event).addItems(returnData.getItems());
                    returnData = (GetCargoData) event;
                }
                return ((Event) returnData);

            case "warehouse:update-item":
                this.returnHere();
                String[] splittedData = data.split("@");
                for (Warehouse warehouse: warehouseManagerModule.getModule().getWarehouses().getWarehouses()) {
                    Item item = warehouse.getItem(data);
                    if (item != null) {
                        warehouse.updateItem(splittedData[0]);
                    }
                }
                return null;
            case "warehouse:delete-item":
                for (Warehouse warehouse: warehouseManagerModule.getModule().getWarehouses().getWarehouses()) {
                    Item item = warehouse.getItem(data);
                    if (item != null) {
                        warehouse.deleteItem(data);
                    }
                }
                break;
        }
        //this.stopRun();
        return null;
    }
}
