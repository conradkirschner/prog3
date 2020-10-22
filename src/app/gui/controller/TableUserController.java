package app.gui.controller;

import app.gui.model.User;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableUserController {
    private TableView userTableView;

    public TableUserController(TableView userTableView) {
        this.userTableView = userTableView;

        TableColumn<User, String> usernameColum = new TableColumn<>("Username");
        usernameColum.setCellValueFactory(new PropertyValueFactory<>("username"));
        this.userTableView.getColumns().add(usernameColum);

    }

    public void refresh() {
        this.userTableView.refresh();
        System.out.println(this.userTableView.getItems().size());

    }
    public void addUser(String username) {
        this.userTableView.getItems().add(new User(username));
        this.refresh();
    }

    public void removeUser() {
        this.userTableView.getItems().clear();

    }
}
