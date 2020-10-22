package app.warehouse.entity;

import app.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ItemTest {

    @Mock
    private BigDecimal mockWeight;
    @Mock
    private User mockUser;
    @Mock
    private ArrayList<Hazard> mockHazards;
    @Mock
    private Date mockExpireDate;

    private Item itemUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        itemUnderTest = new Item(mockWeight, mockUser, mockHazards, mockExpireDate, "warehouse", 0);
    }

    @Test
    void testGetOwner() {
        final User result = itemUnderTest.getOwner();

        assertEquals(mockUser, result);
    }

    @Test
    void testGetValue() {
        final BigDecimal result = itemUnderTest.getValue();


        assertEquals(mockWeight, result);
    }

    @Test
    void testGetDurationOfStorage() {
        final Duration expectedResult = Duration.ofDays(0L);
        when(mockExpireDate.toInstant()).thenReturn(Instant.ofEpochSecond(0L));

        final Duration result = itemUnderTest.getDurationOfStorage();

        assertTrue(expectedResult instanceof Duration);
    }

    @Test
    void testSetOwner() {

        final User user = new User("username");


        itemUnderTest.setOwner(user);


    }

    @Test
    void testGetLastInspectionDate() {
        final Date value = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        itemUnderTest.setInspectDate(value);
        final Date result = itemUnderTest.getLastInspectionDate();

        assertEquals(value, result);
    }
    @Test
    void testGetWeight() {
        final BigDecimal value = new BigDecimal(20);
        itemUnderTest.setWeight(value);
        final BigDecimal result = itemUnderTest.getWeight();

        assertEquals(value, result);
    }
    @Test
    void testGetId() {
        final String value = "itemId";
        itemUnderTest.setId(value);
        final String result = itemUnderTest.getId();

        assertEquals(value, result);
    }
    @Test
    void testGetHazards() {
        final ArrayList<Hazard> value = new ArrayList<>();
        value.add(Hazard.radioactive);

        itemUnderTest.setHazards(value);
        final ArrayList<Hazard> result = itemUnderTest.getHazards();

        assertEquals(value, result);
    }
    @Test
    void testGetExpireDate() {
        final Date value = new Date();
        itemUnderTest.setExpireDate(value);
        final Date result = itemUnderTest.getExpireDate();

        assertEquals(value, result);
    }
    @Test
    void testGetStorageDate() {
        final Date value = new Date();
        itemUnderTest.setStorageDate(value);
        final Date result = itemUnderTest.getStorageDate();

        assertEquals(value, result);
    }
    @Test
    void testGetStoragePlace() {
        final int value = 0;
        itemUnderTest.setStoragePlace(value);
        final int result = itemUnderTest.getStoragePlace();

        assertEquals(value, result);
    }
    @Test
    void testGetWarehouse() {
        final String value = "test";
        itemUnderTest.setWarehouse(value);
        final String result = itemUnderTest.getWarehouse();

        assertEquals(value, result);
    }
    @Test
    void testGetUser() {
        final User value = new User("test");
        itemUnderTest.setUser(value);
        final User result = itemUnderTest.getUser();

        assertEquals(value, result);
    }
    @Test
    void testGetInspectionDate() {
        final Date value = new Date();
        itemUnderTest.setInspectDate(value);
        final Date result = itemUnderTest.getInspectDate();

        assertEquals(value, result);
    }
    @Test
    void testGetType() {
        final String value = "test";
        itemUnderTest.setType(value);
        final String result = itemUnderTest.getType();

        assertEquals(value, result);
    }
}
