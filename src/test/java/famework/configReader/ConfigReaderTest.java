package famework.configReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigReaderTest {

    private ConfigReader configReaderUnderTest;

    @BeforeEach
    void setUp() {
        configReaderUnderTest = new ConfigReader();
    }

    @Test
    void testGetConfig() {
        // Setup
        final Properties expectedResult = new Properties();

        // Run the test
        final Properties result = configReaderUnderTest.getConfig(Object.class);

        // Verify the results
        assertTrue((result instanceof Properties));
    }
}
