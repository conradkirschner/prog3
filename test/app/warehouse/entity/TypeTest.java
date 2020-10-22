package app.warehouse.entity;

import org.junit.jupiter.api.BeforeEach;

class TypeTest {

    private Type typeUnderTest;

    @BeforeEach
    void setUp() {
        typeUnderTest = new Type("name");
    }
}
