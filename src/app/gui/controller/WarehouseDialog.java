package app.gui.controller;

import app.gui.JavaFX;
import app.warehouse.events.CreateWarehouseEvent;
import app.warehouseDistributor.events.ActivateWarehouseEvent;
import famework.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;

public class WarehouseDialog {
    Window primaryStage;
    Scene dialogScene;
    Stage newStage;
    JavaFX javaFX;
    private EventHandler eventHandler;

    public WarehouseDialog(Window primaryStage, Scene dialogScene, EventHandler eventHandler, JavaFX javaFX) {
        this.primaryStage = primaryStage;
        this.dialogScene = dialogScene;
        this.eventHandler = eventHandler;

        this.javaFX = javaFX;
    }
    public boolean open() {
        newStage = javaFX.newStage();

        newStage.setScene(this.dialogScene);
        newStage.setTitle("Warenhäuser verwalten");
        newStage.show();
        Button submitButton = (Button) this.dialogScene.lookup("#submit");
        submitButton.setOnMouseClicked(eventStream -> submit());
        Label errorLabel = (Label) this.dialogScene.lookup("#error");
        errorLabel.setText("");
        return true;
    }

    private void submit() {
        TextField warehousesTextinput = (TextField) this.dialogScene.lookup("#warehouses");
        Label errorLabel = (Label) this.dialogScene.lookup("#error");
        if (warehousesTextinput.getText().equals("")) {
            errorLabel.setText("Keine Leere Eingabe!");
            return;
        }
        String[] warehouses = warehousesTextinput.getText().split(" ");
        this.eventHandler.push(new ActivateWarehouseEvent(new ArrayList<>()));

        for (String warehouse: warehouses) {
            this.eventHandler.push(new CreateWarehouseEvent(warehouse));
        }
        this.newStage.close();
    }
}
