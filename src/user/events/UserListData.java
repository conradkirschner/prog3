package user.events;

import app.events.Event;
import user.entity.Customer;

import java.util.ArrayList;

public class UserListData implements Event {
    private ArrayList<Customer> users;

    public UserListData(ArrayList<Customer> users) {
        this.users = users;
    }

    public ArrayList<Customer> getUsers() {
        return users;
    }

    public void addUser(Customer users) {
        this.users.add(users);
    }
}
