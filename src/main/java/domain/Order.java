package domain;

import java.util.Arrays;
import java.util.Date;

public class Order {

    private String id;
    private OrderStatus status;
    private final String userId;
    private final OrderItem[] items;
    private final Date orderDate;
    private final String deliveryAddress;

    public Order(String userId, OrderItem[] items, Date date, String deliveryAddress) {
        this.userId = userId;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
        orderDate = date;
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

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "," + userId + "," +
                status + "," + orderDate.getTime() + ","
                + deliveryAddress + "," + Arrays.toString(items);
    }
}