package app.warehouse.entity;

import app.user.entity.User;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class LiquidBulkCargo extends Item implements storageContract.cargo.LiquidBulkCargo {
    private Boolean pressurized;

    public LiquidBulkCargo(
            BigDecimal weight,
            User user,
            Collection<Hazard> hazards,
            Date expireDate,
            String warehouse,
            Boolean pressurized
    ) {
        super(weight, user, hazards, expireDate, warehouse);

        this.type = "LiquidBulkCargo";
        this.pressurized = pressurized;
    }

    @Override
    public boolean isPressurized() {
        return this.pressurized;
    }

    public void setPressurized(Boolean pressurized) {
        this.pressurized = pressurized;
    }
}
