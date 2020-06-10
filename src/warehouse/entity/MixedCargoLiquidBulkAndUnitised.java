package warehouse.entity;

import storageContract.administration.Customer;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;

public class MixedCargoLiquidBulkAndUnitised extends LiquidBulkCargo implements storageContract.cargo.MixedCargoLiquidBulkAndUnitised {
    private Boolean fragile;

    public MixedCargoLiquidBulkAndUnitised(
            BigDecimal weight,
            Customer owner,
            Collection<Hazard> hazards,
            ZonedDateTime expireDate,
            Boolean pressurized,
            Boolean fragile
    ) {
        super( weight, owner, hazards, expireDate, pressurized);
        this.type =  "MixedCargoLiquidBulkAndUnitised";
        this.fragile = fragile;
    }

    @Override
    public boolean isFragile() {
        return this.fragile;
    }

}
