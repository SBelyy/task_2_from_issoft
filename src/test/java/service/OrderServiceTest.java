package service;

import domain.Order;
import domain.OrderItem;
import domain.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.OrderStorage;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderService orderService;
    private Order orderNotCorrect;
    private Order orderCorrect;
    private String userId;

    private static final String FILE_NAME = "OrdersList.txt";

    @BeforeEach
    public void before() {
        List<String> lines = Collections.singletonList("157a7604-7357-48db-8022-ac94bfe6b710,SBelyy,CREATED,1617649819917,Kurchatova 8,[OrderItem{name=Beer quantity=2 cost=4}]");
        userId = "SBelyy";

        orderNotCorrect = new Order("Vasia", new OrderItem[]{}, new Date(), "Sofii 15");
        orderCorrect = new Order(userId, new OrderItem[]{new OrderItem("Beer", 2, 4)},
                new Date(1617649819917L), "Kurchatova 8");
        orderCorrect.setId("157a7604-7357-48db-8022-ac94bfe6b710");
        orderCorrect.setStatus(OrderStatus.CREATED);

        OrderStorage orderStorage;
        orderStorage = mock(OrderStorage.class);

        when(orderStorage.getAllOrdersFromFile(FILE_NAME)).thenReturn(lines);
        when(orderStorage.persistOrderInFile(orderCorrect, FILE_NAME)).thenReturn(true);

        orderService = new OrderService(orderStorage, new OrderValidator());
    }

    @Test
    void placeOrderTest() {
        assertTrue(orderService.placeOrder(orderCorrect));
        assertThrows(NullPointerException.class, () -> orderService.placeOrder(orderNotCorrect));
    }

    @Test
    void loadAllByUserIdTest() {
        List<Order> expectedOrders = Collections.singletonList(orderCorrect);
        List<Order> actualOrders = orderService.loadAllByUserId(userId);

        assertNotNull(actualOrders);
        assertEquals(expectedOrders, actualOrders);
    }


}