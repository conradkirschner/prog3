package app.gui.controller;

import app.gui.model.Waren;
import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.entity.LiquidBulkCargo;
import app.warehouse.entity.MixedCargoLiquidBulkAndUnitised;
import app.warehouse.entity.UnitisedCargo;
import app.warehouse.events.DeleteItemEvent;
import app.warehouse.events.StoreItemEvent;
import famework.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import storageContract.cargo.Hazard;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WarenPanelController {
    Window primaryStage;
    Scene dialogScene;
    Stage dialogStage;
    public ObservableList<String> typeSelectionData = FXCollections.observableArrayList(
            "Item", "LiquidBulkCargo", "MixedCargoLiquidBulkAndUnitised", "UnitisedCargo"
    );
    public ObservableList<String> warehouseSelectionData = FXCollections.observableArrayList();
    private EventHandler eventHandler;

    public WarenPanelController(Window primaryStage, Scene scene, EventHandler eventHandler) {
        this.primaryStage = primaryStage;
        this.dialogScene = scene;
        this.eventHandler = eventHandler;
        this.dialogStage = new Stage();

        init();
    }

    public void init() {
        ChoiceBox typeSelection = (ChoiceBox) this.dialogScene.lookup("#type");
        typeSelection.setItems(typeSelectionData);

        Button submitButton = (Button) this.dialogScene.lookup("#submit");
        submitButton.setOnMouseClicked(e -> submit());

    }

    public void open(Waren waren) throws IOException {
        TextField ownerTextInput = (TextField) this.dialogScene.lookup("#owner");
        ChoiceBox typeSelection = (ChoiceBox) this.dialogScene.lookup("#type");
        TextField waggeTextInput = (TextField) this.dialogScene.lookup("#wagge");
        CheckBox radioActiveCheckbox = (CheckBox) this.dialogScene.lookup("#hazard_radioactive");
        CheckBox toxicCheckbox = (CheckBox) this.dialogScene.lookup("#hazard_toxic");
        CheckBox fammableCheckbox = (CheckBox) this.dialogScene.lookup("#hazard_flammable");
        CheckBox explosiveCheckbox = (CheckBox) this.dialogScene.lookup("#hazard_explosive");
        CheckBox fragileCheckbox = (CheckBox) this.dialogScene.lookup("#fragile");
        CheckBox pressureCheckbox = (CheckBox) this.dialogScene.lookup("#pressure");
        TextField storeUntilTextInput = (TextField) this.dialogScene.lookup("#storeUntil");
        this.showError("");
        if(waren == null) {
            this.dialogStage.setTitle("Neue Ware hinzufügen");
            ownerTextInput.setText("");
            waggeTextInput.setText("");
            storeUntilTextInput.setText("");
            typeSelection.getSelectionModel().select(0);
            radioActiveCheckbox.setSelected(false);
            toxicCheckbox.setSelected(false);
            explosiveCheckbox.setSelected(false);
            fammableCheckbox.setSelected(false);
            fragileCheckbox.setSelected(false);
            pressureCheckbox.setSelected(false);

        }
        this.dialogStage.setScene(this.dialogScene);
        this.dialogStage.show();
    }

    public void showError(String text) {
        Label errorLabel = (Label) this.dialogScene.lookup("#error");
        errorLabel.setText(text);
        errorLabel.setTextFill(Color.web("#D8000C"));
    }

    private void submit() {
        Label errorLabel = (Label) this.dialogScene.lookup("#error");
        ChoiceBox typeSelection = (ChoiceBox) this.dialogScene.lookup("#type");

        Item item = null;
        typeSelection.getSelectionModel().getSelectedItem();


        TextField ownerTextInput = (TextField) this.dialogScene.lookup("#owner");
        TextField waggeTextInput = (TextField) this.dialogScene.lookup("#wagge");
        CheckBox radioActiveCheckbox = (CheckBox) this.dialogScene.lookup("#hazard_radioactive");
        CheckBox toxicCheckbox = (CheckBox) this.dialogScene.lookup("#hazard_toxic");
        CheckBox fammableCheckbox = (CheckBox) this.dialogScene.lookup("#hazard_flammable");
        CheckBox explosiveCheckbox = (CheckBox) this.dialogScene.lookup("#hazard_explosive");
        CheckBox fragileCheckbox = (CheckBox) this.dialogScene.lookup("#fragile");
        CheckBox pressureCheckbox = (CheckBox) this.dialogScene.lookup("#pressure");
        TextField storeUntilTextInput = (TextField) this.dialogScene.lookup("#storeUntil");
        GetUserEvent getUserEvent = new GetUserEvent();
        getUserEvent.setFilterByName(ownerTextInput.getText());
        GetUserEvent user = (GetUserEvent) eventHandler.push(getUserEvent);
        Date storeUntil = null;
        try {
            DateFormat format = new SimpleDateFormat("dd.MM.yy", Locale.ENGLISH);
            format.setLenient(false);
            storeUntil = format.parse(storeUntilTextInput.getText());
        } catch (ParseException e) {
            errorLabel.setText("Falsches Format im Datum");
            return;
        }


        ArrayList<Hazard> hazards = new ArrayList<>();
        boolean fragile = fragileCheckbox.isSelected();
        boolean pressure = pressureCheckbox.isSelected();
        if (radioActiveCheckbox.isSelected()) {
            hazards.add(Hazard.radioactive);
        }
        if (toxicCheckbox.isSelected()) {
            hazards.add(Hazard.toxic);
        }
        if (fammableCheckbox.isSelected()) {
            hazards.add(Hazard.flammable);
        }
        if (explosiveCheckbox.isSelected()) {
            hazards.add(Hazard.explosive);
        }
        if (explosiveCheckbox.isSelected()) {
            hazards.add(Hazard.explosive);
        }
        try {
            System.out.println("Selected: " + typeSelection.getSelectionModel().getSelectedItem());

            BigDecimal weight = new BigDecimal(waggeTextInput.getText());
            String warehouse = "";
            int storagePlace = 0;
            String input = (String) typeSelection.getSelectionModel().getSelectedItem();
            switch (input) {
                case "LiquidBulkCargo":
                    item = new LiquidBulkCargo(
                            weight,
                            user.getUsers().get(0),
                            hazards,
                            storeUntil,
                            warehouse,
                            storagePlace,
                            pressure
                    );
                    break;
                case "Item":
                    item = new Item(
                            weight,
                            user.getUsers().get(0),
                            hazards,
                            storeUntil,
                            warehouse,
                            storagePlace
                    );
                    break;
                case "MixedCargoLiquidBulkAndUnitised":
                    item = new MixedCargoLiquidBulkAndUnitised(
                            weight,
                            user.getUsers().get(0),
                            hazards,
                            storeUntil,
                            warehouse,
                            storagePlace,
                            pressure,
                            fragile
                    );
                    break;
                case "UnitisedCargo":
                    item = new UnitisedCargo(
                            weight,
                            user.getUsers().get(0),
                            hazards,
                            storeUntil,
                            warehouse,
                            storagePlace,
                            fragile
                    );
                    break;
                default:
                    return;
            }
            this.eventHandler.push(new StoreItemEvent(item));
            close();
        }catch (Exception exception) {
            errorLabel.setText("Fehler beim speichern");
        }


//        eventStream.pushData("warehouse:store-item", warehouseSelection.getValue() + "$$"  + newCargoInput.getData()
//                 );
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
        this.eventHandler.push(new DeleteItemEvent(id));
//        this.eventStream.pushData("app.gui:refresh-items", id);
    }
}
