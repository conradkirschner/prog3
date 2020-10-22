package app.persistence.helper;

import app.user.entity.UserList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoadServiceTest {

    private LoadService loadServiceUnderTest;

    @BeforeEach
    void setUp() {
        loadServiceUnderTest = new LoadService();
    }

    @Test
    void testLoadFromJOSWithException() {



        final Object result = loadServiceUnderTest.loadFromJOS("content".getBytes());



        assertNull(result);
    }

    @Test
    void testLoadFromJOSValid() {

        byte[] userBytes = readBytes("test/app/persistence/helper/user.data");

        final Object result = loadServiceUnderTest.loadFromJOS(userBytes);



        assertTrue(result instanceof UserList);
    }

    private byte[] readBytes(String path) {
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(new File(path).toPath());
        } catch (Exception exception) {
            bytes = null;
        }
        return bytes;
    }
}
