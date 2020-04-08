package user.entity;

import app.events.Event;

import java.math.BigDecimal;
import java.time.Duration;

public class Customer implements storageContract.administration.Customer, Event {
    String name;
    BigDecimal Value;
//    Duration maxDurationOfStorage;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public BigDecimal getMaxValue() {
        return null;
    }

    @Override
    public Duration getMaxDurationOfStorage() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }
}
