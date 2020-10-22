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

        final Properties expectedResult = new Properties();


        final Properties result = configReaderUnderTest.getConfig(Object.class);


        assertTrue((result instanceof Properties));
    }
}
