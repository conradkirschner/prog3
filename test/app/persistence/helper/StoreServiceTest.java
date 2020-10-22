package app.persistence.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StoreServiceTest {

    private StoreService storeServiceUnderTest;

    @BeforeEach
    void setUp() {
        storeServiceUnderTest = new StoreService();
    }

    @Test
    void testStoreAsJOS() {
        final boolean result = storeServiceUnderTest.storeAsJOS("obj", "test.txt");

        assertTrue(result);
    }
    @Test
    void testStoreAsJOSFailure() {
        final boolean result = storeServiceUnderTest.storeAsJOS(null, null);

        assertFalse(result);
    }


}
