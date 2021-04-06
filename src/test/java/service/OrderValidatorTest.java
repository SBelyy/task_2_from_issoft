package service;

import domain.Order;
import domain.OrderItem;
import domain.OrderStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderValidatorTest {

    @Test
    void validateOrderForPlace() {
        OrderValidator validator = new OrderValidator();

        Order order = new Order("User", new OrderItem[]{},
                new Date(), "Socialisticheskaia");

        assertThrows(NullPointerException.class, () -> validator.validateOrderForPlace(order));

        order.setId("121-1212-32233-232-343");
        order.setStatus(OrderStatus.CREATED);

        assertNotNull(order.getId());
        assertNotNull(order.getStatus());
    }

    @Test
    void validateOrders() {
        OrderValidator validator = new OrderValidator();
        List<Order> ordersNull = null;

        assertThrows(NullPointerException.class, () -> validator.validateOrders(ordersNull));

        Order order = new Order("User", new OrderItem[]{},
                new Date(), "Socialisticheskaia");
        List<Order> ordersNotNull = new ArrayList<>();
        ordersNotNull.add(order);

        assertThrows(NullPointerException.class, () -> validator.validateOrders(ordersNotNull));

        order.setId("121-1212-32233-232-343");
        order.setStatus(OrderStatus.CREATED);

        assertNotNull(order.getId());
        assertNotNull(order.getStatus());
    }

}