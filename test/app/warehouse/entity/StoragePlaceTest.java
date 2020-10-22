package app.warehouse.entity;

import app.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class StoragePlaceTest {

    @Mock
    private BigDecimal mockMaxSize;

    private StoragePlace storagePlaceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        storagePlaceUnderTest = new StoragePlace(0, mockMaxSize);
    }

    @Test
    void testGetItemByIdNull() {
        final Item result = storagePlaceUnderTest.getItemById("itemId");

        assertNull(result);
    }
    @Test
    void testGetItemById() {
        Item mockedItem = mock(Item.class);
        when(mockedItem.getId()).thenReturn("itemId");
        storagePlaceUnderTest.setItem(mockedItem);
        final Item result = storagePlaceUnderTest.getItemById("itemId");

        assertEquals(mockedItem, result);
    }
    @Test
    void testGetItemByHazardYes() {
        Item mockedItem = mock(Item.class);
        ArrayList<Hazard> hazards = new ArrayList<>();
        hazards.add(Hazard.radioactive);
        when(mockedItem.getHazards()).thenReturn(hazards);
        storagePlaceUnderTest.setItem(mockedItem);
        final Item result = storagePlaceUnderTest.getItem(true);

        assertEquals(mockedItem, result);
    }
    @Test
    void testGetItemByHazardNo() {
        Item mockedItem = mock(Item.class);
        ArrayList<Hazard> hazards = new ArrayList<>();
        when(mockedItem.getHazards()).thenReturn(hazards);
        storagePlaceUnderTest.setItem(mockedItem);
        final Item result = storagePlaceUnderTest.getItem(false);

        assertEquals(mockedItem, result);
    }
    @Test
    void testSetItemWithSpecialChar() {
        Item mockedItem = mock(Item.class);
        ArrayList<Hazard> hazards = new ArrayList<>();
        when(mockedItem.getId()).thenReturn("#test");

        storagePlaceUnderTest.setItem(mockedItem);

        assertEquals("#test", storagePlaceUnderTest.getItems().get(0).getId());
    }
    @Test
    void testSetItemWithoutSpecialChar() {
        Item mockedItem = new Item(new BigDecimal(19), new User("test"), new ArrayList<Hazard>(), new Date(), "test", 10);
        mockedItem.setId("test");

        storagePlaceUnderTest.setItem(mockedItem);

        assertEquals("#test", storagePlaceUnderTest.getItems().get(0).getId());
    }

    @Test
    void testGetItemByTypeNull() {
        final Item result = storagePlaceUnderTest.getItemByType("type");

        assertNull(result);
    }

    @Test
    void testGetItemByType() {
        Item mockedItem = mock(Item.class);
        when(mockedItem.getType()).thenReturn("itemType");
        storagePlaceUnderTest.setItem(mockedItem);
        final Item result = storagePlaceUnderTest.getItemByType("itemType");

        assertEquals(mockedItem, result);
    }
    @Test
    void testGetItem() {
        final Item result = storagePlaceUnderTest.getItem(false);
        assertNull(result);
    }

    @Test
    void testSetItem() {
        final Item item = new Item(new BigDecimal("0.00"), new User("username"), new ArrayList<>(Arrays.asList(Hazard.explosive)), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "warehouse", 0);

        storagePlaceUnderTest.setItem(item);
        assertTrue(storagePlaceUnderTest.getItems().contains(item));
    }

    @Test
    void testRemoveItem() {
        Item mockedItem = mock(Item.class);
        when(mockedItem.getId()).thenReturn("itemId");
        storagePlaceUnderTest.setItem(mockedItem);

        final boolean result = storagePlaceUnderTest.removeItem("itemId");

        assertTrue(result);
    }
    @Test
    void testRemoveItemUnknown() {
        final boolean result = storagePlaceUnderTest.removeItem("itemId");

        assertFalse(result);
    }

    @Test
    void testGetUsedSize() {
        final BigDecimal result = storagePlaceUnderTest.getUsedSize();

        assertEquals(new BigDecimal("0"), result);
    }
    @Test
    void testGetUsedSizeWithItem() {
        storagePlaceUnderTest.setMaxSize(new BigDecimal(100));
        storagePlaceUnderTest.setItem(new Item(
                new BigDecimal(10),
                new User("test"),
                new ArrayList<>(),
                new Date(),
                "own",
                0
        ));

        final BigDecimal result = storagePlaceUnderTest.getUsedSize();

        assertEquals(new BigDecimal("10"), result);
    }

    @Test
    void testGetLeftSpace() {
        storagePlaceUnderTest.setMaxSize(new BigDecimal("0.00"));
        when(mockMaxSize.subtract(new BigDecimal("0.00"))).thenReturn(new BigDecimal("0.00"));


        final BigDecimal result = storagePlaceUnderTest.getLeftSpace();

        assertEquals(new BigDecimal("0.00"), result);
    }
    @Test
    void testGetStorageId() {
        storagePlaceUnderTest.setStorageID(10);
        final int result = storagePlaceUnderTest.getStorageID();

        assertEquals(10, result);
    }
    @Test
    void testGetMaxSize() {
        storagePlaceUnderTest.setMaxSize(new BigDecimal(10));
        final BigDecimal result = storagePlaceUnderTest.getMaxSize();

        assertEquals(new BigDecimal(10), result);
    }
    @Test
    void testGetItems() {
        ArrayList<Item> items = new ArrayList<>();
        storagePlaceUnderTest.setItems(items);
        final ArrayList<Item>  result = storagePlaceUnderTest.getItems();

        assertEquals(items, result);
    }
}
