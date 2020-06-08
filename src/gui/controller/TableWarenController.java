package gui.controller;

import gui.model.Waren;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableWarenController {
    public final ObservableList<Waren> data =
            FXCollections.observableArrayList(
                    new Waren(
                            "Jacob",
                            "Jacob",
                            "Jacob",
                            "Jacob",
                            "Jacob",
                            "Jacob",
                            "Jacob",
                            "Jacob"
                    )
            );
}