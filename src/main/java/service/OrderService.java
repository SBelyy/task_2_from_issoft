package service;

import domain.OrderAndItemParser;
import domain.Order;
import domain.OrderItem;
import domain.OrderStatus;
import storage.OrderItemStorage;
import storage.OrderStorage;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class OrderService {

    private final OrderStorage orderStorage;
    private final OrderItemStorage itemStorage;
    private final OrderValidator orderValidator;

    public OrderService(OrderStorage orderStorage, OrderItemStorage itemStorage, OrderValidator orderValidator) {
        this.orderStorage = orderStorage;
        this.itemStorage = itemStorage;
        this.orderValidator = orderValidator;
    }

    public String placeOrder(Order order) {
        orderValidator.validateOrderForPlace(order);

        final String id = orderStorage.persistOrder(order);
        order.setId(id);
        order.setStatus(OrderStatus.CREATED);

        itemStorage.persistItem(order.getItems(), id);

        return id;
    }

    public List<Order> loadAllByUserId(String userId) {
        List<String> lines = orderStorage.loadAllOrders();

        checkNotNull(lines);

        if (userId == null)
            throw new NullPointerException();

        OrderItem[] items = itemStorage.loadItem(userId);
        List<Order> orders = new OrderAndItemParser().getOrdersFromLines(lines, items);
        orderValidator.validateOrders(orders);

        List<Order> targetOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUserId().equals(userId)) {
                targetOrders.add(order);
            }
        }

        return targetOrders;
    }

}
