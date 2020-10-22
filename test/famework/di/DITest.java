package famework.di;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DITest {

    private DI diUnderTest;

    @Test
    void getRegistryTest() {
        diUnderTest = new DI();
        assertTrue((diUnderTest.getRegistry() instanceof Registry));
    }
}
