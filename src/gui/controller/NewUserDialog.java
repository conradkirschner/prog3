package gui.controller;

import app.EventStream;
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
    private EventStream eventStream;

    public NewUserDialog(Window primaryStage, Scene dialogScene, EventStream eventStream) {
        this.primaryStage = primaryStage;
        this.dialogScene = dialogScene;
        this.eventStream = eventStream;
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
        this.eventStream.pushData("user:new", usernameTextinput.getText());
        this.stage.close();
    }
}
