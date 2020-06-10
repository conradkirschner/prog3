package gui.controller;

import gui.model.Waren;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class TableWarenController {
    private final TableView<Waren> warenTableView;
    private WarenContextMenu warenContextMenu;
    private WarenPanelController warenPanelController;
    private NewUserDialog newUserDialog;
    public ObservableList<Waren> data = FXCollections.observableArrayList();

    public TableWarenController(TableView<Waren> warenTableView, WarenPanelController dialog, NewUserDialog newUserDialog) {
        this.warenTableView = warenTableView;
        this.warenPanelController = dialog;
        this.newUserDialog = newUserDialog;
        init();
    }

    private void init() {
        this.warenContextMenu = new WarenContextMenu(this.warenTableView, this.warenPanelController, this.newUserDialog);
        this.warenTableView.setItems(this.data);
    }
}