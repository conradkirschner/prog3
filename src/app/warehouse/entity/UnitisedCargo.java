package app.warehouse.entity;

import app.user.entity.User;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class UnitisedCargo extends Item implements storageContract.cargo.UnitisedCargo {

    private Boolean fragile;

    public UnitisedCargo(
            BigDecimal weight,
            User owner,
            ArrayList<Hazard> hazards,
            Date expireDate,
            String warehouse,
            Integer storagePlace,
            Boolean fragile
    ) {
        super( weight, owner, hazards, expireDate, warehouse, storagePlace);
        this.fragile = fragile;
        this.type = "UnitisedCargo";
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }

    public Boolean getFragile() {
        return fragile;
    }

    @Override
    public boolean isFragile() {
        return this.fragile;
    }

}
