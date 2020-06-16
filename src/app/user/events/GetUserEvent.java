package app.user.events;

import app.user.entity.User;
import famework.event.Event;

import java.util.ArrayList;

public class GetUserEvent implements Event {
    static String name = GetUserEvent.class.getName();

    ArrayList<User> users;
    private String filterByName = "";

    public String getFilterByName() {
        return filterByName;
    }

    public GetUserEvent setFilterByName(String filterByName) {
        this.filterByName = filterByName;
        return this;
    }

    public GetUserEvent(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public String getName() {
        return name;
    }
}
