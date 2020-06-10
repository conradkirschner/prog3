package gui;

import app.EventStream;
import gui.controller.NewUserDialog;
import gui.controller.TableUserController;
import gui.controller.TableWarenController;
import gui.controller.WarenPanelController;
import gui.model.User;
import gui.model.Waren;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import warehouse.entity.Item;
import warehouse.entity.LiquidBulkCargo;
import warehouse.entity.MixedCargoLiquidBulkAndUnitised;
import warehouse.entity.UnitisedCargo;
import warehouse.events.GetCargoData;
import warehouse.model.Warehouse;
import warehouseManager.entity.AllWarehouses;

import java.io.IOException;

public class Window {
    public TableWarenController tableWarenController;
    public TableUserController tableUserController;
    public WarenPanelController warenPanelController;
    private EventStream eventStream;

    public void setEventStream(EventStream eventStream) {
        this.eventStream = eventStream;
    }

    public void start(Stage primaryStage) throws IOException {
    
        Parent dialog = FXMLLoader.load(getClass().getResource("view/dialogWaren.fxml"));
        Scene dialogScene = new Scene(dialog);
        this.warenPanelController = new WarenPanelController(primaryStage, dialogScene, this.eventStream);

        Parent dialogUser = FXMLLoader.load(getClass().getResource("view/dialogUser.fxml"));
        Scene dialogUserScene = new Scene(dialogUser);
        NewUserDialog newUserDialog = new NewUserDialog(primaryStage, dialogUserScene, this.eventStream);

        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Warenlager");
        primaryStage.setScene(scene);
        primaryStage.show();
        TableView<Waren> warenTable = (TableView<Waren>) scene.lookup("#warenTable");
        TableView<User> userTable = (TableView<User>) scene.lookup("#userTable");
        this.tableWarenController = new TableWarenController(warenTable, this.warenPanelController, newUserDialog);
        this.tableUserController = new TableUserController(userTable);
        loadWarehouses();
        loadWare();

    }

    public void showWareResponse(String text){
        this.warenPanelController.showError(text);
    }

    public void loadWare() {
        this.tableWarenController.data.clear();

        AllWarehouses allWarehouses = (AllWarehouses) this.eventStream.pushData("warehouse-manager:get-all" , "");
        for (Warehouse warehouse :allWarehouses.getWarehouses()) {
            GetCargoData allItems = (GetCargoData) this.eventStream.pushData("warehouse:get-all-items" , warehouse.getWarehouseName() + "$$ ");
            for (Item item :allItems.getItems()) {
                if (item instanceof MixedCargoLiquidBulkAndUnitised) {
                    MixedCargoLiquidBulkAndUnitised convertedItem = (MixedCargoLiquidBulkAndUnitised) item;
                    this.tableWarenController.data.add(new Waren(
                            item.getId(),
                            item.getOwner().getName(),
                            item.type,
                            item.getValue().toString(),
                            (item.getHazards()==null)?"":item.getHazards().toString(),
                            (convertedItem.isFragile())?"true":"false",
                            (convertedItem.isPressurized())?"true":"false",
                            String.valueOf(item.getDurationOfStorage().toMillis()/1000),
                            warehouse.getWarehouseName()
                    ));
                } else if (item instanceof LiquidBulkCargo) {
                    LiquidBulkCargo convertedItem = (LiquidBulkCargo) item;
                    this.tableWarenController.data.add(new Waren(
                            item.getId(),
                            item.getOwner().getName(),
                            item.type,
                            item.getValue().toString(),
                            (item.getHazards()==null)?"":item.getHazards().toString(),
                            "false",
                            (convertedItem.isPressurized())?"true":"false",
                            String.valueOf(item.getDurationOfStorage().toMillis()/1000),
                            warehouse.getWarehouseName()
                    ));
                } else if(item instanceof UnitisedCargo) {
                    UnitisedCargo convertedItem = (UnitisedCargo) item;
                    this.tableWarenController.data.add(new Waren(
                            item.getId(),
                            item.getOwner().getName(),
                            item.type,
                            item.getValue().toString(),
                            (item.getHazards()==null)?"":item.getHazards().toString(),
                            (convertedItem.isFragile())?"true":"false",
                            "false",
                            String.valueOf(item.getDurationOfStorage().toMillis()/1000),
                            warehouse.getWarehouseName()
                    ));
                } else { // item
                    this.tableWarenController.data.add(new Waren(
                            item.getId(),
                            item.getOwner().getName(),
                            item.type,
                            item.getValue().toString(),
                            (item.getHazards()==null)?"":item.getHazards().toString(),
                            "false",
                            "false",
                            String.valueOf(item.getDurationOfStorage().toMillis()/1000),
                            warehouse.getWarehouseName()
                    ));
                }

            }
        }

    }

    public void closeWarenDialog() {
        this.warenPanelController.close();
    }

    public void loadWarehouses(){
        AllWarehouses allWarehouses = (AllWarehouses) this.eventStream.pushData("warehouse-manager:get-all" , "");
        this.warenPanelController.warehouseSelectionData.clear();

        for (Warehouse warehouse :allWarehouses.getWarehouses()) {
            this.warenPanelController.warehouseSelectionData.add(warehouse.getWarehouseName());
        }
    }

}
