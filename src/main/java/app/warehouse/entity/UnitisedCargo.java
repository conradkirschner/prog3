package app.warehouse.entity;

import app.user.entity.User;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class UnitisedCargo extends Item implements storageContract.cargo.UnitisedCargo {

    private Boolean fragile;

    public UnitisedCargo(
            BigDecimal weight,
            User owner,
            Collection<Hazard> hazards,
            Date expireDate,
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
