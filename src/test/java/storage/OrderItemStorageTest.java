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
        OrderItem[] item = getValidItem();
        String userId = "43565-43432-76-123-86";

        final String id = itemStorage.persistItems(item);
        assertNotNull(id);

        final OrderItem[] itemLoaded = itemStorage.loadItemsById(userId);

        assertEquals(item, itemLoaded);
    }

    private OrderItem[] getValidItem() {
        return new OrderItem[]{
                new OrderItem("TestName", 0, 0)};
    }
}