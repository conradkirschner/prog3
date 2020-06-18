package app.gui.controller;

// Program to create a context menu and add it to label 
// and associate the context menu with window event listener 
import app.gui.model.Waren;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

import java.io.IOException;

public class WarenContextMenu {
    TableView<Waren> tableView;
    WarenPanelController warenPanelController;
    NewUserDialog newUserDialog;

    public WarenContextMenu(TableView<Waren> tableView, WarenPanelController warenPanelController, NewUserDialog newUserDialog) {
        this.tableView = tableView;
        this.warenPanelController = warenPanelController;
        this.newUserDialog = newUserDialog;
        init();
    }

    public void setContextMenu(TableView tableView) {
        ContextMenu contextMenu = new ContextMenu();
        tableView.setContextMenu(contextMenu);
        MenuItem menuItem1 = new MenuItem("Item hinzufügen");
        MenuItem menuItem2 = new MenuItem("User hinzufügen");
        contextMenu.getItems().add(menuItem1);
        contextMenu.getItems().add(menuItem2);
        // new Item
        menuItem1.setOnAction(event -> {
            try {
                this.warenPanelController.open(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            event.consume();
        });
        // add user
        menuItem2.setOnAction(event -> {
            this.newUserDialog.open();
            event.consume();
        });
    }
    public void setContextMenuToRow(TableRow tableRow) {
        // create a menu
        ContextMenu contextMenu = new ContextMenu();

        // create menuitems
        MenuItem menuItem1 = new MenuItem("Item hinzufügen");
//        MenuItem menuItem2 = new MenuItem("Item updaten");
        MenuItem menuItem3 = new MenuItem("Item entfernen");
        MenuItem menuItem4 = new MenuItem("User hinzufügen");

        // add menu items to menu
        contextMenu.getItems().add(menuItem1);
//        contextMenu.getItems().add(menuItem2);
        contextMenu.getItems().add(menuItem3);
        contextMenu.getItems().add(menuItem4);

        // edit Item
//        menuItem2.setOnAction(event -> {
//            Waren waren = (Waren) tableRow.getItem();
//            try {
//                this.warenPanelController.open(waren);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            event.consume();
//        });
        // delete Item
        menuItem3.setOnAction(event -> {
            Waren waren = (Waren) tableRow.getItem();
            this.warenPanelController.removeItem(waren.getId());
            event.consume();
        });
        // new Item
        menuItem1.setOnAction(event -> {
            try {
                this.warenPanelController.open(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            event.consume();
        });
        // add user
        menuItem4.setOnAction(event -> {
            this.newUserDialog.open();
            event.consume();
        });
        tableRow.setContextMenu(contextMenu);
    }

    public void init()
    {
        setContextMenu(this.tableView);
        this.tableView.setRowFactory(tv -> {
            TableRow<Waren> row = new TableRow<>();
            this.setContextMenuToRow(row);

            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.SECONDARY) {
                    Waren clickedRow = row.getItem();

                }
            });
            return row ;
        });

    }


} 