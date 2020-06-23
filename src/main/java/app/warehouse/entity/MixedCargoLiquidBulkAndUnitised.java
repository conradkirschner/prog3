package app.warehouse.entity;

import app.user.entity.User;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class MixedCargoLiquidBulkAndUnitised extends LiquidBulkCargo implements storageContract.cargo.MixedCargoLiquidBulkAndUnitised {
    private Boolean fragile;
    protected String type = "MixedCargoLiquidBulkAndUnitised";

    public MixedCargoLiquidBulkAndUnitised(
            BigDecimal weight,
            User owner,
            ArrayList<Hazard> hazards,
            Date expireDate,
            String warehouse,
            Boolean pressurized,
            Boolean fragile
    ) {
        super( weight, owner, hazards, expireDate, warehouse, pressurized);
        this.fragile = fragile;
    }

    public MixedCargoLiquidBulkAndUnitised() {
    }

    public MixedCargoLiquidBulkAndUnitised setFragile(Boolean fragile) {
        this.fragile = fragile;
        return this;
    }
    public Boolean getFragile() {
        return fragile;
    }

    @Override
    public boolean isFragile() {
        return false;
    }
}
