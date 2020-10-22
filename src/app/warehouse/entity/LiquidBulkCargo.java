package app.warehouse.entity;

import app.user.entity.User;
import storageContract.cargo.Hazard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class LiquidBulkCargo extends Item implements Serializable, storageContract.cargo.LiquidBulkCargo {
    private Boolean pressurized;
    protected String type = "LiquidBulkCargo";

    public LiquidBulkCargo(
            BigDecimal weight,
            User user,
            ArrayList<Hazard> hazards,
            Date expireDate,
            String warehouse,
            Integer storagePlace,
            Boolean pressurized
    ) {
        super(weight, user, hazards, expireDate, warehouse, storagePlace);
        this.pressurized = pressurized;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public Boolean getPressurized() {
        return pressurized;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isPressurized() {
        return this.pressurized;
    }

    public void setPressurized(Boolean pressurized) {
        this.pressurized = pressurized;
    }
}
