package model;

import java.util.Date;

public class Order {

    private String id;
    private final String userId;
    private OrderStatus status;
    private final OrderItem[] items;
    private final Date orderDate;
    private final String deliveryAddress;

    public Order(String userId, OrderItem[] items, String deliveryAddress) {
        this.userId = userId;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
        orderDate = new Date();
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderItem[] getItems() {
        return items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
}
