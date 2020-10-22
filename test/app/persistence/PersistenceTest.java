package app.persistence;

import app.persistence.helper.LoadService;
import app.persistence.helper.StoreService;
import app.user.entity.User;
import app.user.entity.UserList;
import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import app.warehouse.entity.WarehouseList;
import app.warehouse.events.GetItemEvent;
import app.warehouse.events.GetWarehouseEvent;
import famework.configReader.ConfigBag;
import famework.event.EventHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PersistenceTest {

    @Mock
    private ConfigBag mockConfigBag;

    @Mock
    private LoadService mockLoadService;
    @Mock
    private EventHandler eventHandler;

    @Mock
    private StoreService mockStoreService;
    @Mock
    private WarehouseList warehouseList;

    private Persistence persistenceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        initMocks(this);
        mockConfigBag.props = mock(Properties.class);
        when(mockConfigBag.props.getProperty("user")).thenReturn("/data/user");
        when(mockConfigBag.props.getProperty("items")).thenReturn("/data/items");
        when(mockConfigBag.props.getProperty("warehouse")).thenReturn("/data/warehouse");
        persistenceUnderTest = new Persistence(
                mockLoadService,
                eventHandler,
                mockStoreService,
                mockConfigBag
        );
    }

    @Test
    void testSaveAsJOS() {
        ArrayList<User> userList = new ArrayList<>();
        User user = new User("test");
        userList.add(user);
        ArrayList<Item> itemList = new ArrayList<>();
        ArrayList<Warehouse> warehouseList = new ArrayList<>();
        Warehouse warehouse = new Warehouse("test");
        warehouseList.add(warehouse);
        itemList.add(new Item(new BigDecimal(2), user, new ArrayList<>(), new Date(), "test", 0));

        when(eventHandler.push(any(GetUserEvent.class))).thenReturn(new GetUserEvent(userList));
        when(eventHandler.push(any(GetItemEvent.class))).thenReturn(new GetItemEvent(itemList));
        when(eventHandler.push(any(GetWarehouseEvent.class))).thenReturn(new GetWarehouseEvent(warehouseList));

        persistenceUnderTest.saveAsJOS();

        verify(eventHandler).push(any(GetUserEvent.class));
        verify(eventHandler).push(any(GetItemEvent.class));
        verify(eventHandler).push(any(GetWarehouseEvent.class));
    }

    @Test
    void testLoadFromJOS() {
        UserList mockedUserList = mock(UserList.class);
        when(mockedUserList.getUserList()).thenReturn(new ArrayList<>());
        when(mockLoadService.loadFromJOS(any())).thenReturn(mockedUserList);
        final boolean result = persistenceUnderTest.loadFromJOS();

        assertTrue(result);

    }
}
