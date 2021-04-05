package service;

import domain.FileUtils;
import domain.Order;
import domain.OrderStatus;
import storage.OrderStorage;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class OrderService {

    private final OrderStorage orderStorage;
    private final OrderValidator orderValidator;

    private static final String FILE_NAME = "OrdersList.txt";

    public OrderService(OrderStorage orderStorage, OrderValidator orderValidator) {
        this.orderStorage = orderStorage;
        this.orderValidator = orderValidator;
    }

    /**
     * Creates an order and assigns an "almost" unique ID to it
     */
    public String createOrder(Order order) {
        orderValidator.validateOrderForCreate(order);

        final String id = UUID.randomUUID().toString();
        order.setId(id);
        order.setStatus(OrderStatus.CREATED);

        return id;
    }

    /**
     * The method puts Order passed to it into a text file and returns an true if successful
     */
    public boolean placeOrder(Order order) {
        orderValidator.validateOrderForPlace(order);

        return orderStorage.persistOrderInFile(order, FILE_NAME);
    }

    /**
     * This method returns a List<Order> by the userId that was received when reading the file
     */
    public List<Order> loadAllByUserId(String userId) {
        List<String> lines = orderStorage.getAllOrdersFromFile(FILE_NAME);

        checkNotNull(lines);

        List<Order> orders = new FileUtils().getOrdersFromLines(lines);
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
