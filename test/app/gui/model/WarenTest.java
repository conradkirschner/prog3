package app.gui.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WarenTest {

    private Waren warenUnderTest;

    @BeforeEach
    void setUp() {
        Date date = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        warenUnderTest = new Waren("id", "owner", "type", "wagge", "hazzard", "fragile", "pressure", "storeUntil", "warehouse", "storagePlace", date);
    }
    @Test
    void testGetId() {
        assertEquals("id", warenUnderTest.getId());
    }
    @Test
    void testGetOwner() {
        assertEquals("owner", warenUnderTest.getOwner());
    }
    @Test
    void testGetType() {
        assertEquals("type", warenUnderTest.getType());
    }
    @Test
    void testGetWagge() {
        assertEquals("wagge", warenUnderTest.getWagge());
    }
    @Test
    void testGetHazzard() {
        assertEquals("hazzard", warenUnderTest.getHazzard());
    }
    @Test
    void testGetFragile() {
        assertEquals("fragile", warenUnderTest.getFragile());
    }
    @Test
    void testGetPressure() {
        assertEquals("pressure", warenUnderTest.getPressure());
    }
    @Test
    void testGetStoreUntil() {
        assertEquals("storeUntil", warenUnderTest.getStoreUntil());
    }
    @Test
    void testGetWarehouse() {
        assertEquals("warehouse", warenUnderTest.getWarehouse());
    }
    @Test
    void testGetStoragePlace() {
        assertEquals("storagePlace", warenUnderTest.getStoragePlace());
    }
    @Test
    void testGetInspectionDate() {
        Date date = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        warenUnderTest = new Waren("id", "owner", "type", "wagge", "hazzard", "fragile", "pressure", "storeUntil", "warehouse", "storagePlace", date);

        DateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.ENGLISH);
        String dateString = format.format(date);
        assertEquals(dateString, warenUnderTest.getInspectionDate());
    }
}
