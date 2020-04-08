package warehouse.entity;

import storageContract.administration.Customer;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

public class LiquidBulkCargo extends Item implements storageContract.cargo.LiquidBulkCargo {

    public LiquidBulkCargo(BigDecimal weight, Customer owner, Collection<Hazard> hazards, ZonedDateTime expireDate) {
        super(weight, owner, hazards, expireDate);
    }

    @Override
    public boolean isPressurized() {
        return false;
    }

    @Override
    public Customer getOwner() {
        return null;
    }

    @Override
    public BigDecimal getValue() {
        return this.weight;
    }

    @Override
    public Duration getDurationOfStorage() {
        return null;
    }

    @Override
    public Collection<Hazard> getHazards() {
        return null;
    }

    @Override
    public Date getLastInspectionDate() {
        return null;
    }
}
