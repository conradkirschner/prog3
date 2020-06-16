package app.user.entity;

import storageContract.administration.Customer;

import java.math.BigDecimal;
import java.time.Duration;

public class User implements Customer {
    private String username;
    private String name;
    private String maxValue;
    private String maxDurationOfStorage;

    public String getName() {
        return this.getUsername();
    }

    public BigDecimal getMaxValue() {
        return new BigDecimal(10);
    }

    public Duration getMaxDurationOfStorage() {
        return Duration.ofSeconds(-12);
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
