package gui.controller;

import gui.model.User;
import javafx.scene.control.TableView;

public class TableUserController {
    private TableView<User> userTableView;

    public TableUserController(TableView<User> userTableView) {
        this.userTableView = userTableView;
    }
}