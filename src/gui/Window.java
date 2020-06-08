package gui;

import gui.controller.TableUserController;
import gui.controller.TableWarenController;
import gui.controller.WarenPanelController;
import gui.model.User;
import gui.model.Waren;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class Window extends Application {
    public TableWarenController tableWarenController;
    public TableUserController tableUserController;
    public WarenPanelController warenPanelController;


    public void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent dialog = FXMLLoader.load(getClass().getResource("view/dialog.fxml"));
        Scene dialogScene = new Scene(dialog);
        this.warenPanelController = new WarenPanelController(primaryStage, dialogScene);

        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Warenlager");
        primaryStage.setScene(scene);
        primaryStage.show();
        TableView<Waren> warenTable = (TableView<Waren>) scene.lookup("#warenTable");
        TableView<User> userTable = (TableView<User>) scene.lookup("#userTable");
        this.tableWarenController = new TableWarenController(warenTable, this.warenPanelController);
        this.tableUserController = new TableUserController(userTable);


    }

}
