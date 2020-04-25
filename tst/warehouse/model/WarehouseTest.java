package warehouse.model;

import app.App;
import app.EventStream;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import storageContract.cargo.Hazard;
import user.entity.Customer;
import warehouse.entity.Item;
import warehouse.entity.StoragePlace;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class WarehouseTest {

    @Mock
    private EventStream mockEventStream;

    private Warehouse warehouseUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        warehouseUnderTest = new Warehouse("id", mockEventStream);
    }

    @Test
    void testSetUp() {
        warehouseUnderTest.setUp(0, 0);
        final Item item = new Item(new BigDecimal("1.00"), new Customer("TestUser"), Arrays.asList(Hazard.explosive), ZonedDateTime.of(LocalDateTime.of(2017, 1, 1, 0, 0, 0), ZoneId.of("Z")));

        assertEquals(-1, warehouseUnderTest.store(item));

    }

    @Test
    void testStore_ThrowsParseException() {
        assertThrows(IllegalArgumentException.class, () -> {
            final Item item = new Item(new BigDecimal("1.00"), null, Arrays.asList(Hazard.explosive), ZonedDateTime.of(LocalDateTime.of(2017, 1, 1, 0, 0, 0), ZoneId.of("Z")));
            warehouseUnderTest.store(item.getJson());
        });
    }

    @Test
    void testStore1() {
        warehouseUnderTest.setUp(1, 100000);

        final Item item = new Item(new BigDecimal("0.00"), new Customer("TestUser"), Arrays.asList(Hazard.explosive), ZonedDateTime.of(LocalDateTime.of(2017, 1, 1, 0, 0, 0), ZoneId.of("Z")));
        final int result = warehouseUnderTest.store(item);
        assertEquals(1, result);
    }

    @Test
    void testGetItem() {
        warehouseUnderTest.setUp(1, 100000);

        final Item item = new Item(new BigDecimal("0.00"), new Customer("TestUser"), Arrays.asList(Hazard.explosive), ZonedDateTime.of(LocalDateTime.of(2017, 1, 1, 0, 0, 0), ZoneId.of("Z")));
        warehouseUnderTest.store(item);
        final Item result = warehouseUnderTest.getItem(item.getId());
        assertEquals(item, result);
    }

    @Test
    void testGetItems() {
        warehouseUnderTest.setUp(1, 100000);

        final Item item = new Item(new BigDecimal("0.00"), new Customer("TestUser"), Arrays.asList(Hazard.explosive), ZonedDateTime.of(LocalDateTime.of(2017, 1, 1, 0, 0, 0), ZoneId.of("Z")));
        warehouseUnderTest.store(item);
        final ArrayList<Item> result = warehouseUnderTest.getItems("explosive");
        assertEquals(1, result.size());
    }

    @Test
    void testGetItems1() {
        warehouseUnderTest.setUp(1, 100000);
        final Item item = new Item(new BigDecimal("0.00"), new Customer("TestUser"), Arrays.asList(Hazard.explosive), ZonedDateTime.of(LocalDateTime.of(2017, 1, 1, 0, 0, 0), ZoneId.of("Z")));
        warehouseUnderTest.store(item);
        final ArrayList<Item> result = warehouseUnderTest.getItems();
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateItem() {
        warehouseUnderTest.setUp(1, 100000);
        final Item item = new Item(new BigDecimal("0.00"), new Customer("TestUser"), Arrays.asList(Hazard.explosive), ZonedDateTime.of(LocalDateTime.of(2017, 1, 1, 0, 0, 0), ZoneId.of("Z")));
        warehouseUnderTest.store(item);
        Date firstInspection = item.getLastInspectionDate();
        warehouseUnderTest.updateItem(item.getId());
        Date lastInspection = item.getLastInspectionDate();

        assertNotEquals(firstInspection, lastInspection);

    }

    @Test
    void testDeleteItem() {
        warehouseUnderTest.setUp(1, 100000);
        final Item item = new Item(new BigDecimal("0.00"), new Customer("TestUser"), Arrays.asList(Hazard.explosive), ZonedDateTime.of(LocalDateTime.of(2017, 1, 1, 0, 0, 0), ZoneId.of("Z")));
        warehouseUnderTest.store(item);
        final boolean result = warehouseUnderTest.deleteItem(item.getId());
        assertTrue(result);
    }
}
