package app.user.entity;

import storageContract.administration.Customer;

import java.math.BigDecimal;
import java.time.Duration;

public class User implements Customer, UsernameImpl, java.io.Serializable{

    private String username;
    private String name;
    private BigDecimal maxValue;
    private Duration maxDurationOfStorage;
    @Override
    public String toString() {
        return String.format("[PersonBean: name='%s']", name);
    }
    public User() {
    }



    public String getName() {
        return this.getUsername();
    }

    public BigDecimal getMaxValue() {
        return this.maxValue;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public void setMaxDurationOfStorage(Duration maxDurationOfStorage) {
        this.maxDurationOfStorage = maxDurationOfStorage;
    }
}
