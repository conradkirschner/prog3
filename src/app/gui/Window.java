package app.gui;

import app.gui.controller.NewUserDialog;
import app.gui.controller.TableUserController;
import app.gui.controller.TableWarenController;
import app.gui.controller.WarenPanelController;
import app.gui.model.User;
import app.gui.model.Waren;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

@Service
public class Window {
    public TableWarenController tableWarenController;
    public TableUserController tableUserController;
    public WarenPanelController warenPanelController;
    private EventHandler eventHandler;
    @Inject
    private Stage primaryStage;

    public void setEventStream(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void start() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));

        Parent dialog = FXMLLoader.load(getClass().getResource("view/dialogWaren.fxml"));
        Scene dialogScene = new Scene(dialog);
        this.warenPanelController = new WarenPanelController(primaryStage, dialogScene, this.eventHandler);

        Parent dialogUser = FXMLLoader.load(getClass().getResource("view/dialogUser.fxml"));
        Scene dialogUserScene = new Scene(dialogUser);
        NewUserDialog newUserDialog = new NewUserDialog(primaryStage, dialogUserScene, this.eventHandler);

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

//        AllWarehouses allWarehouses = (AllWarehouses) this.eventStream.pushData("warehouse-manager:get-all" , "");
//        for (Warehouse warehouse :allWarehouses.getWarehouses()) {
//            GetCargoData allItems = (GetCargoData) this.eventStream.pushData("warehouse:get-all-items" , warehouse.getWarehouseName() + "$$ ");
//            for (Item item :allItems.getItems()) {
//                if (item instanceof MixedCargoLiquidBulkAndUnitised) {
//                    MixedCargoLiquidBulkAndUnitised convertedItem = (MixedCargoLiquidBulkAndUnitised) item;
//                    this.tableWarenController.data.add(new Waren(
//                            item.getId(),
//                            item.getOwner().getName(),
//                            item.type,
//                            item.getValue().toString(),
//                            (item.getHazards()==null)?"":item.getHazards().toString(),
//                            (convertedItem.isFragile())?"true":"false",
//                            (convertedItem.isPressurized())?"true":"false",
//                            String.valueOf(item.getDurationOfStorage().toMillis()/1000),
//                            warehouse.getWarehouseName()
//                    ));
//                } else if (item instanceof LiquidBulkCargo) {
//                    LiquidBulkCargo convertedItem = (LiquidBulkCargo) item;
//                    this.tableWarenController.data.add(new Waren(
//                            item.getId(),
//                            item.getOwner().getName(),
//                            item.type,
//                            item.getValue().toString(),
//                            (item.getHazards()==null)?"":item.getHazards().toString(),
//                            "false",
//                            (convertedItem.isPressurized())?"true":"false",
//                            String.valueOf(item.getDurationOfStorage().toMillis()/1000),
//                            warehouse.getWarehouseName()
//                    ));
//                } else if(item instanceof UnitisedCargo) {
//                    UnitisedCargo convertedItem = (UnitisedCargo) item;
//                    this.tableWarenController.data.add(new Waren(
//                            item.getId(),
//                            item.getOwner().getName(),
//                            item.type,
//                            item.getValue().toString(),
//                            (item.getHazards()==null)?"":item.getHazards().toString(),
//                            (convertedItem.isFragile())?"true":"false",
//                            "false",
//                            String.valueOf(item.getDurationOfStorage().toMillis()/1000),
//                            warehouse.getWarehouseName()
//                    ));
//                } else { // item
//                    this.tableWarenController.data.add(new Waren(
//                            item.getId(),
//                            item.getOwner().getName(),
//                            item.type,
//                            item.getValue().toString(),
//                            (item.getHazards()==null)?"":item.getHazards().toString(),
//                            "false",
//                            "false",
//                            String.valueOf(item.getDurationOfStorage().toMillis()/1000),
//                            warehouse.getWarehouseName()
//                    ));
//                }
//
//            }
//        }

    }

    public void closeWarenDialog() {
        this.warenPanelController.close();
    }

    public void loadWarehouses(){
//        AllWarehouses allWarehouses = (AllWarehouses) this.eventStream.pushData("warehouse-manager:get-all" , "");
//        this.warenPanelController.warehouseSelectionData.clear();
//
//        for (Warehouse warehouse :allWarehouses.getWarehouses()) {
//            this.warenPanelController.warehouseSelectionData.add(warehouse.getWarehouseName());
//        }
    }

}
