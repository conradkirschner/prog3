package famework.di;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class RegistryTest {

    private Registry registryUnderTest;

    @BeforeEach
    void setUp() {
        registryUnderTest = new Registry();
    }

    @Test
    void testGetRegistered1() {
        // Setup

        // Run the test
        final Object result = registryUnderTest.getRegistered("name");

        // Verify the results
    }

    @Test
    void testAdd() {
        // Setup

        // Run the test
        final Registry result = registryUnderTest.add("obj");

        // Verify the results
    }

    @Test
    void testAdd1() {
        // Setup
        final ArrayList<Object> obj = new ArrayList<>(Arrays.asList("value"));

        // Run the test
        final Registry result = registryUnderTest.add(obj);

        // Verify the results
    }

    @Test
    void testAdd2() {
        // Setup

        // Run the test
        final Registry result = registryUnderTest.add(Object.class);

        // Verify the results
    }
}
