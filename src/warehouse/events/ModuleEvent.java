package warehouse.events;

import app.App;
import app.events.Event;
import warehouse.entity.Item;
import warehouse.input.NewItemInput;

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
        warehouse.Module warehouseModule = (warehouse.Module) app.getModule("warehouse");
        app.Module appModule = (app.Module) app.getModule("event-stream");

        switch (command) {
            // @todo allow multiple warehouses set a uuid here!
            case "warehouse:build":
                String[] sizeAndCount = data.split(":");
                warehouseModule.warehouse.setUp(
                        Integer.parseInt(sizeAndCount[0]),
                        Integer.parseInt(sizeAndCount[1])
                );
                break;
            case "warehouse:store-item":
                String itemId = String.valueOf(warehouseModule.warehouse.store(data));
                if (itemId.equals("-1")) {
                    appModule.eventStream.pushData("warehouse:store-item=full_storage", "Storage is full");
                } else if (itemId.equals("-2")) {
                    appModule.eventStream.pushData("warehouse:store-item=unkownHazard", "Storage is full");

                } else {
                    appModule.eventStream.pushData("warehouse:store-item=success",itemId );

                }
                break;
            case "warehouse:get-item":
                this.returnHere();
                return warehouseModule.warehouse.getItem(data);

                // data is the filter setting
            case "warehouse:get-all-items":
                this.returnHere();
                GetCargoData returnData = new GetCargoData(new ArrayList<Item>());
                returnData.addItems(warehouseModule.warehouse.getItems(data));


                if (event instanceof  GetCargoData) {
                    ((GetCargoData) event).addItems(returnData.getItems());
                    returnData = (GetCargoData) event;
                }
                return ((Event) returnData);

            case "warehouse:update-item":
                this.returnHere();
                String[] splittedData = data.split("@");
                NewItemInput newItemInput = new NewItemInput();
                Item item = newItemInput.getItem(splittedData[1]);
                warehouseModule.warehouse.updateItem(splittedData[0], item);
                return null;
            case "warehouse:delete-item":
                Item deleteItem =  warehouseModule.warehouse.getItem(data);
                warehouseModule.warehouse.deleteItem(deleteItem);
                break;
        }
        //this.stopRun();
        return null;
    }
}
