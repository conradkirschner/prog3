package famework.di;

import famework.event.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ServiceClass {
    public ServiceClass() {
    }
}

class RegistryTest {

    private Registry registryUnderTest;

    @BeforeEach
    void setUp() {
        registryUnderTest = new Registry();
    }

    @Test
    void testGetRegisteredNotDefined() {
        final Object result = registryUnderTest.getRegistered("not defined");
        assertEquals(null, result);
    }

    @Test
    void testAdd() {
        Event event = mock(Event.class);
        final Registry result = registryUnderTest.add(event);
        result.getRegistered(event.getClass().getName());
        assertEquals(event, result.getRegistered(event.getClass().getName()));
    }

    @Test
    void testAdd1() {
        Event event = mock(Event.class);

        final ArrayList<Object> obj = new ArrayList<>();
        obj.add(event);


        final Registry result = registryUnderTest.add(obj);
        assertEquals(event, result.getRegistered(event.getClass().getName()));
    }

    @Test
    void testAdd2() {
        ServiceClass serviceClass =(ServiceClass) mock(ServiceClass.class);

        final Registry result = registryUnderTest.add(ServiceClass.class);
        String className = serviceClass.getClass().getName().substring(
                0,
                serviceClass.getClass().getName().indexOf('$')
        );
        assertTrue(
                (result.getRegistered(className) instanceof  ServiceClass)
        );
    }
}
