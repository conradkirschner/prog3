package app.user.entity;

import storageContract.administration.Customer;

import java.math.BigDecimal;
import java.time.Duration;

public class User implements Customer, UsernameImpl{

    private String username;
    private String name;
    private String maxValue;
    private String maxDurationOfStorage;

    public User() {
    }

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

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setMaxValue(String maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public User setMaxDurationOfStorage(String maxDurationOfStorage) {
        this.maxDurationOfStorage = maxDurationOfStorage;
        return this;
    }
}
