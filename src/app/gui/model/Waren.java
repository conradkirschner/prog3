package app.gui.model;

import javafx.fxml.FXML;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Waren {
    @FXML
    private final String id;
    @FXML
    private final String owner;
    private final String type;
    private final String wagge;
    private final String hazzard;
    private final String fragile;
    private final String pressure;
    private final String storeUntil;
    private final String warehouse;
    private final String storagePlace;
    private final String inspectionDate;

    public Waren(
            String id,
            String owner,
            String type,
            String wagge,
            String hazzard,
            String fragile,
            String pressure,
            String storeUntil,
            String warehouse,
            String storagePlace,
            Date inspectionDate
    ) {
        this.id = id;
        this.owner = owner;
        this.type = type;
        this.wagge = wagge;
        this.hazzard = hazzard;
        this.fragile = fragile;
        this.pressure = pressure;
        this.storeUntil = storeUntil;
        this.warehouse = warehouse;
        this.storagePlace = storagePlace;
        DateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.ENGLISH);
        this.inspectionDate = format.format(inspectionDate);
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getType() {
        return type;
    }

    public String getWagge() {
        return wagge;
    }

    public String getHazzard() {
        return hazzard;
    }

    public String getFragile() {
        return fragile;
    }

    public String getPressure() {
        return pressure;
    }

    public String getStoreUntil() {
        return storeUntil;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public String getStoragePlace() {
        return storagePlace;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }
}