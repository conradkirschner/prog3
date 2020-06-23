package app.warehouse.entity;

import app.user.entity.User;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class MixedCargoLiquidBulkAndUnitised extends LiquidBulkCargo implements storageContract.cargo.MixedCargoLiquidBulkAndUnitised {
    private Boolean fragile;

    public MixedCargoLiquidBulkAndUnitised(
            BigDecimal weight,
            User owner,
            Collection<Hazard> hazards,
            Date expireDate,
            String warehouse,
            Boolean pressurized,
            Boolean fragile
    ) {
        super( weight, owner, hazards, expireDate, warehouse, pressurized);
        this.type =  "MixedCargoLiquidBulkAndUnitised";
        this.fragile = fragile;
    }

    @Override
    public boolean isFragile() {
        return this.fragile;
    }

    public MixedCargoLiquidBulkAndUnitised setFragile(Boolean fragile) {
        this.fragile = fragile;
        return this;
    }

    public Boolean getFragile() {
        return fragile;
    }
}
