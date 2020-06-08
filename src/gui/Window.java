package gui;

import gui.controller.TableWarenController;
import gui.model.Waren;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class Window extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Warenlager");
        primaryStage.setScene(scene);
        primaryStage.show();
        TableView<Waren> table = (TableView<Waren>) scene.lookup("#warenTable");
        TableWarenController tableWarenController = new TableWarenController();
        table.setItems(tableWarenController.data);
        table.getItems().add(0, new Waren(
               "Jacob",
               "Jacob",
               "Jab",
               "Jacob",
               "Jacob",
               "Jacob",
               "Jacob",
               "Jacob"
        ));
        String t = "test";
    }
}
