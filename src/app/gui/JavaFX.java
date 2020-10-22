package app.gui;

import famework.annotation.Service;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Service
public class JavaFX {
    public Stage newStage() {
        return new Stage();
    }
    public static Scene newScene(Parent parent) {
        return new Scene(parent);
    }
}
