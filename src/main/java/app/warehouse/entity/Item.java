package app.warehouse.entity;

import app.user.entity.User;
import storageContract.cargo.Cargo;
import storageContract.cargo.Hazard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class Item implements Cargo, Serializable {
    public String type = "Item";
    protected BigDecimal weight;
    protected User user;
    protected Collection<Hazard> hazards;
    protected Date expireDate;
    protected Date storageDate;
    protected Date inspectDate;
    protected String id;
    protected String warehouse;

    public Item() {
    }

    public Item(
            BigDecimal weight,
            User user,
            Collection<Hazard> hazards,
            Date expireDate,
            String warehouse
    ) {
        if (user == null ) throw new IllegalArgumentException("Owner darf nicht null sein");
        UUID uuid = UUID.randomUUID();


        this.weight = weight;
        this.user = user;
        this.hazards = hazards;
        this.expireDate = expireDate;
        this.storageDate = new Date();
        this.inspectDate = new Date();
        this.id = uuid.toString();
        this.warehouse = warehouse;
    }

    public User getOwner() {
        return this.user;
    }

    public BigDecimal getValue() {
        return this.weight;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Duration getDurationOfStorage() {
        Instant timestamp = new Date().toInstant();
        Instant expireTimestamp =expireDate.toInstant();
        return Duration.between(timestamp, expireTimestamp);
    }

    public Collection<Hazard> getHazards() {
        return this.hazards;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public void setOwner(User user) {
        this.user = user;
    }

    public void setHazards(Collection<Hazard> hazards) {
        this.hazards = hazards;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

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

    public String getType() {
        return type;
    }

    public Item setType(String type) {
        this.type = type;
        return this;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public User getUser() {
        return user;
    }

    public Item setUser(User user) {
        this.user = user;
        return this;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public Date getStorageDate() {
        return storageDate;
    }

    public Date getInspectDate() {
        return inspectDate;
    }
}
