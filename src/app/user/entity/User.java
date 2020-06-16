package app.user.entity;

public class User {
    private String username;
    private String name;
    private String maxValue;
    private String maxDurationOfStorage;

    public String getName() {
        return this.getUsername();
    }

    public String getMaxValue() {
        return "maxValue";
    }

    public String getMaxDurationOfStorage() {
        return "maxDurationOfStorage";
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
