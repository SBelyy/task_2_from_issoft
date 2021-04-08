package service;

import domain.Order;
import domain.OrderItem;

import java.util.List;

import static com.google.common.base.Preconditions.*;

public class OrderValidator {

    public void validateOrderForPlace(Order order) {
        checkNotNull(order.getUserId(), "UserId is Null");
        checkNotNull(order.getItems(), "Array items is Null");
        checkNotNull(order.getDeliveryAddress(), "Delivery address is Null");
        checkNotNull(order.getOrderDate(), "Order date is Null");
        for (OrderItem item : order.getItems()) {
            checkNotNull(item, "Item in array is Null");
        }
    }

    public void validateOrders(List<Order> orders) {
        checkNotNull(orders, "List orders is Null");
        for (Order order : orders) {
            checkNotNull(order, "Order is Null");
            validateOrderForPlace(order);
        }
    }
}
