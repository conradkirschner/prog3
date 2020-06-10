import app.App;
import app.Bootstrap;
import gui.controller.TableUserController;
import gui.controller.TableWarenController;
import gui.controller.WarenPanelController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static app.Bootstrap.setup;

/**
 * Start GUI App
 */
public class Window extends Application {
    public TableWarenController tableWarenController;
    public TableUserController tableUserController;
    public WarenPanelController warenPanelController;


    public void run(String[] args) throws ClassNotFoundException {
       launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setOnCloseRequest(windowEvent -> System.exit(0));
        BufferedReader cliInput = new BufferedReader(new InputStreamReader(System.in));
        PrintStream output = System.out;
        App app = setup(cliInput, output);
        Bootstrap.run(app, false, true);
        app.Module appModule = (app.Module) app.getModule("event-stream");
        gui.Module gui = (gui.Module) app.getModule("gui");
        gui.getModule().setEventStream(appModule.eventStream);
        gui.getModule().start(primaryStage);



    }
}
