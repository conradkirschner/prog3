package gui.controller;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WarenPanelController implements Initializable {
    Window primaryStage;
    Scene dialogScene;
    final Stage dialog = new Stage();

    public WarenPanelController(Window primaryStage,Scene scene ) {
        this.primaryStage = primaryStage;
        this.dialogScene = scene;
    }

    public void open() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("New Window");
        stage.setScene(this.dialogScene);
        stage.show();
//        dialog.initModality(Modality.APPLICATION_MODAL);
//        dialog.initOwner(primaryStage);
//        VBox dialogVbox = new VBox(20);
//
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dialog.fxml"));
//        loader.setController(this);
//        DialogPane dialogPane = loader.load();
//
//        dialogVbox.getChildren().add(dialogPane);
//        Scene dialogScene = new Scene(dialogVbox, 300, 200);
//
//        dialog.setScene(dialogScene);
//        dialog.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
