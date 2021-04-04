package service;

import model.Order;
import model.OrderItem;

import static com.google.common.base.Preconditions.*;

public class OrderValidator {

    public void validateOrderForCreate(Order order) {
        checkNotNull(order.getUserId(), "UserId is Null");
        checkNotNull(order.getItems(), "Array items is Null");
        checkNotNull(order.getDeliveryAddress(), "Delivery address is Null");
        checkNotNull(order.getOrderDate(), "Order date is Null");
    }

    public void validateOrderForPlace(Order order) {
        validateOrderForCreate(order);
        checkNotNull(order.getId(), "Id is Null");
        for (OrderItem item : order.getItems()) {
            checkNotNull(item, "Item in array is Null");
        }
        checkNotNull(order.getStatus(), "Status is Null");
    }
}
