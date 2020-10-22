package app.warehouse.entity;

import app.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WarehouseTest {

    private Warehouse warehouseUnderTest;

    @BeforeEach
    void setUp() {
        warehouseUnderTest = new Warehouse("id");
    }

    @Test
    void testUpdateItem() {
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<Hazard>(), new Date(), "0", 0);
        item.setId("#id");

        warehouseUnderTest.storeItem(item);
        final ArrayList<Item> result = warehouseUnderTest.updateItem("#id");

        assertEquals(item, result.get(0));
    }
    @Test
    void testUpdateItemFailure() {
        Item item = new Item(new BigDecimal(100000), new User("12"), new ArrayList<Hazard>(), new Date(), "0", 0);
        item.setId("#id");

        warehouseUnderTest.storeItem(item);
        final ArrayList<Item> result = warehouseUnderTest.updateItem("#id");

        assertEquals(0, result.size());
    }

    @Test
    void testGetItems() {
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<Hazard>(), new Date(), "0", 0);
        item.setId("#id");

        warehouseUnderTest.storeItem(item);

        final ArrayList<Item> result = warehouseUnderTest.getItems();

        assertEquals(item, result.get(0));
    }
    @Test
    void testGetItemsById() {
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<Hazard>(), new Date(), "0", 0);
        item.setId("#id");

        warehouseUnderTest.storeItem(item);

        final ArrayList<Item> result = warehouseUnderTest.getItems("#id");

        assertEquals(item, result.get(0));
    }
    @Test
    void testGetItemsByIdFalse() {
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<Hazard>(), new Date(), "0", 0);
        item.setId("#idadasda");

        warehouseUnderTest.storeItem(item);

        final ArrayList<Item> result = warehouseUnderTest.getItems("#id");

        assertEquals(0, result.size());
    }
    @Test
    void testGetItemsByBooleanTrue() {
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<>(), new Date(), "0", 0);
        ArrayList<Hazard> hazards = new ArrayList<>();
        hazards.add(Hazard.radioactive);
        item.setHazards(hazards);

        warehouseUnderTest.storeItem(item);

        final ArrayList<Item> result = warehouseUnderTest.getItems(true);

        assertEquals(item, result.get(0));
    }
    @Test
    void testGetItemsByBooleanFalse() {
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<>(), new Date(), "0", 0);
        ArrayList<Hazard> hazards = new ArrayList<>();
        hazards.add(Hazard.radioactive);
        item.setHazards(hazards);
        warehouseUnderTest.storeItem(item);

        final ArrayList<Item> result = warehouseUnderTest.getItems(false);

        assertEquals(0, result.size());
    }

    @Test
    void testGetItem() {
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<Hazard>(), new Date(), "0", 0);
        item.setId("#id");
        warehouseUnderTest.storeItem(item);

        final Item result = warehouseUnderTest.getItem("#id");

        assertEquals(item, result);
    }
    @Test
    void testGetItemFailure() {
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<Hazard>(), new Date(), "0", 0);
        item.setId("#id");
        warehouseUnderTest.storeItem(item);

        final Item result = warehouseUnderTest.getItem("#isdfsdfsfd");

        assertNull(result);
    }

    @Test
    void testGetStoragePlaces() {
        ArrayList<StoragePlace> storagePlaces = new ArrayList<>();
        storagePlaces.add(new StoragePlace(10, new BigDecimal(20)));
        warehouseUnderTest.setStoragePlaces(storagePlaces);

        final ArrayList<StoragePlace> result = warehouseUnderTest.getStoragePlaces();

        assertEquals(storagePlaces, result);
    }

    @Test
    void testGetStoragePlaceByIdNull() {
        StoragePlace storagePlace = new StoragePlace(10, new BigDecimal(10));
        ArrayList<StoragePlace> storagePlaces = new ArrayList<>();
        storagePlaces.add(storagePlace);
        warehouseUnderTest.setStoragePlaces(storagePlaces);

        final StoragePlace result = warehouseUnderTest.getStoragePlaceById(1);

        assertNull(result);
    }

    @Test
    void testGetStoragePlaceById() {
        StoragePlace storagePlace = new StoragePlace(10, new BigDecimal(10));
        ArrayList<StoragePlace> storagePlaces = new ArrayList<>();
        storagePlaces.add(storagePlace);
        warehouseUnderTest.setStoragePlaces(storagePlaces);

        final StoragePlace result = warehouseUnderTest.getStoragePlaceById(10);

        assertEquals(storagePlace, result);
    }



    @Test
    void testGetItems2() {
        final Type type = new Type("Item");
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<Hazard>(), new Date(), "0", 0);

        warehouseUnderTest.storeItem(item);


        final ArrayList<Item> result = warehouseUnderTest.getItems(type);

        assertEquals(item, result.get(0));
    }
    @Test
    void testGetItems2NotFound() {
        final Type type = new Type("asdasdsd");
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<Hazard>(), new Date(), "0", 0);

        warehouseUnderTest.storeItem(item);


        final ArrayList<Item> result = warehouseUnderTest.getItems(type);

        assertEquals(0, result.size());
    }

    @Test
    void testGetItemByTypeFound() {
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<Hazard>(), new Date(), "0", 0);
        warehouseUnderTest.storeItem(item);

        final ArrayList<Item> result = warehouseUnderTest.getItemByType("Item");

        assertEquals(item, result.get(0));

    }
    @Test
    void testGetItemByTypeNotFound() {
        Item item = new Item(new BigDecimal(10), new User("12"), new ArrayList<Hazard>(), new Date(), "0", 0);
        warehouseUnderTest.storeItem(item);

        final ArrayList<Item> result = warehouseUnderTest.getItemByType("adsaddasd");

        assertEquals(0, result.size());

    }


    @Test
    void testSwapItems() {
        StoragePlace storagePlaceA = new StoragePlace(9, new BigDecimal(10));
        StoragePlace storagePlaceB = new StoragePlace(1, new BigDecimal(10));
        ArrayList<StoragePlace> storagePlaces = new ArrayList<>();
        storagePlaces.add(storagePlaceA);
        storagePlaces.add(storagePlaceB);
        warehouseUnderTest.setStoragePlaces(storagePlaces);

        final Item itemA = new Item(new BigDecimal("6.00"), new User("username"), new ArrayList<>(Arrays.asList(Hazard.explosive)), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "warehouse", 9);
        final Item itemB = new Item(new BigDecimal("6.00"), new User("username"), new ArrayList<>(Arrays.asList(Hazard.explosive)), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "warehouse", 1);
        warehouseUnderTest.storeItem(itemA);
        warehouseUnderTest.storeItem(itemB);

        warehouseUnderTest.swapItems(itemA, itemB);

        assertEquals(1, itemA.getStoragePlace());
    }

    @Test
    void testDeleteItem() {
        Item item = mock(Item.class);
        when(item.getId()).thenReturn("id");
        when(item.getWeight()).thenReturn(new BigDecimal(10));

        StoragePlace storagePlace = mock(StoragePlace.class);
        when(storagePlace.getLeftSpace()).thenReturn(new BigDecimal(100));

        warehouseUnderTest.storeItem(item);

        final boolean result = warehouseUnderTest.deleteItem("id");

        assertTrue(result);
    }
    @Test
    void testSetId() {
        warehouseUnderTest.setId("test");

        final String result = warehouseUnderTest.getId();

        assertEquals("test", result);
    }
    @Test
    void testDeleteItemFailure() {
        Item item = mock(Item.class);
        when(item.getId()).thenReturn("id");
        when(item.getWeight()).thenReturn(new BigDecimal(10));

        StoragePlace storagePlace = mock(StoragePlace.class);
        when(storagePlace.getLeftSpace()).thenReturn(new BigDecimal(0));

        warehouseUnderTest.storeItem(item);

        final boolean result = warehouseUnderTest.deleteItem("iasdasdsadadad");

        assertFalse(result);
    }
}
