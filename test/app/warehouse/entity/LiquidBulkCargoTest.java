package app.warehouse.entity;

import app.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class LiquidBulkCargoTest {

    @Mock
    private BigDecimal mockWeight;
    @Mock
    private User mockUser;
    @Mock
    private ArrayList<Hazard> mockHazards;
    @Mock
    private Date mockExpireDate;

    private LiquidBulkCargo liquidBulkCargoUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        liquidBulkCargoUnderTest = new LiquidBulkCargo(mockWeight, mockUser, mockHazards, mockExpireDate, "warehouse", 0, false);
    }

    @Test
    void testGetType() {
        final String value = "LiquidBulkCargo";
        final String result = liquidBulkCargoUnderTest.getType();

        assertEquals(value, result);
    }
    @Test
    void testSetType() {
        final String value = "Changed";
        liquidBulkCargoUnderTest.setType(value);
        final String result = liquidBulkCargoUnderTest.getType();

        assertEquals(value, result);
    }
    @Test
    void testGetPressurizedDefault() {
        final boolean value = false;
        final boolean result = liquidBulkCargoUnderTest.getPressurized();

        assertEquals(value, result);
    }
    @Test
    void testGetPressurizedChanged() {
        final boolean value = true;
        liquidBulkCargoUnderTest.setPressurized(value);
        final boolean result = liquidBulkCargoUnderTest.isPressurized();

        assertEquals(value, result);
    }
}
