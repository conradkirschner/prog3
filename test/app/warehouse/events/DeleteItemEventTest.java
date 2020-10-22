package app.warehouse.events;

import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.MockitoAnnotations.initMocks;

class DeleteItemEventTest {

    @Mock
    private ArrayList<Item> mockItem;
    @Mock
    private Warehouse mockWarehouse;

    private DeleteItemEvent deleteItemEventUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        deleteItemEventUnderTest = new DeleteItemEvent(mockItem, mockWarehouse, false);
    }

    @Test
    void testGetName() {
        final String result = deleteItemEventUnderTest.getName();

        assertEquals(DeleteItemEvent.class.getName(), result);
    }
    @Test
    void testGetIdNull() {
       final String result = deleteItemEventUnderTest.getId();

        assertNull(result);
    }
    @Test
    void testGetIdValue() {
        deleteItemEventUnderTest = new DeleteItemEvent("test");

        final String result = deleteItemEventUnderTest.getId();

        assertEquals("test", result);
    }
    @Test
    void testGetItemsNull() {
        deleteItemEventUnderTest = new DeleteItemEvent("test");

        final ArrayList<Item> result = deleteItemEventUnderTest.getItems();

        assertNull(result);
    }
    @Test
    void testGetWarehouse() {
        Warehouse warehouse = new Warehouse("test");
        deleteItemEventUnderTest = new DeleteItemEvent(null, warehouse);

        final Warehouse result = deleteItemEventUnderTest.getWarehouse();

        assertEquals(warehouse, result);
    }
    @Test
    void testGetItemsValue() {
        deleteItemEventUnderTest = new DeleteItemEvent(mockItem, mockWarehouse);


        final ArrayList<Item> result = deleteItemEventUnderTest.getItems();

        assertEquals(mockItem, result);
    }
}
