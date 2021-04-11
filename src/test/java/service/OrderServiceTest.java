package service;

import domain.Order;
import domain.OrderItem;
import domain.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.OrderItemStorage;
import storage.OrderStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderStorage orderStorage;
    private OrderItemStorage itemStorage;
    private OrderService orderService;

    @BeforeEach
    public void before() {
        orderStorage = mock(OrderStorage.class);
        itemStorage = mock(OrderItemStorage.class);
        orderService = new OrderService(orderStorage, itemStorage, new OrderValidator());
    }

    @Test
    void placeOrderTest() {
        Order orderValid = new Order("SBelyy", new OrderItem[]{},
                new Date(), "Adress");
        Order orderNotCorrect = new Order("SBelyy", null,
                new Date(), "Adress");
        String orderId = "234567-45454-454545";
        String itemId = "345-34-23";

        when(orderStorage.persistOrder(orderValid)).thenReturn(orderId);
        when(itemStorage.persistItems(orderValid.getItems())).thenReturn(itemId);

        assertEquals(orderId, orderService.placeOrder(orderValid));
        assertEquals(orderId, orderValid.getId());
        assertEquals(OrderStatus.CREATED, orderValid.getStatus());
        assertEquals(itemId, orderValid.getItemId());

        assertThrows(NullPointerException.class, () -> orderService.placeOrder(orderNotCorrect));
    }

    @Test
    void loadAllByUserIdTest() {
        List<Order> ordersStore = getCorrectListOrders();
        ordersStore.get(0).setItems(new OrderItem[]{});
        OrderItem[] items = new OrderItem[]{new OrderItem("Beer", 2, 4)};
        List<Order> orders = getCorrectListOrders();

        assertEquals(new ArrayList<>(), orderService.loadAllByUserId("2"));

        when(orderStorage.loadAllOrders()).thenReturn(null).thenReturn(new ArrayList<>());
        assertThrows(NullPointerException.class, () -> orderService.loadAllByUserId("1"));

        assertThrows(NullPointerException.class, () -> orderService.loadAllByUserId(null));

        when(orderStorage.loadAllOrders()).thenReturn(ordersStore);
        when(itemStorage.loadItemsById(null)).thenReturn(items);
        assertEquals(orders, orderService.loadAllByUserId("SBelyy"));
    }

    private List<Order> getCorrectListOrders() {
        Order orderCorrect = new Order("SBelyy", new OrderItem[]{new OrderItem("Beer", 2, 4)},
                new Date(1617649819917L), "Kurchatova 8");
        orderCorrect.setId("157a7604-7357-48db-8022-ac94bfe6b710");
        orderCorrect.setStatus(OrderStatus.CREATED);
        return Collections.singletonList(orderCorrect);
    }

}