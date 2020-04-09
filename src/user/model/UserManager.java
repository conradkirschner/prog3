package user.model;

import app.App;
import app.events.Event;
import user.entity.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Set;

public class UserManager {
    private ArrayList<Customer> user;

    public UserManager() {
        this.user = new ArrayList<Customer>();
    }

    public Customer newCustomer(String name) {
        Customer customer = new Customer(name);
        this.user.add(customer);
        return customer;
    }

    public Customer updateCustomer(int id, String name) {
        Customer customer = this.user.get(id);
        customer.setName(name);
        return customer;
    }

    public Customer getCustomer(String name) {
        for(Customer customer: user) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public Customer getCustomer(int id) {
        return this.user.get(id);
    }

    public Event deleteCustomer(int parseInt) {
        this.user.remove(this.user.get(parseInt));
        return null;
    }


}
