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
        TextField ownerTextInput = (TextField) this.dialogScene.lookup("#owner");
        ChoiceBox typeSelection = (ChoiceBox) this.dialogScene.lookup("#type");
        TextField waggeTextInput = (TextField) this.dialogScene.lookup("#wagge");
        TextField hazzardTextInput = (TextField) this.dialogScene.lookup("#hazzard");
        CheckBox fragileCheckbox = (CheckBox) this.dialogScene.lookup("#fragile");
        CheckBox pressureCheckbox = (CheckBox) this.dialogScene.lookup("#pressure");
        ChoiceBox warehouseSelection = (ChoiceBox) this.dialogScene.lookup("#warehouse");
        TextField storeUntilTextInput = (TextField) this.dialogScene.lookup("#storeUntil");
        this.showError("");
        if(waren == null) {
            this.dialogStage.setTitle("Neue Ware hinzufügen");
            ownerTextInput.setText("");
            waggeTextInput.setText("");
            hazzardTextInput.setText("");
            storeUntilTextInput.setText("");
            typeSelection.getSelectionModel().select(0);
            warehouseSelection.getSelectionModel().select(0);
            fragileCheckbox.setSelected(false);
            pressureCheckbox.setSelected(false);

        } else {
            this.dialogStage.setTitle("Ware bearbeiten");
            ownerTextInput.setText(waren.getOwner());
            waggeTextInput.setText(waren.getWagge());
            hazzardTextInput.setText(waren.getHazzard().replace("[", "").replace("]","").replace(" ", ""));
            storeUntilTextInput.setText(waren.getStoreUntil());

            typeSelection.setValue(waren.getType());
            warehouseSelection.setValue(waren.getWarehouse());

            if (waren.getPressure().equals("true")) {
                pressureCheckbox.setSelected(true);
            } else {
                pressureCheckbox.setSelected(false);
            }
            if (waren.getFragile().equals("true")) {
                fragileCheckbox.setSelected(true);
            } else {
                fragileCheckbox.setSelected(false);

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
        String[] input = new String[8];
        input[0] = typeSelection.getValue() + "";
        input[1] = ownerTextInput.getText();
        input[2] = waggeTextInput.getText();
        input[3] = storeUntilTextInput.getText();
        input[4] = (hazzardTextInput.getText().equals("")?"":hazzardTextInput.getText());
        input[5] = convertCheckboxToString(fragileCheckbox);
        input[6] = convertCheckboxToString(pressureCheckbox);
        input[7] = "y";
        boolean isValid =  newCargoInput.isValid(input);
        if (!isValid) {
            this.showError("Ungültige Eingabe");
            return;
        }
        eventStream.pushData("warehouse:store-item", warehouseSelection.getValue() + "$$"  + newCargoInput.getData()
                 );
    }
    private String convertCheckboxToString(CheckBox checkBox) {
        if(checkBox.isSelected()) {
            return "true";
        }
        return "false";
    }

    public void close() {
        this.dialogStage.close();
    }

    public void removeItem(String id) {
        this.eventStream.pushData("warehouse:delete-item", id);
        this.eventStream.pushData("gui:refresh-items", id);
    }
}
