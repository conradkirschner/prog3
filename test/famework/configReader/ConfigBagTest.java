package famework.configReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigBagTest {

    private ConfigBag configBagUnderTest;

    @BeforeEach
    void setUp() {
        configBagUnderTest = new ConfigBag(new Properties());
    }

    @Test
    void testConstruktor() {
        assertTrue((configBagUnderTest.props != null));
    }
}
