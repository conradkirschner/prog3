package app.gui;

import app.gui.controller.*;
import app.gui.model.User;
import app.gui.model.Waren;
import app.user.events.DeleteUserEvent;
import app.warehouse.events.DeleteItemEvent;
import app.warehouse.events.UpdateItemEvent;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

@Service
public class Window {
    public TableWarenController tableWarenController;
    public TableUserController tableUserController;
    public WarenPanelController warenPanelController;
    public NewUserDialog newUserDialog;
    public WarehouseDialog warehouseDialog;
    private EventHandler eventHandler;
    @Inject
    private Stage primaryStage;

    @Inject
    private JavaFX javaFX;

    public void start(EventHandler eventHandler) throws IOException {
        this.eventHandler = eventHandler;
        Stage primaryStage = javaFX.newStage();



        Parent root = FXMLLoader.load(getClass().getResource("./view/main.fxml"));

        Parent dialog = FXMLLoader.load(getClass().getResource("./view/dialogWaren.fxml"));
        Scene dialogScene = JavaFX.newScene(dialog);
        this.warenPanelController = new WarenPanelController(primaryStage, dialogScene, eventHandler);

        Parent dialogUser = FXMLLoader.load(getClass().getResource("./view/dialogUser.fxml"));
        Scene dialogUserScene = JavaFX.newScene(dialogUser);
        newUserDialog = new NewUserDialog(primaryStage, dialogUserScene, eventHandler, this.javaFX);

        Parent dialogWarehouses = FXMLLoader.load(getClass().getResource("./view/dialogWarehouse.fxml"));
        Scene dialogWarehousesScene = JavaFX.newScene(dialogWarehouses);
        warehouseDialog = new WarehouseDialog(primaryStage, dialogWarehousesScene, eventHandler, this.javaFX);

        Scene scene =  JavaFX.newScene(root);

        primaryStage.setTitle("Warenlager");
        primaryStage.setScene(scene);
        primaryStage.show();

        setToolBarActions(scene);
        showWarenTable(scene);

        TableView warenTable = (TableView) scene.lookup("#warenTable");
        TableView userTable = (TableView) scene.lookup("#userTable");
        warenTable.prefHeightProperty().bind(primaryStage.heightProperty());
        warenTable.prefWidthProperty().bind(primaryStage.widthProperty());

        userTable.prefHeightProperty().bind(primaryStage.heightProperty());
        userTable.prefWidthProperty().bind(primaryStage.widthProperty());

        this.tableWarenController = new TableWarenController(warenTable, this.warenPanelController, newUserDialog, eventHandler);
        this.tableUserController = new TableUserController(userTable);

    }
    public void setToolBarActions(Scene scene) {
        TableView warenTable = (TableView) scene.lookup("#warenTable");
        TableView userTable = (TableView) scene.lookup("#userTable");

        Button showUserButton = (Button) scene.lookup("#showUserButton");
        Button showItemButton = (Button) scene.lookup("#showItemButton");
        Button newUserButton = (Button) scene.lookup("#newUserButton");
        Button newItemButton = (Button) scene.lookup("#newItemButton");
        Button updateItemButton = (Button) scene.lookup("#updateItemButton");
        Button deleteUserButton = (Button) scene.lookup("#deleteUserButton");
        Button deleteItemButton = (Button) scene.lookup("#deleteItemButton");
        Button showWarehousesButton = (Button) scene.lookup("#showWarehousesButton");

        showItemButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                showWarenTable(scene);
            }
        });
        showUserButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
               showUserTable(scene);
            }
        });
        newUserButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                newUserDialog.open();
            }
        });
        showWarehousesButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                warehouseDialog.open();
            }
        });
        newItemButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    warenPanelController.open(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        updateItemButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                Waren waren = (Waren) warenTable.getSelectionModel().getSelectedItem();
                eventHandler.push(new UpdateItemEvent('#' + waren.getId()));
            }
        });
        deleteItemButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                deleteSelectedItem(scene);
            }
        });
        deleteUserButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                deleteSelectedUser(scene);
            }
        });
    }

    public void showWareResponse(String text){
        this.warenPanelController.showError(text);
    }

    private void showUserTable(Scene scene) {
        VBox warenTable = (VBox) scene.lookup("#warenTableBox");
        VBox userTable = (VBox) scene.lookup("#userTableBox");
        Button updateItemButton = (Button) scene.lookup("#updateItemButton");
        Button deleteUserButton = (Button) scene.lookup("#deleteUserButton");
        Button deleteItemButton = (Button) scene.lookup("#deleteItemButton");
        Button newUserButton = (Button) scene.lookup("#newUserButton");
        Button newItemButton = (Button) scene.lookup("#newItemButton");

        updateItemButton.setVisible(false);
        updateItemButton.managedProperty().bind(updateItemButton.visibleProperty());
        deleteUserButton.setVisible(true);
        deleteUserButton.managedProperty().bind(deleteUserButton.visibleProperty());
        deleteItemButton.setVisible(false);
        deleteItemButton.managedProperty().bind(deleteItemButton.visibleProperty());

        newUserButton.setVisible(true);
        newUserButton.managedProperty().bind(newUserButton.visibleProperty());
        newItemButton.setVisible(false);
        newItemButton.managedProperty().bind(newItemButton.visibleProperty());

        warenTable.setVisible(false);
        warenTable.managedProperty().bind(warenTable.visibleProperty());
        userTable.setVisible(true);
        userTable.managedProperty().bind(userTable.visibleProperty());
    }
    private void showWarenTable(Scene scene) {
        VBox warenTable = (VBox) scene.lookup("#warenTableBox");
        VBox userTable = (VBox) scene.lookup("#userTableBox");
        Button updateItemButton = (Button) scene.lookup("#updateItemButton");
        Button deleteUserButton = (Button) scene.lookup("#deleteUserButton");
        Button deleteItemButton = (Button) scene.lookup("#deleteItemButton");
        Button newUserButton = (Button) scene.lookup("#newUserButton");
        Button newItemButton = (Button) scene.lookup("#newItemButton");

        updateItemButton.setVisible(true);
        updateItemButton.managedProperty().bind(updateItemButton.visibleProperty());
        deleteUserButton.setVisible(false);
        deleteUserButton.managedProperty().bind(deleteUserButton.visibleProperty());
        deleteItemButton.setVisible(true);
        deleteItemButton.managedProperty().bind(deleteItemButton.visibleProperty());
        newUserButton.setVisible(false);
        newUserButton.managedProperty().bind(newUserButton.visibleProperty());
        newItemButton.setVisible(true);
        newItemButton.managedProperty().bind(newItemButton.visibleProperty());

        warenTable.setVisible(true);
        warenTable.managedProperty().bind(warenTable.visibleProperty());
        userTable.setVisible(false);
        userTable.managedProperty().bind(userTable.visibleProperty());
    }
    private void deleteSelectedUser(Scene scene) {
        TableView userTable = (TableView) scene.lookup("#userTable");
        if (!userTable.isVisible()) {
            return;
        }
        User user = (User) userTable.getSelectionModel().getSelectedItem();
        this.eventHandler.push(new DeleteUserEvent(user.getUsername()));
    }

    private void deleteSelectedItem(Scene scene) {
        TableView warenTable = (TableView) scene.lookup("#warenTable");
        if (!warenTable.isVisible()) {
            return;
        }
        Waren item = (Waren) warenTable.getSelectionModel().getSelectedItem();
        this.eventHandler.push(new DeleteItemEvent('#'+item.getId()));
    }


}
