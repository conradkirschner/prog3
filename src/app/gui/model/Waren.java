package app.gui.model;

import javafx.fxml.FXML;

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

    public Waren(
            String id,
            String owner,
            String type,
            String wagge,
            String hazzard,
            String fragile,
            String pressure,
            String storeUntil,
            String warehouse
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
}