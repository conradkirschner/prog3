package app.warehouse.events;

import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

class StoreItemEventTest {

    @Mock
    private Item mockItem;
    @Mock
    private Warehouse mockWarehouse;

    private StoreItemEvent storeItemEventUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        storeItemEventUnderTest = new StoreItemEvent();
    }

    @Test
    void testGetName() {
        final String result = storeItemEventUnderTest.getName();

        assertEquals(StoreItemEvent.class.getName(), result);
    }
    @Test
    void testGetItem() {
        storeItemEventUnderTest = new StoreItemEvent(mockItem);

        final Item result = storeItemEventUnderTest.getItem();

        assertEquals(mockItem, result);
    }
    @Test
    void testGetItem1() {
        storeItemEventUnderTest = new StoreItemEvent();
        storeItemEventUnderTest.setItem(mockItem);
        final Item result = storeItemEventUnderTest.getItem();

        assertEquals(mockItem, result);
    }
    @Test
    void testGetSuccess() {
        storeItemEventUnderTest = new StoreItemEvent(mockItem, mockWarehouse, true);

        final boolean result = storeItemEventUnderTest.getSuccess();

        assertTrue(result);
    }
    @Test
    void testGetWarehouse() {
        storeItemEventUnderTest = new StoreItemEvent(mockItem, mockWarehouse, true);

        final Warehouse result = storeItemEventUnderTest.getWarehouse();

        assertEquals(mockWarehouse, result);
    }
    @Test
    void testSetWarehouse() {
        storeItemEventUnderTest = new StoreItemEvent(mockItem, mockWarehouse, true);
        storeItemEventUnderTest.setWarehouse(mockWarehouse);
        final Warehouse result = storeItemEventUnderTest.getWarehouse();

        assertEquals(mockWarehouse, result);
    }
}
