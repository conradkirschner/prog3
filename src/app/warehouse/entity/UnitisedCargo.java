package app.warehouse.entity;

import storageContract.administration.Customer;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;

public class UnitisedCargo extends Item implements storageContract.cargo.UnitisedCargo {

    private Boolean fragile;

    public UnitisedCargo(
            BigDecimal weight,
            Customer owner,
            Collection<Hazard> hazards,
            ZonedDateTime expireDate,
            String warehouse,
            Boolean fragile
    ) {
        super( weight, owner, hazards, expireDate, warehouse);
        this.fragile = fragile;
        this.type = "UnitisedCargo";
    }

    @Override
    public boolean isFragile() {
        return this.fragile;
    }

}
