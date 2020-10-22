package app.gui.controller;

import app.gui.model.Waren;
import app.warehouse.entity.Item;
import app.warehouse.entity.LiquidBulkCargo;
import app.warehouse.entity.MixedCargoLiquidBulkAndUnitised;
import app.warehouse.entity.UnitisedCargo;
import app.warehouse.events.SwapStoragePlace;
import famework.event.EventHandler;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class TableWarenController {
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    private final TableView warenTableView;
    private WarenPanelController warenPanelController;
    private app.gui.controller.NewUserDialog newUserDialog;
    private app.gui.controller.WarenContextMenu warenContextMenu;

    private EventHandler eventHandler;

    public TableWarenController(TableView warenTableView, WarenPanelController dialog, NewUserDialog newUserDialog, EventHandler eventHandler) {
        this.warenTableView = warenTableView;
        this.warenPanelController = dialog;
        this.newUserDialog = newUserDialog;
        this.eventHandler = eventHandler;
        init();
    }

    private void init() {
        this.warenContextMenu = new WarenContextMenu(this.warenTableView, this.warenPanelController, this.newUserDialog);
        this.warenTableView.setRowFactory(tv -> {
            TableRow<Waren> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (! row.isEmpty()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    int dropIndex = row.getIndex();
                    this.eventHandler.push(
                            new SwapStoragePlace(
                                ((Waren) this.warenTableView.getItems().get(draggedIndex)).getId(),
                                ((Waren) this.warenTableView.getItems().get(dropIndex)).getId(),
                                ((Waren) this.warenTableView.getItems().get(draggedIndex)).getWarehouse()
                            )
                    );
                    event.consume();
                }
            });

            return row ;
        });
    }

    public void refresh() {
        this.warenTableView.refresh();
        System.out.println(this.warenTableView.getItems().size());

    }
    public void addItem(Item item) {
        if (item instanceof MixedCargoLiquidBulkAndUnitised) {
            this.warenTableView.getItems().add(
                    new Waren(
                            item.getId(),
                            item.getOwner().getName(),
                            "MixedCargoLiquidBulkAndUnitised",
                            item.getWeight().toString(),
                            item.getHazards().toString(),
                            String.valueOf(((MixedCargoLiquidBulkAndUnitised) item).isFragile()),
                            String.valueOf(((MixedCargoLiquidBulkAndUnitised) item).isPressurized()),
                            item.getExpireDate().toString(),
                            item.getWarehouse(),
                            item.getStoragePlace().toString(),
                            item.getLastInspectionDate()
                    )
            );
            this.refresh();
            return;
        }
        if (item instanceof UnitisedCargo) {
            this.warenTableView.getItems().add(
                    new Waren(
                            item.getId(),
                            item.getOwner().getName(),
                            item.getType(),
                            item.getWeight().toString(),
                            item.getHazards().toString(),
                            String.valueOf(((UnitisedCargo) item).isFragile()),
                            "-",
                            item.getExpireDate().toString(),
                            item.getWarehouse(),
                            item.getStoragePlace().toString(),
                            item.getLastInspectionDate()
                    )
            );
            this.refresh();
            return;
        }
        if (item instanceof LiquidBulkCargo) {
            this.warenTableView.getItems().add(
                    new Waren(
                            item.getId(),
                            item.getOwner().getName(),
                            item.getType(),
                            item.getWeight().toString(),
                            item.getHazards().toString(),
                            "-",
                            String.valueOf(((LiquidBulkCargo) item).isPressurized()),
                            item.getExpireDate().toString(),
                            item.getWarehouse(),
                            item.getStoragePlace().toString(),
                            item.getLastInspectionDate()
                    )
            );
            this.refresh();
            return;
        }
        if (item instanceof Item) {
            this.warenTableView.getItems().add(
                    new Waren(
                            item.getId(),
                            item.getOwner().getName(),
                            item.getType(),
                            item.getWeight().toString(),
                            item.getHazards().toString(),
                            "-",
                           "-",
                            item.getExpireDate().toString(),
                            item.getWarehouse(),
                            item.getStoragePlace().toString(),
                            item.getLastInspectionDate()
                    )
            );
            this.refresh();
            return;
        }
        System.out.println(this.warenTableView.getItems().size());
    }

    public void removeItem() {
        this.warenTableView.getItems().clear();

    }
}