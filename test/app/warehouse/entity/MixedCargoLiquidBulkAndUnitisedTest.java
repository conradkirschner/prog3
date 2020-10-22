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

class MixedCargoLiquidBulkAndUnitisedTest {

    @Mock
    private BigDecimal mockWeight;
    @Mock
    private User mockOwner;
    @Mock
    private ArrayList<Hazard> mockHazards;
    @Mock
    private Date mockExpireDate;

    private MixedCargoLiquidBulkAndUnitised mixedCargoLiquidBulkAndUnitisedUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        mixedCargoLiquidBulkAndUnitisedUnderTest = new MixedCargoLiquidBulkAndUnitised(mockWeight, mockOwner, mockHazards, mockExpireDate, "warehouse", 0, false, false);
    }

    @Test
    void testGetPressurizedDefault() {
        final boolean value = false;
        final boolean result = mixedCargoLiquidBulkAndUnitisedUnderTest.getFragile();

        assertEquals(value, result);
    }
    @Test
    void testGetPressurizedChanged() {
        final boolean value = true;
        mixedCargoLiquidBulkAndUnitisedUnderTest.setFragile(value);
        final boolean result = mixedCargoLiquidBulkAndUnitisedUnderTest.isFragile();

        assertEquals(value, result);
    }
}
