package app.warehouse.events;

import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

class UpdateItemEventTest {

    @Mock
    private Item mockItem;
    @Mock
    private Warehouse mockWarehouse;

    private UpdateItemEvent updateItemEventUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        updateItemEventUnderTest = new UpdateItemEvent();
    }

    @Test
    void testGetName() {
        final String result = updateItemEventUnderTest.getName();

        assertEquals(UpdateItemEvent.class.getName(), result);
    }
    @Test
    void testGetItem() {
        updateItemEventUnderTest = new UpdateItemEvent(mockItem);

        final Item result = updateItemEventUnderTest.getItem();

        assertEquals(mockItem, result);
    }
    @Test
    void testGetId() {
        updateItemEventUnderTest = new UpdateItemEvent("test");

        final String result = updateItemEventUnderTest.getId();

        assertEquals("test", result);
    }
    @Test
    void testGetWarehouse() {
        updateItemEventUnderTest = new UpdateItemEvent(mockItem, mockWarehouse);

        final Warehouse result = updateItemEventUnderTest.getWarehouse();

        assertEquals(mockWarehouse, result);
    }
    @Test
    void testGetitems() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(mockItem);
        updateItemEventUnderTest = new UpdateItemEvent(items);

        assertEquals(updateItemEventUnderTest, updateItemEventUnderTest);
    }
    @Test
    void testGetSuccess() {
        updateItemEventUnderTest = new UpdateItemEvent(mockItem, mockWarehouse,true);

        final boolean result = updateItemEventUnderTest.getSuccess();

        assertTrue(result);
    }
}
