package warehouse.entity;

import storageContract.administration.Customer;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

public class UnitisedCargo extends Item implements storageContract.cargo.UnitisedCargo {

    private Boolean fragile;

    public UnitisedCargo(
            BigDecimal weight,
            Customer owner,
            Collection<Hazard> hazards,
            ZonedDateTime expireDate,
            Boolean fragile
    ) {
        super( weight, owner, hazards, expireDate);
        this.fragile = fragile;
        this.type = "UnitisedCargo";
    }

    @Override
    public boolean isFragile() {
        return this.fragile;
    }

}
