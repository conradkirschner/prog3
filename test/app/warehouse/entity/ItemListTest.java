package app.warehouse.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemListTest {

    private ItemList itemListUnderTest;

    @BeforeEach
    void setUp() {
        itemListUnderTest = new ItemList();
    }

    @Test
    void testToStringNull() {
        final String result = itemListUnderTest.toString();

        assertEquals("null", result);
    }
    @Test
    void testToStringWithItems() {
        Item item = mock(Item.class);
        when(item.toString()).thenReturn("works");
        List items = new ArrayList();
        items.add(item);
        itemListUnderTest.setItemList(items);
        final String result = itemListUnderTest.toString();

        assertEquals("[works]", result);
    }
    @Test
    void testGetItems() {
        Item item = mock(Item.class);
        when(item.toString()).thenReturn("works");
        List items = new ArrayList();
        items.add(item);
        itemListUnderTest.setItemList(items);
        final List<Item> result = itemListUnderTest.getItemList();

        assertEquals(items, result);
    }
}
