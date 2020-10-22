package app.warehouse.events;

import app.user.entity.User;
import app.warehouse.entity.Item;
import app.warehouse.entity.Type;
import app.warehouse.entity.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

class GetItemEventTest {

    @Mock
    private ArrayList<Item> mockItem;
    @Mock
    private Warehouse mockWarehouse;

    private GetItemEvent getItemEventUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        getItemEventUnderTest = new GetItemEvent();
    }

    @Test
    void testGetName() {
        final String result = getItemEventUnderTest.getName();

        assertEquals(GetItemEvent.class.getName(), result);
    }
    @Test
    void testGetId() {
        getItemEventUnderTest = new GetItemEvent("test");

        final String result = getItemEventUnderTest.getId();

        assertEquals("test", result);
    }
    @Test
    void testGetItems() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(new BigDecimal(20), new User("test"), new ArrayList<Hazard>(), new Date(), "test", 10));

        getItemEventUnderTest = new GetItemEvent(items);

        final ArrayList<Item> result = getItemEventUnderTest.getItems();

        assertEquals(items, result);
    }
    @Test
    void testIsSuccess() {
        getItemEventUnderTest = new GetItemEvent(null, null, true);

        final boolean result = getItemEventUnderTest.isSuccess();

        assertTrue(result);
    }
    @Test
    void testIsHazardFiltre() {
        getItemEventUnderTest = new GetItemEvent( true);

        final boolean result = getItemEventUnderTest.isHazardsFilter();

        assertTrue(result);
    }
    @Test
    void testGetWarehouse() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(new BigDecimal(20), new User("test"), new ArrayList<Hazard>(), new Date(), "test", 10));
        Warehouse warehouse = new Warehouse("test");
        getItemEventUnderTest = new GetItemEvent( items, warehouse);

        final Warehouse result = getItemEventUnderTest.getWarehouse();

        assertEquals(warehouse, result);
    }
    @Test
    void testGetSuccess() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(new BigDecimal(20), new User("test"), new ArrayList<Hazard>(), new Date(), "test", 10));
        Warehouse warehouse = new Warehouse("test");
        getItemEventUnderTest = new GetItemEvent( items, warehouse, true);

        final boolean result = getItemEventUnderTest.getSuccess();

        assertTrue(result);
    }
    @Test
    void testGetType() {
        getItemEventUnderTest = new GetItemEvent(new Type("test"));

        final Type result = getItemEventUnderTest.getType();

        assertEquals("test", result.getName());
    }
}
