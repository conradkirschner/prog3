package app.warehouse.entity;

import app.user.entity.User;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;

public class MixedCargoLiquidBulkAndUnitised extends LiquidBulkCargo implements storageContract.cargo.MixedCargoLiquidBulkAndUnitised {
    private Boolean fragile;

    public MixedCargoLiquidBulkAndUnitised(
            BigDecimal weight,
            User owner,
            Collection<Hazard> hazards,
            ZonedDateTime expireDate,
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

}
