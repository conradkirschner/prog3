package gui.controller;

import gui.model.Waren;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class TableWarenController {
    private final TableView<Waren> warenTableView;
    private WarenContextMenu warenContextMenu;
    private WarenPanelController warenPanelController;
    public ObservableList<Waren> data = FXCollections.observableArrayList();

    public TableWarenController(TableView<Waren> warenTableView, WarenPanelController dialog) {
        this.warenTableView = warenTableView;
        this.warenPanelController = dialog;
        init();
    }

    private void init() {
        this.warenContextMenu = new WarenContextMenu(this.warenTableView, this.warenPanelController);
        this.warenTableView.setItems(this.data);
        addRow();
    }

    public void addRow() {
        data.add(new Waren(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8"
        ));    data.add(new Waren(
                "11",
                "22",
                "33",
                "43",
                "35",
                "6",
                "7",
                "8"
        ));

    }

    public void removeRow(int index) {
        data.remove(index);
    }

    public void updateRow() {

    }


}