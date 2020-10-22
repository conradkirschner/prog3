package app.user.events;

import app.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class GetUserEventTest {

    @Mock
    private ArrayList<User> mockUsers;

    private GetUserEvent getUserEventUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        getUserEventUnderTest = new GetUserEvent(mockUsers);
    }

    @Test
    void testGetName() {



        final String result = getUserEventUnderTest.getName();


        assertEquals(GetUserEvent.class.getName(), result);
    }
    @Test
    void testGetFilterByName() {

        getUserEventUnderTest.setFilterByName("test-NameFilter");

        final String result = getUserEventUnderTest.getFilterByName();


        assertEquals("test-NameFilter", result);
    }
}
