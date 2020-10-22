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

class UnitisedCargoTest {

    @Mock
    private BigDecimal mockWeight;
    @Mock
    private User mockUser;
    @Mock
    private ArrayList<Hazard> mockHazards;
    @Mock
    private Date mockExpireDate;

    private UnitisedCargo UnitisedCargoUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        UnitisedCargoUnderTest = new UnitisedCargo(mockWeight, mockUser, mockHazards, mockExpireDate, "warehouse", 0, false);
    }


    @Test
    void testGetFragileDefault() {
        final boolean value = false;
        final boolean result = UnitisedCargoUnderTest.getFragile();

        assertEquals(value, result);
    }
    @Test
    void testGetFragileChanged() {
        final boolean value = true;
        UnitisedCargoUnderTest.setFragile(value);
        final boolean result = UnitisedCargoUnderTest.isFragile();

        assertEquals(value, result);
    }
}
