package user.model;

import app.events.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.entity.Customer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserManagerTest {

    private UserManager userManagerUnderTest;

    @BeforeEach
    void setUp() {
        userManagerUnderTest = new UserManager();
    }

    @Test
    void testNewCustomer() {
        final Customer result = userManagerUnderTest.newCustomer("name");
        assertEquals(1, userManagerUnderTest.getCustomers().size());
    }

    @Test
    void testUpdateCustomer() {

        userManagerUnderTest.newCustomer("original");

        final Customer result = userManagerUnderTest.updateCustomer(0, "changed");
        assertEquals(result, userManagerUnderTest.getCustomer("changed"));
    }

    @Test
    void testGetCustomerIfNoCustomerIsSet() {
        final Customer result = userManagerUnderTest.getCustomer("name");
        assertNull(result);
    }
    @Test
    void testGetCustomerWithCustomer() {
        final Customer customer = userManagerUnderTest.newCustomer("name");
        final Customer result = userManagerUnderTest.getCustomer("name");
        assertEquals(customer, result);
    }

    @Test
    void testGetCustomer1() {
        final Customer customer = userManagerUnderTest.newCustomer("name");
        final Customer result = userManagerUnderTest.getCustomer(0);
        assertEquals(customer, result);
    }

    @Test
    void testGetCustomers() {
        final ArrayList<Customer> result = userManagerUnderTest.getCustomers();
        assertEquals(0, result.size());
    }

    @Test
    void testDeleteCustomer() {
        userManagerUnderTest.newCustomer("name");
        userManagerUnderTest.deleteCustomer("name");
        final ArrayList<Customer> result = userManagerUnderTest.getCustomers();
        assertEquals(0, result.size());

    }
}
