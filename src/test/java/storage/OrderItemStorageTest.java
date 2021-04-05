package storage;

import domain.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemStorageTest {

    private OrderItemStorage itemStorage;

    @BeforeEach
    void setUp() {
        itemStorage = new OrderItemStorage();
    }

    @Test
    void persistItemInFile() {
        OrderItem item = getValidItem();

        final String id = itemStorage.persistItemInFile(item);
        assertNotNull(id);

        final OrderItem itemLoaded = itemStorage.loadItem(id);

        assertEquals(item, itemLoaded);
    }

    private OrderItem getValidItem() {
        return new OrderItem("TestName", 0, 0);
    }
}