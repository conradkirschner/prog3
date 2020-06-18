package app.gui.controller;

import app.user.events.CreateUserEvent;
import famework.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class NewUserDialog {
    Window primaryStage;
    Scene dialogScene;
    Stage stage;
    private EventHandler eventHandler;

    public NewUserDialog(Window primaryStage, Scene dialogScene, EventHandler eventHandler) {
        this.primaryStage = primaryStage;
        this.dialogScene = dialogScene;
        this.eventHandler = eventHandler;
        this.stage = new Stage();

    }
    public void open() {
        stage.setScene(this.dialogScene);
        stage.setTitle("Neuen Nutzer hinzufügen");
        stage.show();
        Button submitButton = (Button) this.dialogScene.lookup("#submit");
        submitButton.setOnMouseClicked(eventStream -> submit());
        Label errorLabel = (Label) this.dialogScene.lookup("#error");

        errorLabel.setText("");

        System.out.println("User Dialog open");
    }

    private void submit() {
        TextField usernameTextinput = (TextField) this.dialogScene.lookup("#username");
        Label errorLabel = (Label) this.dialogScene.lookup("#error");
        if (usernameTextinput.getText().equals("")) {
            errorLabel.setText("Username sollte nicht leer sein");
            return;
        }
        this.eventHandler.push(new CreateUserEvent(usernameTextinput.getText()));
        this.stage.close();
    }
}
