package gui.controller;

// Program to create a context menu and add it to label 
// and associate the context menu with window event listener 
import gui.model.Waren;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

import java.io.IOException;

public class WarenContextMenu {
    TableView<Waren> tableView;
    WarenPanelController warenPanelController;

    public WarenContextMenu(TableView<Waren> tableView, WarenPanelController warenPanelController) {
        this.tableView = tableView;
        this.warenPanelController = warenPanelController;
        init();
    }
    public void setContextMenu(TableRow tableRow)
    {
        // create a menu
        ContextMenu contextMenu = new ContextMenu();

        // create menuitems
        MenuItem menuItem1 = new MenuItem("Item updaten");
        MenuItem menuItem2 = new MenuItem("Item entfernen");

        // add menu items to menu
        contextMenu.getItems().add(menuItem1);
        contextMenu.getItems().add(menuItem2);
        menuItem1.setOnAction(event -> {
            Waren waren = (Waren) tableRow.getItem();
            try {
                this.warenPanelController.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
            event.consume();
        });
        tableRow.setContextMenu(contextMenu);
    }

    public void init()
    {
        this.tableView.setRowFactory(tv -> {
            TableRow<Waren> row = new TableRow<>();
            this.setContextMenu(row);

            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.SECONDARY) {
                    Waren clickedRow = row.getItem();

                }
            });
            return row ;
        });

    }
} 