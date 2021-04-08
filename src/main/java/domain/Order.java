package domain;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Order {

    private String id;
    private OrderStatus status;
    private OrderItem[] items;
    private String itemId;
    private final String userId;
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

    public void setItems(OrderItem[] items) {
        this.items = items;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getItemId() {
        return itemId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(userId, order.userId) && Arrays.equals(items, order.items) && Objects.equals(deliveryAddress, order.deliveryAddress);
    }

}
