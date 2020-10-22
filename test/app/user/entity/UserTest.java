package app.user.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private User userUnderTest;

    @BeforeEach
    void setUp() {
        userUnderTest = new User("username");
    }

    @Test
    void testToString() {
        final String result = userUnderTest.toString();

        assertEquals("[PersonBean: username='username']", result);
    }
    @Test
    void testGetName() {
        userUnderTest.setName("test");
        userUnderTest.setUsername("test-real-used-name");

        final String result = userUnderTest.getName();

        assertEquals("test-real-used-name", result);
    }
    @Test
    void testGetUsername() {
        userUnderTest.setUsername("test");

        final String result = userUnderTest.getUsername();

        assertEquals("test", result);
    }
    @Test
    void testGetMaxDurationOfStorage() {
        Duration duration = Duration.ofHours(1);
        userUnderTest.setMaxDurationOfStorage(duration);

        final Duration result = userUnderTest.getMaxDurationOfStorage();

        assertEquals(duration, result);
    }
    @Test
    void testGetMaxValue() {

        BigDecimal maxValue = new BigDecimal(12);
        userUnderTest.setMaxValue(maxValue);

        final BigDecimal result = userUnderTest.getMaxValue();

        assertEquals(maxValue, result);
    }
}
