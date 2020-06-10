package gui.controller;

import app.EventStream;
import cli.validators.NewCargoInput;
import gui.model.Waren;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class WarenPanelController {
    Window primaryStage;
    Scene dialogScene;
    Stage dialogStage;
    public ObservableList<String> typeSelectionData = FXCollections.observableArrayList(
            "Item", "LiquidBulkCargo", "MixedCargoLiquidBulkAndUnitised", "UnitisedCargo"
    );
    public ObservableList<String> warehouseSelectionData = FXCollections.observableArrayList();
    private EventStream eventStream;

    public WarenPanelController(Window primaryStage, Scene scene, EventStream eventStream) {
        this.primaryStage = primaryStage;
        this.dialogScene = scene;
        this.eventStream = eventStream;
        this.dialogStage = new Stage();

        init();
    }

    public void init() {
        ChoiceBox typeSelection = (ChoiceBox) this.dialogScene.lookup("#type");
        typeSelection.setItems(typeSelectionData);
        ChoiceBox warehouseSelection = (ChoiceBox) this.dialogScene.lookup("#warehouse");
        warehouseSelection.setItems(warehouseSelectionData);

        Button submitButton = (Button) this.dialogScene.lookup("#submit");
        submitButton.setOnMouseClicked(e -> submit());

    }

    public void open(Waren waren) throws IOException {

        this.showError("");
        if(waren == null) {
            this.dialogStage.setTitle("Neue Ware hinzufügen");
        } else {
            this.dialogStage.setTitle("Ware bearbeiten");
            TextField ownerTextInput = (TextField) this.dialogScene.lookup("#owner");
            ChoiceBox typeSelection = (ChoiceBox) this.dialogScene.lookup("#type");
            TextField waggeTextInput = (TextField) this.dialogScene.lookup("#wagge");
            TextField hazzardTextInput = (TextField) this.dialogScene.lookup("#hazzard");
            CheckBox fragileCheckbox = (CheckBox) this.dialogScene.lookup("#fragile");
            CheckBox pressureCheckbox = (CheckBox) this.dialogScene.lookup("#pressure");
            ChoiceBox warehouseSelection = (ChoiceBox) this.dialogScene.lookup("#warehouse");
            TextField storeUntilTextInput = (TextField) this.dialogScene.lookup("#storeUntil");

            ownerTextInput.setText(waren.getOwner());
            waggeTextInput.setText(waren.getWagge());
            hazzardTextInput.setText(waren.getHazzard());
            storeUntilTextInput.setText(waren.getStoreUntil());

            typeSelection.getSelectionModel().select(0);
            warehouseSelection.getSelectionModel().select(0);

            if (waren.getPressure().equals("ja")) {
                pressureCheckbox.setSelected(true);
            }
            if (waren.getFragile().equals("ja")) {
                fragileCheckbox.setSelected(true);
            }

        }
        this.dialogStage.setScene(this.dialogScene);
        this.dialogStage.show();
    }

    public void showError(String text) {
        Label errorLabel = (Label) this.dialogScene.lookup("#error");
        errorLabel.setText(text);
    }

    private void submit() {
        Label errorLabel = (Label) this.dialogScene.lookup("#error");
        TextField ownerTextInput = (TextField) this.dialogScene.lookup("#owner");
        ChoiceBox typeSelection = (ChoiceBox) this.dialogScene.lookup("#type");
        TextField waggeTextInput = (TextField) this.dialogScene.lookup("#wagge");
        TextField hazzardTextInput = (TextField) this.dialogScene.lookup("#hazzard");
        CheckBox fragileCheckbox = (CheckBox) this.dialogScene.lookup("#fragile");
        CheckBox pressureCheckbox = (CheckBox) this.dialogScene.lookup("#pressure");
        ChoiceBox warehouseSelection = (ChoiceBox) this.dialogScene.lookup("#warehouse");
        TextField storeUntilTextInput = (TextField) this.dialogScene.lookup("#storeUntil");

        NewCargoInput newCargoInput = new NewCargoInput();

        boolean isValid =  newCargoInput.isValid(
                (typeSelection.getValue() + " " +
                        ownerTextInput.getText() + " " +
                        waggeTextInput.getText() + " " +
                        storeUntilTextInput.getText() + " " +
                        hazzardTextInput.getText() + " " +
                        convertCheckboxToString(fragileCheckbox) + " " +
                        convertCheckboxToString(pressureCheckbox) + " " +
                        "y ").split(" ")  // block is not required for homework
                );
        if (!isValid) {
            this.showError("Ungültige Eingabe");
            return;
        }
        eventStream.pushData("warehouse:store-item", warehouseSelection.getValue() + "$$"  + newCargoInput.getData()
                 );
    }
    private String convertCheckboxToString(CheckBox checkBox) {
        if(checkBox.isSelected()) {
            return "y";
        }
        return "n";
    }

    public void close() {
        this.dialogStage.close();
    }
}
