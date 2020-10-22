package app.warehouseDistributor.events;

import app.warehouse.events.CreateWarehouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class ActivateWarehouseEventTest {

    @Mock
    private ArrayList<String> mockWarehouseIds;

    private ActivateWarehouseEvent activateWarehouseEventUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        activateWarehouseEventUnderTest = new ActivateWarehouseEvent(mockWarehouseIds);
    }

    @Test
    void testGetName() {



        final String result = activateWarehouseEventUnderTest.getName();


        assertEquals(CreateWarehouseEvent.class.getName(), result);
    }
    @Test
    void testGetWarehouseIds() {

        ArrayList<String> warehouseIds = new ArrayList<>();
        activateWarehouseEventUnderTest = new ActivateWarehouseEvent(warehouseIds);
        final ArrayList<String> result = activateWarehouseEventUnderTest.getWarehouseIds();


        assertEquals(warehouseIds, result);
    }
}
