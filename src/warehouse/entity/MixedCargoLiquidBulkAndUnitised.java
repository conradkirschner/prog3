package warehouse.entity;

import storageContract.administration.Customer;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

public class MixedCargoLiquidBulkAndUnitised extends Item implements storageContract.cargo.MixedCargoLiquidBulkAndUnitised {
    private Boolean pressurized;
    private Boolean fragile;

    public MixedCargoLiquidBulkAndUnitised(
            BigDecimal weight,
            Customer owner,
            Collection<Hazard> hazards,
            ZonedDateTime expireDate,
            Boolean pressurized,
            Boolean fragile
    ) {
        super( weight, owner, hazards, expireDate);
        this.pressurized = pressurized;
        this.fragile = fragile;
    }

    @Override
    public boolean isPressurized() {
        return this.pressurized;
    }

    @Override
    public boolean isFragile() {
        return this.fragile;
    }

}
