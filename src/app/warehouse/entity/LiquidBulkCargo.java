package app.warehouse.entity;

import storageContract.administration.Customer;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;

public class LiquidBulkCargo extends Item implements storageContract.cargo.LiquidBulkCargo {
    private Boolean pressurized;

    public LiquidBulkCargo(
            BigDecimal weight,
            Customer owner,
            Collection<Hazard> hazards,
            ZonedDateTime expireDate,
            String warehouse,
            Boolean pressurized
    ) {
        super(weight, owner, hazards, expireDate, warehouse);

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
