package app.warehouse.entity;

import storageContract.administration.Customer;
import storageContract.cargo.Cargo;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class Item implements Cargo {
    public String type = "Item";

    protected BigDecimal weight;
    protected Customer owner;
    protected Collection<Hazard> hazards;
    protected ZonedDateTime expireDate;
    protected ZonedDateTime storageDate;
    protected Date inspectDate;
    protected String id;
    protected String warehouse;

    public Item(
            BigDecimal weight,
            Customer owner,
            Collection<Hazard> hazards,
            ZonedDateTime expireDate,
            String warehouse
    ) {
        if (owner == null ) throw new IllegalArgumentException("Owner darf nicht null sein");
        UUID uuid = UUID.randomUUID();


        this.weight = weight;
        this.owner = owner;
        this.hazards = hazards;
        this.expireDate = expireDate;
        this.storageDate = ZonedDateTime.now();
        this.inspectDate = new Date();
        this.id = uuid.toString();
        this.warehouse = warehouse;
    }

    @Override
    public Customer getOwner() {
        return this.owner;
    }

    @Override
    public BigDecimal getValue() {
        return this.weight;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Duration getDurationOfStorage() {
        return Duration.between(ZonedDateTime.now(), expireDate);
    }

    @Override
    public Collection<Hazard> getHazards() {
        return this.hazards;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public void setHazards(Collection<Hazard> hazards) {
        this.hazards = hazards;
    }

    public void setExpireDate(ZonedDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public void setStorageDate(ZonedDateTime storageDate) {
        this.storageDate = storageDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

    @Override
    public Date getLastInspectionDate() {
        return this.inspectDate;
    }

    public String getId() {
        return this.id;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
    public String getWarehouse() {
        return this.warehouse;
    }
}
