package app.warehouse.entity;

import app.user.entity.User;
import storageContract.cargo.Cargo;
import storageContract.cargo.Hazard;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class Item implements Cargo {
    @XmlElement(name="type")
    public String type = "Item";
    @XmlElement(name="weight")
    protected BigDecimal weight;
    @XmlElement(name="user")
    protected User user;
    protected Collection<Hazard> hazards;
    protected ZonedDateTime expireDate;
    protected ZonedDateTime storageDate;
    protected Date inspectDate;
    protected String id;
    protected String warehouse;

    public Item(
            BigDecimal weight,
            User user,
            Collection<Hazard> hazards,
            ZonedDateTime expireDate,
            String warehouse
    ) {
        if (user == null ) throw new IllegalArgumentException("Owner darf nicht null sein");
        UUID uuid = UUID.randomUUID();


        this.weight = weight;
        this.user = user;
        this.hazards = hazards;
        this.expireDate = expireDate;
        this.storageDate = ZonedDateTime.now();
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
        return Duration.between(ZonedDateTime.now(), expireDate);
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

    public void setExpireDate(ZonedDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public void setStorageDate(ZonedDateTime storageDate) {
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
}
