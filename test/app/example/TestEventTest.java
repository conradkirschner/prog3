package app.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestEventTest {

    private TestEvent testEventUnderTest;

    @BeforeEach
    void setUp() {
        testEventUnderTest = new TestEvent();
    }

    @Test
    void testGetName() {
        final String result = testEventUnderTest.getName();

        assertEquals(TestEvent.class.getName(), result);
    }

    @Test
    void testCreate() {
        final TestEvent result = TestEvent.create();

        assertEquals(TestEvent.class.getName(), result.getClass().getName());
    }
}
