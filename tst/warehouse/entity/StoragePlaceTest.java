package warehouse.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import storageContract.cargo.Hazard;
import warehouse.model.Warehouse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

class StoragePlaceTest {

    @Mock
    private Warehouse mockWarehouse;

    private StoragePlace storagePlaceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        storagePlaceUnderTest = new StoragePlace(mockWarehouse, 0, new BigDecimal("0.00"));
    }

    @Test
    void testSetItem() {
        final Item item = new Item(new BigDecimal("1.00"), null, Arrays.asList(Hazard.explosive), ZonedDateTime.of(LocalDateTime.of(2017, 1, 1, 0, 0, 0), ZoneId.of("Z")));

        storagePlaceUnderTest.setItem(item);
        assertEquals(1, storagePlaceUnderTest.getItems().size());
    }

    @Test
    void testRemoveItem() {
        final Item item = new Item(new BigDecimal("1.00"), null, Arrays.asList(Hazard.explosive), ZonedDateTime.of(LocalDateTime.of(2017, 1, 1, 0, 0, 0), ZoneId.of("Z")));

        storagePlaceUnderTest.setItem(item);

        final boolean result = storagePlaceUnderTest.removeItem(item.getId());

        assertEquals(0, storagePlaceUnderTest.getItems().size());
    }

    @Test
    void testGetUsedSize() {
        final BigDecimal result = storagePlaceUnderTest.getUsedSize();

        assertEquals(new BigDecimal("0"), result);
    }

    @Test
    void testGetLeftSpace() {
        final BigDecimal result = storagePlaceUnderTest.getLeftSpace();

        assertEquals(new BigDecimal("0.00"), result);
    }
}
